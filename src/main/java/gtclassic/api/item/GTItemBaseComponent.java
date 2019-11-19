package gtclassic.api.item;

import java.util.Arrays;
import java.util.List;

import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemBaseComponent extends Item implements IStaticTexturedItem {

	public String name;
	public String sprite;
	int x;
	int y;

	/**
	 * Constructor for making a simple item with no action.
	 * 
	 * @param name   - String name for the item
	 * @param x      - int column
	 * @param y      - int row
	 * @param sprite - sprite sheet to use
	 */
	public GTItemBaseComponent(String name, int x, int y, String sprite) {
		this.name = name;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}

	@Override
	public List<Integer> getValidVariants() {
		return Arrays.asList(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite getTexture(int i) {
		return Ic2Icons.getTextures(sprite)[(this.y * 16) + this.x];
	}
}
