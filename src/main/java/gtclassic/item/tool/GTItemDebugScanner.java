package gtclassic.item.tool;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.block.tileentity.GTTileEntityFusionComputer;
import gtclassic.block.tileentity.GTTileEntityLightningRod;
import gtclassic.block.tileentity.GTTileEntityQuantumChest;
import ic2.api.classic.item.IEUReader;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityElectricBlock;
import ic2.core.item.base.ItemIC2;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDebugScanner extends ItemIC2 implements IEUReader {

	public GTItemDebugScanner() {
		this.maxStackSize = 1;
		setRegistryName("debug_scanner");
		setUnlocalizedName(GTClassic.MODID + ".debug_scanner");
		setCreativeTab(GTClassic.creativeTabGT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures("gtclassic_items")[59];
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

			if (tileEntity instanceof GTTileEntityLightningRod) {
				GTTileEntityLightningRod te = (GTTileEntityLightningRod) tileEntity;
				IC2.platform.messagePlayer(player, "---Lightning Rod Multi Block Information---");
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + te.checkStructure());
				IC2.platform.messagePlayer(player, "Fence Amount: " + (te.fenceheight - (te.getPos().getY() + 1)));
				IC2.platform.messagePlayer(player, "Fence Level: " + te.fenceheight);
				IC2.platform.messagePlayer(player, "Weather Height: " + world.getPrecipitationHeight(pos).getY());
				IC2.platform.messagePlayer(player, "Block Up Level: " + (te.getPos().getY() + 1));
				IC2.platform.messagePlayer(player, "Storm Strength: " + ((int) (world.thunderingStrength) * 100) + "%");
				IC2.platform.messagePlayer(player, "1 out of " + te.chance + " chance to strike based on fence height");
				IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
				return EnumActionResult.SUCCESS;
			}

			if (tileEntity instanceof GTTileEntityFusionComputer) {
				GTTileEntityFusionComputer te1 = (GTTileEntityFusionComputer) tileEntity;
				IC2.platform.messagePlayer(player, "---Fusion Computer Multi Block Information---");
				IC2.platform.messagePlayer(player, "Correct Strucuture: " + te1.checkStructure());
				IC2.platform.messagePlayer(player, "Active: " + te1.getActive());
				IC2.platform.messagePlayer(player, "Progress: " + ((int) (te1.getProgress() / 100)) + "%");
				IC2.platform.messagePlayer(player, "Stored EU: " + te1.getStoredEU());
				IC2.audioManager.playOnce(player, Ic2Sounds.scannerUse);
				return EnumActionResult.SUCCESS;
			}
			
			if (tileEntity instanceof GTTileEntityQuantumChest) {
				 GTTileEntityQuantumChest te2 = ( GTTileEntityQuantumChest) tileEntity;
				IC2.platform.messagePlayer(player, "---Quantum Chest Information---");
				IC2.platform.messagePlayer(player, "Quantity: " + te2.getAmount());
				IC2.platform.messagePlayer(player, "Qauntum Count: " + te2.getCount());
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
			}

			else {
				IC2.platform.messagePlayer(player, "Nothing to read from this");
			}

		}
		return EnumActionResult.SUCCESS;

	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	public boolean isEUReader(ItemStack var1) {
		return true;
	}

	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}

}
