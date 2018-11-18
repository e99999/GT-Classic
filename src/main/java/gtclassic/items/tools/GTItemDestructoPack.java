package gtclassic.items.tools;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.items.inventory.GTInventoryDestructoPack;
import ic2.core.IC2;
import ic2.core.inventory.base.IHandHeldInventory;
import ic2.core.inventory.base.IHasGui;
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

	
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
    	ItemStack stack = player.getHeldItem(hand);
    	if (IC2.platform.isSimulating()) {
			IC2.platform.launchGui(player, this.getInventory(player, hand, stack), hand);
    	}
    	return EnumActionResult.SUCCESS;
    }
    
    
    
    @Override
	public int getGuiId(ItemStack var1) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public IHasGui getInventory(EntityPlayer player, EnumHand hand, ItemStack stack) {
		return new GTInventoryDestructoPack(player, stack);
	}

	@Override
	public void setGuiID(ItemStack stack, int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getTextureEntry(int var1) {
		return 0;
	}

	
	
	
	

}
