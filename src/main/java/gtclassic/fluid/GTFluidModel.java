package gtclassic.fluid;

import java.util.List;

import ic2.core.platform.textures.Ic2Models;
import ic2.core.platform.textures.models.BaseModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;

public class GTFluidModel extends BaseModel {
	IBakedModel model;
	Fluid fluid;

	public GTFluidModel(Fluid fluid) {
		super(Ic2Models.getBlockTransforms());
		this.fluid = fluid;
	}

	@Override
	public void init() {
		IModel forge = new ModelFluid(fluid);
		model = forge.bake(forge.getDefaultState(), DefaultVertexFormats.ITEM, ModelLoader.defaultTextureGetter());
	}

	@Override
	public boolean isAmbientOcclusion(IBlockState state) {
		return model.isAmbientOcclusion(state);
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return model.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return model.getOverrides();
	}

	@Override
	public boolean isAmbientOcclusion() {
		return model.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return model.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return model.isBuiltInRenderer();
	}

	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
		return model.getQuads(state, side, rand);
	}
}
