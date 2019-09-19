package gtclassic.item;

import gtclassic.GTMod;
import gtclassic.tile.GTTileBaseBuffer;
import gtclassic.tile.GTTileBaseMachine;
import gtclassic.tile.GTTileDrum;
import gtclassic.tile.GTTileLESU;
import gtclassic.tile.multi.GTTileMultiBaseMachine;
import gtclassic.tile.multi.GTTileMultiBlastFurnace;
import gtclassic.tile.multi.GTTileMultiLightningRod;
import ic2.api.classic.item.IEUReader;
import ic2.api.energy.EnergyNet;
import ic2.api.item.ElectricItem;
import ic2.api.reactor.IReactor;
import ic2.api.tile.IEnergyStorage;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.block.base.tile.TileEntityGeneratorBase;
import ic2.core.block.base.tile.TileEntityTransformer;
import ic2.core.block.crop.TileEntityCrop;
import ic2.core.block.personal.base.misc.IPersonalBlock;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemCreativeScanner extends ItemBatteryBase implements IEUReader {

	public GTItemCreativeScanner() {
		super(0);
		this.setRightClick();
		setRegistryName("debug_scanner");
		setUnlocalizedName(GTMod.MODID + ".debug_scanner");
		setCreativeTab(GTMod.creativeTabGT);
		this.maxCharge = Integer.MAX_VALUE;
		this.transferLimit = Integer.MAX_VALUE;
		this.tier = 1;
		this.provider = true;
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return true;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}

	@Override
	public boolean wantsToPlay(ItemStack stack) {
		return true;
	}

	@Override
	public ResourceLocation createSound(ItemStack stack) {
		return Ic2Sounds.batteryUse;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			ItemStack full = new ItemStack(this, 1, 0);
			ElectricItem.manager.charge(full, 2.147483647E9D, Integer.MAX_VALUE, true, false);
			items.add(full);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(ItemStack item) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[31];
	}

	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		return scanBlock(player, world, pos, side, hitX, hitY, hitZ, hand);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
	}

	@Override
	public boolean isEUReader(ItemStack var1) {
		return true;
	}

	/*
	 * The logic for both the creative and survival scanners.
	 */
	@SuppressWarnings("deprecation")
	public static EnumActionResult scanBlock(EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, EnumHand hand) {
		if (player.isSneaking() || !IC2.platform.isSimulating()) {
			return EnumActionResult.PASS;
		} else {
			TileEntity tileEntity = world.getTileEntity(pos);
			IBlockState state = world.getBlockState(pos);
			IC2.platform.messagePlayer(player, "-----X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ()
					+ " -----");
			IC2.platform.messagePlayer(player, "" + state.getBlock().getLocalizedName());
			IC2.platform.messagePlayer(player, "" + state.getBlock().getUnlocalizedName());
			IC2.platform.messagePlayer(player, "Meta: " + state.getBlock().getMetaFromState(state));
			IC2.platform.messagePlayer(player, "Hardness: " + state.getBlock().getBlockHardness(state, world, pos));
			IC2.platform.messagePlayer(player, "Blast Resistance: "
					+ state.getBlock().getExplosionResistance(null) * 5.0F);
			IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
			if (tileEntity instanceof TileEntityBlock) {
				TileEntityBlock te = (TileEntityBlock) tileEntity;
				IC2.platform.messagePlayer(player, "Active: " + te.getActive());
				IC2.platform.messagePlayer(player, "Facing: " + te.getFacing());
			}
			if (tileEntity instanceof TileEntityGeneratorBase) {
				TileEntityGeneratorBase te2 = (TileEntityGeneratorBase) tileEntity;
				IC2.platform.messagePlayer(player, "Fuel: " + te2.fuel);
				IC2.platform.messagePlayer(player, "Storage: " + te2.storage + " EU");
			}
			if (tileEntity instanceof TileEntityElecMachine) {
				TileEntityElecMachine te3 = (TileEntityElecMachine) tileEntity;
				IC2.platform.messagePlayer(player, "Tier: " + te3.getTier());
				IC2.platform.messagePlayer(player, "Energy: " + te3.energy + " EU");
			}
			if (tileEntity instanceof IEnergyStorage) {
				IEnergyStorage te4 = (IEnergyStorage) tileEntity;
				IC2.platform.messagePlayer(player, "Stored: " + te4.getStored() + " EU");
			}
			if (tileEntity instanceof IReactor) {
				IReactor te5 = (IReactor) tileEntity;
				IC2.platform.messagePlayer(player, "Reactor Heat: " + te5.getHeat());
				IC2.platform.messagePlayer(player, "Max Heat: " + te5.getMaxHeat());
				IC2.platform.messagePlayer(player, "HEM: " + te5.getHeatEffectModifier());
				IC2.platform.messagePlayer(player, "Output: " + te5.getReactorEnergyOutput() + " EU");
			}
			if (tileEntity instanceof IPersonalBlock) {
				IPersonalBlock te6 = (IPersonalBlock) tileEntity;
				IC2.platform.messagePlayer(player, "Can Access: " + te6.canAccess(player.getUniqueID()));
			}
			if (tileEntity instanceof TileEntityCrop) {
				TileEntityCrop te7 = (TileEntityCrop) tileEntity;
				IC2.platform.messagePlayer(player, "Crop: " + te7.getCrop());
				IC2.platform.messagePlayer(player, "Size: " + te7.getCurrentSize());
				IC2.platform.messagePlayer(player, "Growth: " + te7.getStatGrowth());
				IC2.platform.messagePlayer(player, "Gain: " + te7.getStatGain());
				IC2.platform.messagePlayer(player, "Resistance: " + te7.getStatResistance());
				IC2.platform.messagePlayer(player, "Nutrients: " + te7.getTerrainNutrients());
				IC2.platform.messagePlayer(player, "Water: " + te7.getTerrainHumidity());
				IC2.platform.messagePlayer(player, "Growth Points: " + te7.getGrowthPoints());
			}
			if (tileEntity instanceof GTTileBaseMachine) {
				GTTileBaseMachine machine = (GTTileBaseMachine) tileEntity;
				IC2.platform.messagePlayer(player, "Progress: "
						+ (Math.round((machine.getProgress() / machine.getMaxProgress()) * 100)) + "%");
				IC2.platform.messagePlayer(player, "Default Input: " + machine.defaultEnergyConsume + " EU");
				IC2.platform.messagePlayer(player, "Max Input: " + machine.defaultMaxInput + " EU");
				if (machine instanceof GTTileMultiBlastFurnace) {
					IC2.platform.messagePlayer(player, "Speed Boost: " + machine.progressPerTick + "X");
				}
			}
			if (tileEntity instanceof GTTileMultiBaseMachine) {
				GTTileMultiBaseMachine multi = (GTTileMultiBaseMachine) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + multi.checkStructure());
			}
			if (tileEntity instanceof GTTileMultiLightningRod) {
				GTTileMultiLightningRod rod = (GTTileMultiLightningRod) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + rod.checkStructure());
				IC2.platform.messagePlayer(player, "Casing Block Amount: "
						+ (rod.casingheight - (rod.getPos().getY() + 1)));
				IC2.platform.messagePlayer(player, "Casing Block Level: " + rod.casingheight);
				IC2.platform.messagePlayer(player, "Weather Height: " + world.getPrecipitationHeight(pos).getY());
				IC2.platform.messagePlayer(player, "Block Up Level: " + (rod.getPos().getY() + 1));
				IC2.platform.messagePlayer(player, "Storm Strength: " + ((int) (world.thunderingStrength) * 100) + "%");
				IC2.platform.messagePlayer(player, "1 out of " + rod.chance
						+ " chance to strike based on fence height");
			}
			if (tileEntity instanceof GTTileDrum) {
				GTTileDrum drum = (GTTileDrum) tileEntity;
				FluidStack fluid = drum.getTankInstance().getFluid();
				if (fluid != null) {
					IC2.platform.messagePlayer(player, fluid.amount + "mB of " + fluid.getLocalizedName());
				}
			}
			if (tileEntity instanceof TileEntityElectricBlock) {
				TileEntityElectricBlock eu = (TileEntityElectricBlock) tileEntity;
				IC2.platform.messagePlayer(player, "Tier: " + eu.getTier());
				IC2.platform.messagePlayer(player, "Capacity: " + eu.getMaxEU() + " EU");
				IC2.platform.messagePlayer(player, "Output: " + eu.getOutput() + " EU");
				if (eu instanceof GTTileLESU) {
					IC2.platform.messagePlayer(player, "Max Input: 32 EU");
					IC2.platform.messagePlayer(player, "Lapotron Blocks: " + ((GTTileLESU) eu).getCount());
					IC2.platform.messagePlayer(player, "Energy Packets: "
							+ ((GTTileLESU) eu).getMultipleEnergyPacketAmount());
				}
			}
			if (tileEntity instanceof TileEntityTransformer) {
				TileEntityTransformer transformer = (TileEntityTransformer) tileEntity;
				IC2.platform.messagePlayer(player, "Low: " + transformer.lowOutput + " EU");
				IC2.platform.messagePlayer(player, "Low Tier: "
						+ EnergyNet.instance.getTierFromPower((double) transformer.lowOutput));
				IC2.platform.messagePlayer(player, "High: " + transformer.highOutput + " EU");
				IC2.platform.messagePlayer(player, "High Tier: "
						+ EnergyNet.instance.getTierFromPower((double) transformer.highOutput));
				IC2.platform.messagePlayer(player, "Stored: " + transformer.getStoredEU() + " EU");
			}
			if (tileEntity instanceof GTTileBaseBuffer) {
				GTTileBaseBuffer buffer = (GTTileBaseBuffer) tileEntity;
				IC2.platform.messagePlayer(player, "Outputs Power: " + buffer.conduct);
				if (buffer.conduct) {
					IC2.platform.messagePlayer(player, "Stored: " + buffer.energy + " EU");
				}
				IC2.platform.messagePlayer(player, "Outputs Redstone: " + buffer.outputRedstone);
				if (buffer.outputRedstone) {
					IC2.platform.messagePlayer(player, "Redstone Strength: " + buffer.redstoneStrength);
				}
				IC2.platform.messagePlayer(player, "Inverted Redstone: " + buffer.invertRedstone);
			}
			return EnumActionResult.SUCCESS;
		}
	}
}
