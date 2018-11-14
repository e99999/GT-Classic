package gtclassic.items.tools;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import gtclassic.util.GTItems;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemMortar extends Item implements IStaticTexturedItem {
	int itemIndex;
	boolean enchant;

	public GTItemMortar(int maxDamage, String reg, String un, int index, boolean enchant) {
		this.maxStackSize = 1;
		this.itemIndex = index;
		this.enchant = enchant;
		this.setMaxDamage(maxDamage);//7
		setRegistryName(reg);//"flint_mortar"
        setUnlocalizedName(GTClassic.MODID + "." + un);//"mortarFlint"
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
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (this == GTItems.mortarIron){
        	tooltip.add(I18n.format("tooltip."+ GTClassic.MODID +".motar"));
        }

    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return this.enchant;
    }
	
	@Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("gtclassic_items")[this.itemIndex];
    }//31

}

