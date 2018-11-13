package gtclassic.items.tools;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemMortarFlint extends Item implements IStaticTexturedItem {
	
	public GTItemMortarFlint() {
		this.maxStackSize = 1;
		this.setMaxDamage(7);
		setRegistryName("flint_mortar");      
        setUnlocalizedName(GTClassic.MODID + ".mortarFlint");
        setCreativeTab(GTClassic.creativeTabGT);
    }

	@Override
    public boolean hasContainerItem(ItemStack itemStack)
    {
             return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
         // copy our item.
         ItemStack returnItem = new ItemStack(itemStack.getItem(), 1, itemStack.getItemDamage()+1);
  
         // is it enchanted
         // if so, copy the enchantment
         if (itemStack.isItemEnchanted())
         {
              NBTTagCompound nbtcompound = itemStack.getTagCompound();
              returnItem.setTagCompound(nbtcompound);
         }        

         return returnItem;
    }
	
	@Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("gtclassic_items")[31];
    }

}

