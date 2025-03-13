package gtclassic.common.tile;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.interfaces.IGTCoordinateTile;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.common.util.GTTextWrapper;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.reactor.IReactor;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.tile.TileEntityReactorChamberBase;
import ic2.core.block.crop.TileEntityCrop;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.util.obj.IClickable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

public class GTTileDisplayScreen extends TileEntityMachine
		implements IGTDebuggableTile, IGTCoordinateTile, IClickable, ITickable {

	private BlockPos targetPos;
	private static final String NBT_TARGETPOS = "targetPos";
	private static final String NBT_INFO = "information";
	private static final String NBT_DRAW = "shouldDraw";
	private static final String MB = "Mb of", EMPTY_TANK = "Tank Empty", EMPTY_CHEST = "Chest Empty",
			NO_DATA = "No Data", CROP = "Crop", ON = "ON", OFF = "OFF", HEAT = "Heat: ", MAX = "Max: ", TEMP = "Temp: ",
			OUTPUT = "Output: ", EU = " EU", PROGRESS = "Progress: ", OF = " of", AMP = "%", SLASH = " /",
			SIZE = "Size: ", GROWTH = "Growth: ", GAIN = "Gain: ", RESISTANCE = "Resistance: ",
			NUTRIENTS = "Nutrients: ", WATER = "Water: ", POINTS = "Points: ", RAYMOND = "...";
	@NetworkField(index = 3)
	public GTTextWrapper information = new GTTextWrapper();
	@NetworkField(index = 4)
	public boolean shouldDraw = true;

	public GTTileDisplayScreen() {
		super(1);
		this.addNetworkFields(NBT_INFO, NBT_DRAW);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey(NBT_TARGETPOS)) {
			this.targetPos = NBTUtil.getPosFromTag(nbt.getCompoundTag(NBT_TARGETPOS));
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		if (this.targetPos != null) {
			nbt.setTag(NBT_TARGETPOS, NBTUtil.createPosTag(targetPos));
		}
		return nbt;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}

	@SideOnly(Side.CLIENT)
	public void applySize(float size, double posX, double posY, double posz) {
		GL11.glTranslated(0.0D, 0.0D, posz);
		GL11.glScalef(0.0039063F, 0.0039063F, -1.0E-004F);
		GL11.glTranslated(posX, posY, 0.0D);
		GL11.glScalef(size, size, 1.0F);
	}

	@SideOnly(Side.CLIENT)
	public void applyRotation(float x, float y, float z) {
		GL11.glTranslatef((float) x + 0.5f, (float) y + 0.5f, (float) z + 0.5f);
		switch (this.facing) {
		case 2:
			GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			break;
		case 3:
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			break;
		case 4:
			GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180, 0.0F, 0F, 1.0F);
			break;
		case 5:
			GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180, 0.0F, 0F, 1.0F);
			break;
		}
		GL11.glTranslated(-0.5D, -0.5D, -0.5D);
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 10 == 0) {
			draw();
			this.information.getWrapperList().clear();
			if (this.targetPos != null && world.isBlockLoaded(this.targetPos) && !world.isAirBlock(this.targetPos)) {
				IBlockState targetState = world.getBlockState(this.targetPos);
				Block targetBlock = targetState.getBlock();
				String name = new ItemStack(targetBlock, 1, targetBlock.getMetaFromState(targetState)).getDisplayName();
				if (!name.equals(CROP)) {
					addInfoToScreenSplit(name);
				}
				collectTileInformation();
				collectFluidTankInformation();
			} else {
				this.information.getWrapperList().add(NO_DATA);
			}
			this.getNetwork().updateTileEntityField(this, NBT_INFO);
		}
	}

	public void draw() {
		if (!this.shouldDraw) {
			this.shouldDraw = true;
			this.getNetwork().updateTileEntityField(this, NBT_DRAW);
		}
	}

	public void collectTileInformation() {
		TileEntity tileEntity = world.getTileEntity(this.targetPos);
		if (tileEntity instanceof TileEntityElecMachine) {
			TileEntityMachine machine = (TileEntityMachine) tileEntity;
			addInfoToScreen(machine.getActive() ? ON : OFF);
		}
		if (tileEntity instanceof IReactor) {
			IReactor te5 = (IReactor) tileEntity;
			addInfoToScreen(TEMP + te5.getHeat());
			addInfoToScreen(MAX + te5.getMaxHeat());
			addInfoToScreen(HEAT + te5.getHeat() / te5.getMaxHeat() + AMP);
			addInfoToScreen(OUTPUT + formatNumberForScreen((int) te5.getReactorEUEnergyOutput()) + EU);
		}
		if (tileEntity instanceof TileEntityReactorChamberBase) {
			TileEntityReactorChamberBase chamber = (TileEntityReactorChamberBase) tileEntity;
			IReactor reactor = chamber.getReactorInstance();
			if (reactor != null) {
				addInfoToScreen(TEMP + reactor.getHeat());
			}
		}
		if (tileEntity instanceof IProgressMachine) {
			IProgressMachine progress = (IProgressMachine) tileEntity;
			addInfoToScreen(PROGRESS + +(Math.round((progress.getProgress() / progress.getMaxProgress()) * 100)) + AMP);
		}
		if (tileEntity instanceof GTTileQuantumChest) {
			GTTileQuantumChest chest = (GTTileQuantumChest) tileEntity;
			int count = chest.getQuantumCount();
			if (count > 0) {
				addInfoToScreen(chest.getQuantumCount() + OF);
				addInfoToScreen(chest.getStackInSlot(2).getDisplayName());
			} else {
				addInfoToScreen(EMPTY_CHEST);
			}
		}
		if (tileEntity instanceof IEUStorage) {
			IEUStorage euStorage = (IEUStorage) tileEntity;
			addInfoToScreen(formatNumberForScreen(euStorage.getStoredEU()) + SLASH);
			addInfoToScreen(formatNumberForScreen(euStorage.getMaxEU()) + EU);
		}
		if (tileEntity instanceof TileEntityCrop) {
			TileEntityCrop crop = (TileEntityCrop) tileEntity;
			addInfoToScreen(crop.getCrop().getId());
			addInfoToScreen(SIZE + crop.getCurrentSize());
			addInfoToScreen(GROWTH + crop.getStatGrowth());
			addInfoToScreen(GAIN + crop.getStatGain());
			addInfoToScreen(RESISTANCE + crop.getStatResistance());
			addInfoToScreen(NUTRIENTS + crop.getTerrainNutrients());
			addInfoToScreen(WATER + crop.getTerrainHumidity());
			addInfoToScreen(POINTS + crop.getGrowthPoints());
		}
	}

	public void collectFluidTankInformation() {
		IFluidHandler fluidTile = GTHelperFluid.getFluidHandler(world, this.targetPos, null);
		if (fluidTile != null) {
			IFluidTankProperties[] props = fluidTile.getTankProperties();
			for (int i = 0; i < props.length; ++i) {
				FluidStack fluid = props[i].getContents();
				if (fluid != null && fluid.amount > 0) {
					addInfoToScreen(fluid.amount + MB);
					addInfoToScreen(fluid.getLocalizedName());
				} else {
					addInfoToScreen(EMPTY_TANK);
				}
			}
		}
	}

	public void addInfoToScreenSplit(String text) {
		if (text.length() > 14) {
			String[] words = text.split("\\s+");
			for (String word : words) {
				addInfoToScreen(word);
			}
		} else {
			addInfoToScreen(text);
		}
	}

	public void addInfoToScreen(String text) {
		this.information.getWrapperList().add(formatTextForScreen(text));
	}

	private static String formatTextForScreen(String text) {
		return text.length() > 14 ? text.substring(0, 14) + RAYMOND : text;
	}

	private static String formatNumberForScreen(int number) {
		return NumberFormat.getNumberInstance(Locale.US).format(number);
	}

	@Override
	public boolean applyCoordinates(BlockPos pos, int dimensionId) {
		if (!pos.toString().equals(this.pos.toString())) {
			this.targetPos = pos;
			return true;
		}
		return false;
	}

	@Override
	public void setFacing(EnumFacing face) {
		super.setFacing(face);
		this.shouldDraw = false;
		this.getNetwork().updateTileEntityField(this, NBT_DRAW);
	}

	@Override
	public boolean isInterdimensional() {
		return false;
	}

	@Override
	public BlockPos getAppliedPos() {
		return this.targetPos;
	}

	@Override
	public int getAppliedDimId() {
		return this.world.provider.getDimension();
	}

	@Override
	public boolean hasLeftClick() {
		return false;
	}

	@Override
	public boolean hasRightClick() {
		return true;
	}

	@Override
	public void onLeftClick(EntityPlayer arg0, Side arg1) {
		// neeeded by interface, unused by tile
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand arg1, EnumFacing arg2, Side arg3) {
		ItemStack slotStack = this.getStackInSlot(0);
		if (slotStack.isEmpty() || !player.isSneaking()) {
			return false;
		}
		ItemHandlerHelper.giveItemToPlayer(player, slotStack.copy());
		slotStack.shrink(1);
		this.resetTargetPos();
		IC2.audioManager.playOnce(player, Ic2Sounds.wrenchUse);
		return true;
	}

	public void resetTargetPos() {
		this.targetPos = null;
	}

	@Override
	public boolean insertSensorStick(ItemStack card) {
		if (!this.getStackInSlot(0).isEmpty()) {
			return false;
		}
		this.setStackInSlot(0, card.copy());
		return true;
	}

	@Override
	public void getData(Map<String, Boolean> data) {
		data.put("Facing: " + this.getFacing().toString().toUpperCase(), true);
		data.put("Facing Int : " + this.facing, true);
	}
}
