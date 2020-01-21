package gtclassic.common.block;

import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockMachineCustom extends GTBlockMachine {

	/** The same as GTBlockMachine but much more control over textures **/
	public GTBlockMachineCustom(String name, LocaleComp comp) {
		super(name, comp);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public TextureAtlasSprite[] getIconSheet(int meta) {
		return buildTexture(0,0,0,3,0,0);
	}
	
	//Array is as follows {bottom, top, back, front, right, left} as veiwed from the player
	private TextureAtlasSprite[] buildTexture(int bottom, int top, int back, int front, int right, int left) {
		return new TextureAtlasSprite[] {Ic2Icons.getTextures("idsu")[bottom], Ic2Icons.getTextures("idsu")[top], Ic2Icons.getTextures("idsu")[back], Ic2Icons.getTextures("idsu")[front], Ic2Icons.getTextures("idsu")[right], Ic2Icons.getTextures("idsu")[left],Ic2Icons.getTextures("idsu")[bottom], Ic2Icons.getTextures("idsu")[top], Ic2Icons.getTextures("idsu")[back], Ic2Icons.getTextures("idsu")[front], Ic2Icons.getTextures("idsu")[right], Ic2Icons.getTextures("idsu")[left]};
	}
}
