package gtclassic.common.item;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gtclassic.GTMod;
import gtclassic.api.interfaces.IGTCoordinateTile;
import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTMultiTileStatus;
import gtclassic.api.tile.GTTileBaseMachine;
import gtclassic.api.world.GTBedrockOreHandler;
import ic2.api.classic.item.IEUReader;
import ic2.api.classic.tile.machine.IEUStorage;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.item.ElectricItem;
import ic2.api.reactor.IReactor;
import ic2.api.tile.IEnergyStorage;
import ic2.core.IC2;
import ic2.core.block.crop.TileEntityCrop;
import ic2.core.block.personal.base.misc.IPersonalBlock;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemCreativeScanner extends ItemBatteryBase implements IEUReader {

	public static final String POS = "pos";
	public static final String BLOCK = "block";

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
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		genToolTip(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return Color.CYAN.hashCode();
	}

	public static void genToolTip(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		if (nbt.getIntArray(POS).length == 4) {
			int[] pos = nbt.getIntArray(POS);
			tooltip.add(TextFormatting.GREEN + I18n.format("Last Scanned: "));
			if (nbt.getString("block") != null) {
				tooltip.add(TextFormatting.DARK_GREEN + I18n.format(nbt.getString(BLOCK)));
			}
			tooltip.add(TextFormatting.DARK_GREEN + I18n.format("X: " + pos[0] + " Y: " + pos[1] + " Z: " + pos[2]));
			tooltip.add(TextFormatting.DARK_GREEN + I18n.format("Dimension: " + pos[3]));
			tooltip.add(TextFormatting.ITALIC + I18n.format("Sneak to apply coords to a machine"));
		}
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
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slot, boolean selected) {
		if (ElectricItem.manager.getCharge(stack) < ElectricItem.manager.getMaxCharge(stack)) {
			ElectricItem.manager.charge(stack, 2.147483647E9D, Integer.MAX_VALUE, true, false);
		}
	}

	@Override
	public boolean isEUReader(ItemStack var1) {
		return true;
	}

	public static EnumActionResult tryParseCoords(World world, BlockPos pos, EntityPlayer player, EnumHand hand) {
		TileEntity tileEntity = world.getTileEntity(pos);
		NBTTagCompound nbt = StackUtil.getNbtData(player.getHeldItem(hand));
		if (tileEntity instanceof IGTCoordinateTile && nbt.getIntArray(POS).length == 4) {
			int[] posArr = nbt.getIntArray(POS);
			IGTCoordinateTile coordTile = (IGTCoordinateTile) tileEntity;
			if (!coordTile.isInterdimensional() && posArr[3] != world.provider.getDimension()) {
				IC2.platform.messagePlayer(player, "This machine does not support interdimensional communication");
				return EnumActionResult.PASS;
			}
			if (coordTile.applyCoordinates(new BlockPos(posArr[0], posArr[1], posArr[2]), posArr[3])) {
				IC2.platform.messagePlayer(player, "Coordinates successfully parsed to machine!");
				return EnumActionResult.SUCCESS;
			}
		}
		IC2.platform.messagePlayer(player, "Parsing coordinates to this block failed!");
		return EnumActionResult.FAIL;
	}

	/*
	 * The logic for both the creative and survival scanners.
	 */
	@SuppressWarnings("deprecation")
	public static EnumActionResult scanBlock(EntityPlayer player, World world, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ, EnumHand hand) {
		if (!IC2.platform.isSimulating()) {
			return EnumActionResult.PASS;
		}
		IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
		if (player.isSneaking()) {
			return tryParseCoords(world, pos, player, hand);
		} else {
			NBTTagCompound nbt = StackUtil.getOrCreateNbtData(player.getHeldItem(hand));
			nbt.setIntArray(POS, new int[] { pos.getX(), pos.getY(), pos.getZ(), world.provider.getDimension() });
			nbt.setString(BLOCK, world.getBlockState(pos).getBlock().getLocalizedName());
			TileEntity tileEntity = world.getTileEntity(pos);
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			IC2.platform.messagePlayer(player, "-----X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ()
					+ " -----");
			IC2.platform.messagePlayer(player, "You are facing: "
					+ player.getHorizontalFacing().toString().toUpperCase());
			IC2.platform.messagePlayer(player, "You clicked: " + side.toString().toUpperCase());
			IC2.platform.messagePlayer(player, "" + state.getBlock().getLocalizedName());
			if (tileEntity == null) {
				IC2.platform.messagePlayer(player, "Hardness: " + state.getBlock().getBlockHardness(state, world, pos));
				IC2.platform.messagePlayer(player, "Blast Resistance: "
						+ state.getBlock().getExplosionResistance(null) * 5.0F);
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
				IC2.platform.messagePlayer(player, "Crop: " + te7.getCrop().getId());
				IC2.platform.messagePlayer(player, "Size: " + te7.getCurrentSize());
				IC2.platform.messagePlayer(player, "Growth: " + te7.getStatGrowth());
				IC2.platform.messagePlayer(player, "Gain: " + te7.getStatGain());
				IC2.platform.messagePlayer(player, "Resistance: " + te7.getStatResistance());
				IC2.platform.messagePlayer(player, "Nutrients: " + te7.getTerrainNutrients());
				IC2.platform.messagePlayer(player, "Water: " + te7.getTerrainHumidity());
				IC2.platform.messagePlayer(player, "Growth Points: " + te7.getGrowthPoints());
			}
			if (tileEntity instanceof IEnergySink) {
				IEnergySink euSink = (IEnergySink) tileEntity;
				IC2.platform.messagePlayer(player, "Input Tier: " + euSink.getSinkTier());
				IC2.platform.messagePlayer(player, "Input Max: "
						+ EnergyNet.instance.getPowerFromTier(euSink.getSinkTier()) + " EU");
			}
			if (tileEntity instanceof GTTileBaseMachine) {
				GTTileBaseMachine machine = (GTTileBaseMachine) tileEntity;
				IC2.platform.messagePlayer(player, "Usage: " + (double) machine.defaultEnergyConsume + " EU Per Tick");
			}
			if (tileEntity instanceof IEUStorage) {
				IEUStorage euStorage = (IEUStorage) tileEntity;
				IC2.platform.messagePlayer(player, "Stored: " + euStorage.getStoredEU() + " EU");
				IC2.platform.messagePlayer(player, "Storage Max: " + euStorage.getMaxEU() + " EU");
			}
			if (tileEntity instanceof IEnergyStorage) {
				IEnergyStorage te4 = (IEnergyStorage) tileEntity;
				IC2.platform.messagePlayer(player, "Output Tier: "
						+ EnergyNet.instance.getTierFromPower(te4.getOutputEnergyUnitsPerTick()));
				IC2.platform.messagePlayer(player, "Output: " + te4.getOutputEnergyUnitsPerTick() + " EU");
			}
			if (tileEntity instanceof IEnergySource) {
				IEnergySource euSource = (IEnergySource) tileEntity;
				IC2.platform.messagePlayer(player, "Output Source Tier: " + euSource.getSourceTier());
				IC2.platform.messagePlayer(player, "Output Source Max: "
						+ EnergyNet.instance.getPowerFromTier(euSource.getSourceTier()) + " EU");
				IC2.platform.messagePlayer(player, "Output Source Actual: " + euSource.getOfferedEnergy() + " EU");
			}
			if (tileEntity instanceof IProgressMachine) {
				IProgressMachine progress = (IProgressMachine) tileEntity;
				IC2.platform.messagePlayer(player, "Progress: "
						+ +(Math.round((progress.getProgress() / progress.getMaxProgress()) * 100)) + "%");
			}
			if (GTBedrockOreHandler.isBedrockOre(block)) {
				ItemStack resource = GTBedrockOreHandler.getResource(block);
				String amount = resource.getCount() > 1 ? " x " + resource.getCount() : "";
				IC2.platform.messagePlayer(player, "Contains: "
						+ GTBedrockOreHandler.getResource(block).getDisplayName() + amount);
			}
			if (tileEntity instanceof IGTMultiTileStatus) {
				IGTMultiTileStatus multi = (IGTMultiTileStatus) tileEntity;
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + multi.getStructureValid());
			}
			if (tileEntity instanceof IGTDebuggableTile) {
				LinkedHashMap<String, Boolean> data = new LinkedHashMap<>();
				IGTDebuggableTile debug = (IGTDebuggableTile) tileEntity;
				debug.getData(data);
				for (Map.Entry<String, Boolean> entry : data.entrySet()) {
					if (!entry.getValue()) {
						IC2.platform.messagePlayer(player, entry.getKey());
					}
					if (entry.getValue()) {
						IC2.platform.messagePlayer(player, entry.getKey());
					}
				}
			}
			return EnumActionResult.SUCCESS;
		}
	}
}
