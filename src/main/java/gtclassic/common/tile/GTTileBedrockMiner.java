package gtclassic.common.tile;

import java.util.List;
import java.util.Random;

import gtclassic.api.helpers.GTHelperFluid;
import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import gtclassic.api.material.GTMaterial;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.world.GTBedrockOreHandler;
import gtclassic.common.GTBlocks;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerBedrockMiner;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.fluid.IC2Tank;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.item.misc.ItemDisplayIcon;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.obj.IClickable;
import ic2.core.util.obj.ITankListener;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileBedrockMiner extends TileEntityElecMachine
		implements ITickable, IHasGui, IGTDisplayTickTile, ITankListener, IClickable {

	ItemStack output;
	private IC2Tank tank;
	public static final ItemStack pipe = GTMaterialGen.get(GTBlocks.miningPipe);
	public static final IFilter filter = new BasicItemFilter(pipe);
	static final int[] SLOT_INPUTS = { 0, 1 };
	static final int[] SLOT_OUTPUTS = { 2, 3 };
	static final int[] SLOT_ALLVALID = { 0, 1, 2, 3 };
	static final int SLOT_TANK = 4;
	static final int SLOT_FUEL = 5;
	public static final String NBT_TANK = "tank";

	public GTTileBedrockMiner() {
		super(6, 512);
		this.setFuelSlot(SLOT_FUEL);
		this.tank = new IC2Tank(16000);
		this.tank.addListener(this);
		this.addGuiFields(NBT_TANK);
		maxEnergy = 1000000;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, SLOT_INPUTS);
		handler.registerDefaultSlotAccess(AccessRule.Export, SLOT_OUTPUTS);
		handler.registerDefaultSlotsForSide(RotationList.DOWN.invert(), SLOT_ALLVALID);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Ic2Items.suBattery)), SLOT_FUEL);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, SLOT_FUEL);
		handler.registerSlotType(SlotType.Fuel, SLOT_FUEL);
		handler.registerSlotType(SlotType.Input, SLOT_INPUTS);
		handler.registerSlotType(SlotType.Output, SLOT_OUTPUTS);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.BEDROCK_MINER;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.tank.readFromNBT(nbt.getCompoundTag(NBT_TANK));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.tank.writeToNBT(this.getTag(nbt, NBT_TANK));
		return nbt;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? true
				: super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
				? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this.tank)
				: super.getCapability(capability, facing);
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer var1) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerBedrockMiner(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer arg0) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer arg0) {
	}

	public int[] getInputSlots() {
		return SLOT_INPUTS;
	}

	public int[] getOutputSlots() {
		return SLOT_OUTPUTS;
	}

	@Override
	public void update() {
		this.handleChargeSlot(this.maxEnergy);
		checkForBedrockOre();
		if (this.output == null) {
			this.setActive(false);
			return;
		}
		boolean pipeFound = false;
		for (int j : getInputSlots()) {
			if (GTHelperStack.isEqual(pipe, this.inventory.get(j))) {
				pipeFound = true;
				break;
			}
		}
		if (!pipeFound) {
			this.setActive(false);
			return;
		}
		for (int i : getOutputSlots()) {
			if (GTHelperStack.canMerge(this.output, this.getStackInSlot(i)) && this.energy >= 4096
					&& !this.redstoneEnabled()) {
				if (world.rand.nextInt(31) == 0) {
					int count = this.getStackInSlot(i).getCount();
					this.setStackInSlot(i, GTHelperStack.copyWithSize(this.output, count + 1));
					world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 0.4F, 1.0F);
					tryDamagePipe();
					tryRemoveOre();
					tryExport();
				}
				this.energy = this.energy - 4096;
				this.getNetwork().updateTileGuiField(this, "energy");
				this.setActive(true);
				break;
			} else {
				this.setActive(false);
			}
		}
	}

	public void tryDamagePipe() {
		if (world.rand.nextInt(15) == 0) {
			for (int i : getInputSlots()) {
				if (GTHelperStack.isEqual(pipe, this.inventory.get(i))) {
					if (this.hasLube()) {
						useLube();
					} else {
						this.inventory.get(i).shrink(1);
						world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 0.4F, 1.0F);
						tryAndBeNice();
					}
					break;
				}
			}
		}
	}

	public boolean hasLube() {
		return this.tank.getFluid() != null
				&& this.tank.getFluid().getFluid() == GTMaterialGen.getFluid(GTMaterial.Lubricant)
				&& this.tank.getFluidAmount() >= 100;
	}

	public void useLube() {
		this.tank.drain(100, true);
	}

	public void tryAndBeNice() {
		if (world.rand.nextInt(2) == 0) {
			for (int j : getOutputSlots()) {
				if (GTHelperStack.canMerge(GTMaterialGen.getDust(GTMaterial.Tungsten, 1), this.getStackInSlot(j))) {
					int count = this.getStackInSlot(j).getCount();
					this.setStackInSlot(j, GTHelperStack.copyWithSize(GTMaterialGen.getDust(GTMaterial.Tungsten, 1), count
							+ 1));
				}
			}
		}
	}

	public void tryRemoveOre() {
		if (world.rand.nextInt(127) == 0) {
			for (BlockPos pos : getAreaToCheck()) {
				Block block = world.getBlockState(pos).getBlock();
				if (GTBedrockOreHandler.isBedrockOre(block)) {
					world.setBlockState(pos, Blocks.BEDROCK.getDefaultState());
					this.output = null;
					break;
				}
			}
		}
	}

	public Iterable<BlockPos> getAreaToCheck() {
		BlockPos downPos = this.pos.offset(EnumFacing.DOWN);
		return BlockPos.getAllInBox(downPos.offset(EnumFacing.SOUTH, 2).offset(EnumFacing.WEST, 2), downPos.offset(EnumFacing.NORTH, 2).offset(EnumFacing.EAST, 2).offset(EnumFacing.DOWN, 5));
	}

	public void checkForBedrockOre() {
		if (world.getTotalWorldTime() % 100 == 0) {
			for (BlockPos pos : getAreaToCheck()) {
				Block block = world.getBlockState(pos).getBlock();
				if (isBlockProperlySet() && GTBedrockOreHandler.isBedrockOre(block)) {
					this.output = GTBedrockOreHandler.getResource(block).copy();
					break;
				} else {
					this.output = null;
				}
			}
		}
	}

	public boolean isBlockProperlySet() {
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == Blocks.BEDROCK || GTBedrockOreHandler.isBedrockOre(block);
	}

	public void tryExport() {
		IItemTransporter slave = TransporterManager.manager.getTransporter(world.getTileEntity(pos.up()), false);
		if (slave != null) {
			for (int i : getOutputSlots()) {
				int added = slave.addItem(this.getStackInSlot(i).copy(), EnumFacing.DOWN, true).getCount();
				if (added > 0) {
					this.getStackInSlot(i).shrink(added);
				}
			}
		}
	}

	public boolean redstoneEnabled() {
		return this.world.isBlockPowered(this.getPos());
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
	public boolean supportsNotify() {
		return true;
	}

	@Override
	public List<ItemStack> getDrops() {
		List<ItemStack> list = super.getDrops();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getItem() instanceof ItemDisplayIcon) {
				list.remove(i);
			}
		}
		return list;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isActive) {
			for (int i = 0; i < 3; ++i) {
				double d0 = (double) pos.getX() + rand.nextDouble();
				double d1 = (double) pos.getY() + .5D + rand.nextDouble() * 0.5D + 0.5D;
				double d2 = (double) pos.getZ() + rand.nextDouble();
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void onTankChanged(IFluidTank tank) {
		this.getNetwork().updateTileGuiField(this, NBT_TANK);
		this.inventory.set(SLOT_TANK, ItemDisplayIcon.createWithFluidStack(this.tank.getFluid()));
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
	public void onLeftClick(EntityPlayer var1, Side var2) {
	}

	@Override
	public boolean onRightClick(EntityPlayer player, EnumHand hand, EnumFacing enumFacing, Side side) {
		return GTHelperFluid.doClickableFluidContainerThings(player, hand, world, pos, this.tank);
	}

	@Override
	public double getWrenchDropRate() {
		return 1.0D;
	}
}
