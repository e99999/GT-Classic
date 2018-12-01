package gtclassic.items.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.IC2;
import ic2.core.item.base.BasicElectricItem;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemElectromagnet extends BasicElectricItem implements ITexturedItem {
	
	boolean isActive;
	
	public GTItemElectromagnet() 
    {
		this.maxStackSize = 1;
		this.setRegistryName("electromagnet");
        this.setUnlocalizedName(GTClassic.MODID + ".electroMagnet");
        this.maxCharge = 1000;
        this.transferLimit = 10;
        this.tier = 1;
        this.setCreativeTab(GTClassic.creativeTabGT);
        this.isActive = false;
    }
	
	public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1.0F, 1.0F);
		if (IC2.platform.isSimulating()) 
		{
			if (!isActive)
			{
				this.isActive = true;
				//turn on magnet
			}
			else 
			{
				this.isActive = false;
				//turn off magnet
			}
			
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return isActive;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta)
	{
        return Ic2Icons.getTextures("gtclassic_items")[59];
    }

	@Override
    public List<Integer> getValidVariants()
	{
        return Arrays.asList(0);
    }
	
	@Override
	public List<ItemStack> getValidItemVariants()
	{
		return new ArrayList();
	}

	@Override
	public TextureAtlasSprite getTexture(ItemStack var1)
	{
		return null;
	}

	@Override
	public int getTextureEntry(int var1)
	{
		return 0;
	}

	

}
