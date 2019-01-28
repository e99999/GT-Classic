package gtclassic.item.materials;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.GTRecipes;
import gtclassic.util.GTValues;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class GTItemStick extends Item implements IStaticTexturedItem {
	public enum GTItemStickTypes {
		IRON(48),ALUMINIUM(49), TITANIUM(50), CHROME(51),PLATINUM(53), TUNGSTEN(52);

		private int id;

		GTItemStickTypes(int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}
		
		public String getName() {
			return name().toLowerCase();
		}
		
	}

	GTItemStickTypes variant;

	public GTItemStick(GTItemStickTypes variant) {
		this.variant = variant;
		setRegistryName(variant.toString().toLowerCase() + "_stick");
		setUnlocalizedName(GTMod.MODID + "." + variant.toString().toLowerCase() + "_stick");
		setCreativeTab(GTMod.creativeTabGT);
		setRecipe();
	}
	
	public String getFormatName() {
		String name = variant.toString().toLowerCase();
		String output = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (GTValues.debugMode){
			GTMod.logger.info("Creating sticks/rods: "+ output);
			}
		return output;
	}
	
	public void setRecipe() {
		String input = "ingot" + getFormatName();
		GTRecipes.recipes.addShapelessRecipe(new ItemStack(this, 1),
				new Object[] { input, "craftingToolFile" });
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[variant.getID()];
	}
}
