package gtclassic.items.tools;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemHammerIron extends ItemPickaxe implements IStaticTexturedItem {
	
	public GTItemHammerIron() 
	{
		super(Item.ToolMaterial.IRON);
		this.setMaxDamage(500);
		setRegistryName("iron_hammer");
        setUnlocalizedName(GTClassic.MODID + ".hammerIron");
        setCreativeTab(GTClassic.creativeTabGT);
    }
	
	@Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
    	tooltip.add(stack.getMaxDamage() - stack.getItemDamage()+"/500");
    }
	
	@Override
    public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack copy = itemStack.copy();
		return copy.attemptDamageItem(1, itemRand, null) ? ItemStack.EMPTY : copy;
    }
	
	@Override
    public List<Integer> getValidVariants() 
	{
        return Arrays.asList(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta) 
    {
        return Ic2Icons.getTextures("gtclassic_items")[62];
    }

}
