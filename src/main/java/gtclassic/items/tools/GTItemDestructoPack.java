package gtclassic.items.tools;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.inventory.base.IHandHeldInventory;
import ic2.core.inventory.base.IHasGui;
import ic2.core.item.base.ItemIC2;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDestructoPack extends ItemIC2 implements IHandHeldInventory{

	public GTItemDestructoPack(){
        this.setCreativeTab(GTClassic.creativeTabGT);
        this.setRegistryName("destructo_pack");
        this.setUnlocalizedName(GTClassic.MODID + ".destructoPack");
	}
	
	
	@Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("gtclassic_items")[33];
    }

	@Override
	public int getGuiId(ItemStack var1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IHasGui getInventory(EntityPlayer var1, EnumHand var2, ItemStack var3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGuiID(ItemStack var1, int var2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}

	
	
	
	

}
