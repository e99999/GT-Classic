package gtclassic.tile;

import gtclassic.container.GTContainerTranslocator;
import gtclassic.util.int3;
import ic2.api.classic.network.adv.NetworkField;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.energy.EnergyNetLocal;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.inventory.transport.IItemTransporter;
import ic2.core.inventory.transport.TransporterManager;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.math.MathUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileTranslocator extends TileEntityMachine implements IEnergyConductor, IEnergySink, IHasGui, ITickable {

	// Import and Export Booleans
	boolean allowImport = true;
	boolean allowExport = true;
	public int tier = 1;
	public int output = 32;
	public int maxEnergy = 32;
	@NetworkField(index = 3)
	public int energy;
	public boolean addedToEnet;

	public GTTileTranslocator() {
		super(9);
		setWorld(world);
		this.addGuiFields(new String[] { "energy" });
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerTranslocator(player.inventory, this);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		int[] array = MathUtil.fromTo(0, 8);
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, array);
		handler.registerDefaultSlotsForSide(RotationList.ALL, array);
		handler.registerSlotType(SlotType.Input, array);
		handler.registerSlotType(SlotType.Output, array);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.energy = nbt.getInteger("energy");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("energy", this.energy);
		return nbt;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (this.isSimulating() && !this.addedToEnet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.addedToEnet = true;
		}
	}

	@Override
	public void onUnloaded() {
		if (this.isSimulating() && this.addedToEnet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.addedToEnet = false;
		}
		super.onUnloaded();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return !this.isInvalid();
	}

	@Override
	public void onGuiClosed(EntityPlayer entityPlayer) {
		// needed for construction
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 20 == 0) {
			tryImportItems();
			tryExportItems();
		}
	}

	public TileEntity getImportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.back(1).asBlockPos());
	}

	public TileEntity getExportTile() {
		int3 dir = new int3(getPos(), getFacing());
		return world.getTileEntity(dir.forward(1).asBlockPos());
	}

	@SuppressWarnings("static-access")
	public void tryImportItems() {
		if (this.allowImport && world.getTotalWorldTime() % 20 == 0) {
			IItemTransporter slave = TransporterManager.manager.getTransporter(getImportTile(), true);
			if (slave == null) {
				return;
			}
			IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
			int limit = 64;
			for (int i = 0; i < limit; ++i) {
				ItemStack stack = slave.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, false);
				if (stack.isEmpty()) {
					break;
				}
				ItemStack added = controller.addItem(stack, getFacing().SOUTH, true);
				if (added.getCount() <= 0) {
					break;
				}
				slave.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
			}
		}
	}

	@SuppressWarnings("static-access")
	public void tryExportItems() {
		if (this.allowImport && world.getTotalWorldTime() % 20 == 0) {
			IItemTransporter slave = TransporterManager.manager.getTransporter(getExportTile(), true);
			if (slave == null) {
				return;
			}
			IItemTransporter controller = TransporterManager.manager.getTransporter(this, true);
			int limit = 64;
			for (int i = 0; i < limit; ++i) {
				ItemStack stack = controller.removeItem(CommonFilters.Anything, getFacing().NORTH, 1, false);
				if (stack.isEmpty()) {
					break;
				}
				ItemStack added = slave.addItem(stack, getFacing().UP, true);
				if (added.getCount() <= 0) {
					break;
				}
				controller.removeItem(CommonFilters.Anything, getFacing().getOpposite(), 1, true);
			}
		}
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return this.getFacing() != facing;
	}

	@Override
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
		return this.getFacing() != side;
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
		return this.getFacing() == side;
	}

	@Override
	public double getDemandedEnergy() {
		return (double) (this.maxEnergy - this.energy);
	}

	@Override
	public int getSinkTier() {
		return 1;
	}

	@Override
	public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
		if (amount <= (double) this.output && amount > 0.0D) {
			this.energy = (int) ((double) this.energy + amount);
			int left = 0;
			if (this.energy >= this.maxEnergy) {
				left = this.energy - this.maxEnergy;
				this.energy = this.maxEnergy;
			}
			this.getNetwork().updateTileGuiField(this, "energy");
			return (double) left;
		} else {
			return 0.0D;
		}
	}

	@Override
	public double getConductionLoss() {
		return 0.2D;
	}

	@Override
	public double getConductorBreakdownEnergy() {
		return 33.0D;
	}

	@Override
	public double getInsulationBreakdownEnergy() {
		return 9001.0D;
	}

	@Override
	public double getInsulationEnergyAbsorption() {
		return 32.0D;
	}

	@Override
	public void removeConductor() {
		boolean burn = EnergyNetLocal.burn && this.world.rand.nextFloat() < EnergyNetLocal.chance;
		this.world.setBlockToAir(this.getPos());
		this.getNetwork().initiateTileEntityEvent(this, 0, true);
		if (burn) {
			this.world.setBlockState(this.getPos(), Blocks.FIRE.getDefaultState());
		}
	}

	@Override
	public void removeInsulation() {
		// TODO Auto-generated method stub
	}
}
