package gtclassic.common.tile;

import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.helpers.GTUtility;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.common.GTLang;
import gtclassic.common.container.GTContainerRockBreaker;
import ic2.core.RotationList;
import ic2.core.inventory.base.IHasGui;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.IFilter;
import ic2.core.inventory.gui.GuiComponentContainer;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTTileRockBreaker extends GTTileBufferBase implements IHasGui {

	public static final IFilter reallyCoolHackyItemStackArrayFilter = new ArrayFilter(new IFilter[] {
			new BasicItemFilter(Items.REDSTONE), new BasicItemFilter(Items.QUARTZ) });
	RockMode rockMode = RockMode.INVALID;
	static final ItemStack[] randomRocks = { new ItemStack(Blocks.STONE, 1, 1), new ItemStack(Blocks.STONE, 1, 3),
			new ItemStack(Blocks.STONE, 1, 5) };

	public GTTileRockBreaker() {
		super(2);
	}

	@Override
	public LocaleComp getBlockName() {
		return GTLang.ROCK_BREAKER;
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Import, 1);
		handler.registerDefaultSlotAccess(AccessRule.Export, 0);
		handler.registerDefaultSlotsForSide(RotationList.ALL, 0, 1);
		handler.registerInputFilter(reallyCoolHackyItemStackArrayFilter, 1);
		handler.registerSlotType(SlotType.Input, 1);
		handler.registerSlotType(SlotType.Output, 0);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return facing != getFacing() && facing.getAxis().isHorizontal();
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return !this.isInvalid();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GuiComponentContainer.class;
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerRockBreaker(player.inventory, this);
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}

	@Override
	public void onGuiClosed(EntityPlayer arg0) {
	}

	@Override
	public void update() {
		checkStructure();
		super.update();
	}

	@Override
	public void onBufferTick() {
		GTUtility.exportFromMachineToSide(this, this.getFacing(), 0);
		if (this.rockMode != null && this.rockMode != RockMode.INVALID && this.energy >= 32) {
			ItemStack input = this.getStackInSlot(1);
			ItemStack output = this.getStackInSlot(0);
			if (processInputSlot(input, output)) {
				this.energy = 0;
				return;
			}
			if (this.rockMode == RockMode.COBBLE
					&& GTHelperStack.canMerge(GTMaterialGen.get(Blocks.COBBLESTONE), output)) {
				this.setStackInSlot(0, StackUtil.copyWithSize(GTMaterialGen.get(Blocks.COBBLESTONE), this.getStackInSlot(0).getCount()
						+ 1));
				this.energy = 0;
				return;
			}
			if (this.rockMode == RockMode.STONE && GTHelperStack.canMerge(GTMaterialGen.get(Blocks.STONE), output)) {
				this.setStackInSlot(0, StackUtil.copyWithSize(GTMaterialGen.get(Blocks.STONE), this.getStackInSlot(0).getCount()
						+ 1));
				this.energy = 0;
			}
		}
	}

	private boolean processInputSlot(ItemStack input, ItemStack output) {
		if (input.isEmpty()) {
			return false;
		}
		if (GTHelperStack.matchOreDict(input, "dustRedstone")
				&& GTHelperStack.canMerge(GTMaterialGen.get(Blocks.OBSIDIAN), output)) {
			input.shrink(1);
			this.setStackInSlot(0, StackUtil.copyWithSize(GTMaterialGen.get(Blocks.OBSIDIAN), this.getStackInSlot(0).getCount()
					+ 1));
			return true;
		}
		ItemStack rock = randomRocks[world.rand.nextInt(randomRocks.length)].copy();
		if (GTHelperStack.matchOreDict(input, "gemQuartz") && output.isEmpty()) {
			input.shrink(1);
			this.setStackInSlot(0, StackUtil.copyWithSize(rock, this.getStackInSlot(0).getCount() + 1));
			return true;
		}
		return false;
	}

	private void checkStructure() {
		if (world.getTotalWorldTime() % 60 == 0) {
			EnumFacing foundSide = null;
			for (EnumFacing side : EnumFacing.VALUES) {
				IBlockState x = world.getBlockState(pos.offset(side));
				IBlockState z = world.getBlockState(pos.offset(side.getOpposite()));
				if (x.getMaterial() == Material.WATER && x.getValue(BlockLiquid.LEVEL) == 0
						&& z.getMaterial() == Material.LAVA && z.getValue(BlockLiquid.LEVEL) == 0) {
					foundSide = side;
					break;
				}
			}
			if (foundSide == null) {
				this.rockMode = RockMode.INVALID;
				return;
			}
			this.rockMode = foundSide.getAxis().isHorizontal() ? RockMode.COBBLE : RockMode.STONE;
		}
	}

	@Override
	public boolean isInventoryFull() {
		return !this.inventory.get(0).isEmpty();
	}

	public enum RockMode {
		COBBLE(),
		STONE(),
		INVALID();
	}
}
