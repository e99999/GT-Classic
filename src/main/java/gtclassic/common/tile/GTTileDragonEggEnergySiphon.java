package gtclassic.common.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gtclassic.api.helpers.GTUtility;
import gtclassic.api.interfaces.IGTDisplayTickTile;
import gtclassic.api.material.GTMaterialGen;
import gtclassic.api.recipe.GTRecipeMultiInputList;
import gtclassic.common.GTConfig;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.RecipeModifierHelpers.ModifierType;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.base.tile.TileEntityMachine;
import ic2.core.block.base.util.info.misc.IEmitterTile;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GTTileDragonEggEnergySiphon extends TileEntityMachine
		implements IEnergySource, IEmitterTile, IGTDisplayTickTile, ITickable {

	protected double production = 128.0D;
	int storage;
	boolean enet = false;
	private int tickOffset = 0;
	public static final GTRecipeMultiInputList RECIPE_LIST = new GTRecipeMultiInputList("gt.trophies");

	public GTTileDragonEggEnergySiphon() {
		super(0);
	}

	@Override
	public boolean emitsEnergyTo(IEnergyAcceptor var1, EnumFacing facing) {
		return true;
	}

	@Override
	public int getOutput() {
		return 128;
	}

	@Override
	public void drawEnergy(double amount) {
	}

	@Override
	public double getOfferedEnergy() {
		return this.getActive() ? this.production : 0.0D;
	}

	@Override
	public int getSourceTier() {
		return 2;
	}

	@Override
	public void onLoaded() {
		super.onLoaded();
		if (this.isSimulating() && !this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
			this.enet = true;
		}
		if (this.isSimulating()) {
			this.tickOffset = world.rand.nextInt(128);
		}
		this.checkForEgg();
	}

	@Override
	public void onUnloaded() {
		if (this.isSimulating() && this.enet) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
			this.enet = false;
		}
		super.onUnloaded();
	}

	@Override
	public void onBlockUpdate(Block block) {
		this.checkForEgg();
	}

	private void checkForEgg() {
		int trophyValue = GTUtility.getTrophyProductionValue(world, pos.up());
		this.production = trophyValue;
		this.setActive(this.production > 0);
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return false;
	}

	public static void initFakeRecipes() {
		addFakeRecipe(GTMaterialGen.get(Blocks.DRAGON_EGG), 128);
		if (!GTConfig.general.energySiphonJustSucksEggs) {
			addFakeRecipe(new ItemStack(Items.SKULL, 1, 1), 1);
			addFakeRecipe(new ItemStack(Items.SKULL, 1, 5), 8);
			addFakeRecipe(new ItemStack(Blocks.BEACON), 999);
		}
	}

	public static void addFakeRecipe(ItemStack stack, int energyPerTick) {
		addFakeRecipe(new RecipeInputItemStack(stack), new IRecipeModifier[] {
				ModifierType.RECIPE_LENGTH.create(energyPerTick) });
	}

	private static void addFakeRecipe(IRecipeInput input, IRecipeModifier[] modifiers) {
		List<IRecipeInput> inlist = new ArrayList<>();
		List<ItemStack> outlist = new ArrayList<>();
		NBTTagCompound mods = new NBTTagCompound();
		for (IRecipeModifier modifier : modifiers) {
			modifier.apply(mods);
		}
		inlist.add(input);
		outlist.add(GTMaterialGen.get(Items.REDSTONE));
		addFakeRecipe(inlist, new MachineOutput(mods, outlist));
	}

	private static void addFakeRecipe(List<IRecipeInput> input, MachineOutput output) {
		if (!input.isEmpty()) {
			RECIPE_LIST.addRecipe(input, output, input.get(0).getInputs().get(0).getTranslationKey(), 128);
		}
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % (248 + this.tickOffset) == 0 && this.getActive()
				&& world.getBlockState(this.pos.up()).getBlock().equals(Blocks.BEACON)) {
			TileEntity tile = world.getTileEntity(pos.up());
			if (tile instanceof TileEntityBeacon) {
				TileEntityBeacon beacon = (TileEntityBeacon) tile;
				this.production = GTUtility.getBeaconProductionValue(beacon);
			}
		}
	}

	@Override
	public void randomTickDisplay(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.isActive) {
			for (int i = 0; i < 3; ++i) {
				double j = (rand.nextInt(2) * 2 - 1) * .5;
				double k = (rand.nextInt(2) * 2 - 1) * .5;
				double d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
				double d1 = (double) ((float) pos.getY() + rand.nextFloat() + .5);
				double d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) k;
				double d3 = (double) (rand.nextFloat() * (float) j);
				double d4 = ((double) rand.nextFloat() - 0.5D) * 0.125D;
				double d5 = (double) (rand.nextFloat() * (float) k);
				worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
			}
		}
	}
}
