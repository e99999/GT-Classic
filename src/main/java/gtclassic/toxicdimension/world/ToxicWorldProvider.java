package gtclassic.toxicdimension.world;

import gtclassic.ModDimensions;
import gtclassic.toxicdimension.biome.BiomeProviderCustom;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToxicWorldProvider extends WorldProvider {
	
	@Override
	public boolean hasSkyLight() {
		return true;
	}

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.testDimensionType;
    }

    @Override
    public String getSaveFolder() {
        return "TEST";
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ToxicChunkGenerator(world);
    }
    
    @Override
	public void init() {
		this.biomeProvider = new BiomeProviderCustom(this.world.getSeed());
	}

    @Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float par1, float par2) {
		return new Vec3d(0.01568627450980392D, 0.09019607843137255D, 0.0D);
	}
    
    @Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public boolean canCoordinateBeSpawn(int par1, int par2) {
		return false;
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public float getSunBrightness(float par1) {
		return (par1*2F);
	}

	@Override
	public float getStarBrightness(float par1) {
		return (par1*5F);
	}
	
	@Override
    public boolean canDoLightning(net.minecraft.world.chunk.Chunk chunk)
    {
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int par1, int par2) {
		return true;
	}
}
