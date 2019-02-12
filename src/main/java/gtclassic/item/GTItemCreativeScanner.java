package gtclassic.item;

import gtclassic.GTMod;
import gtclassic.tile.GTTileDigitalChest;
import gtclassic.tile.GTTileFusionComputer;
import gtclassic.tile.GTTileLightningRod;
import ic2.api.classic.item.IEUReader;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
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
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[79];
	}

	@Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		if (player.isSneaking()) {
			return EnumActionResult.PASS;
		} else {

			TileEntity tileEntity = world.getTileEntity(pos);

			if (!IC2.platform.isSimulating()) {
				return EnumActionResult.PASS;
			}

			if (tileEntity instanceof GTTileLightningRod) {
				GTTileLightningRod te = (GTTileLightningRod) tileEntity;
				IC2.platform.messagePlayer(player, "---Lightning Rod Multi Block Information---");
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + te.checkStructure());
				IC2.platform.messagePlayer(player,
						"Casing Block Amount: " + (te.casingheight - (te.getPos().getY() + 1)));
				IC2.platform.messagePlayer(player, "Casing Block Level: " + te.casingheight);
				IC2.platform.messagePlayer(player, "Weather Height: " + world.getPrecipitationHeight(pos).getY());
				IC2.platform.messagePlayer(player, "Block Up Level: " + (te.getPos().getY() + 1));
				IC2.platform.messagePlayer(player, "Storm Strength: " + ((int) (world.thunderingStrength) * 100) + "%");
				IC2.platform.messagePlayer(player, "1 out of " + te.chance + " chance to strike based on fence height");
				IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
				return EnumActionResult.SUCCESS;
			}

			if (tileEntity instanceof GTTileFusionComputer) {
				GTTileFusionComputer te1 = (GTTileFusionComputer) tileEntity;
				IC2.platform.messagePlayer(player, "---Fusion Computer Multi Block Information---");
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + te1.checkStructure());
				IC2.platform.messagePlayer(player, "Active: " + te1.getActive());
				IC2.platform.messagePlayer(player, "Progress: " + ((int) (te1.getProgress() / 100)) + "%");
				IC2.platform.messagePlayer(player, "Stored EU: " + te1.getStoredEU());
				IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
				return EnumActionResult.SUCCESS;
			}

			if (tileEntity instanceof GTTileDigitalChest) {
				GTTileDigitalChest te2 = (GTTileDigitalChest) tileEntity;
				IC2.platform.messagePlayer(player, "---Quantum Chest Information---");
				IC2.platform.messagePlayer(player, "Display Count: " + te2.getDisplayCount());
				IC2.platform.messagePlayer(player, "Internal Count: " + te2.getQuantumCount());
				IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
				return EnumActionResult.SUCCESS;
			}

			if (tileEntity instanceof TileEntityElectricBlock) {
				TileEntityElectricBlock te4 = (TileEntityElectricBlock) tileEntity;
				IC2.platform.messagePlayer(player, "---Electric Storage Information---");
				IC2.platform.messagePlayer(player, "Tier: " + te4.getTier());
				IC2.platform.messagePlayer(player, "Output: " + te4.getOutput());
				IC2.platform.messagePlayer(player, "Stored EU: " + te4.getStored());
				IC2.platform.messagePlayer(player, "Max EU: " + te4.getCapacity());
				IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
				return EnumActionResult.SUCCESS;
			}

			else {
				IC2.platform.messagePlayer(player, "Nothing to read from this");
				return EnumActionResult.PASS;
			}
		}

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
	}

	@Override
	public boolean isEUReader(ItemStack var1) {
		return true;
	}

}
