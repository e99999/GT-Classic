package gtclassic.items.tools;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.api.classic.item.IEUReader;
import ic2.core.item.base.ItemIC2;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDebugScanner extends ItemIC2 implements IEUReader {
    public GTItemDebugScanner() {
        this.setMaxStackSize(1);
    	this.setRegistryName("debug_scanner");
        this.setUnlocalizedName(GTClassic.MODID + ".debugScanner");
        this.setCreativeTab(GTClassic.creativeTabGT);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
    	return EnumActionResult.PASS;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int meta) {
		return Ic2Icons.getTextures("gtclassic_items")[55];
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

