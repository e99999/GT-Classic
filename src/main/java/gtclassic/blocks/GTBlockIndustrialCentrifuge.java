package gtclassic.blocks;

import gtclassic.GTClassic;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import ic2.core.block.base.BlockMultiID;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GTBlockIndustrialCentrifuge extends BlockMultiID {
    public GTBlockIndustrialCentrifuge(){
        super(Material.IRON);
        this.setCreativeTab(GTClassic.creativeTabGT);
        this.setRegistryName("industrial_centrifuge");
        this.setUnlocalizedName(GTClassic.MODID + ".industrialCentrifuge");
    }

    @Override
    public List<Integer> getValidMetas() {
        return Arrays.asList(0);
    }

    @Override
    public TileEntityBlock createNewTileEntity(World worldIn, int meta){
        return new GTTileEntityIndustrialCentrifuge();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getIconSheet(int meta)
    {
        return Ic2Icons.getTextures("gtclassic_blocklv");
    }

    @Override
    public List<IBlockState> getValidStateList()
    {
        IBlockState def = getDefaultState();
        List<IBlockState> states = new ArrayList<IBlockState>();
        for(EnumFacing side : EnumFacing.VALUES)
        {
            states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active, false));
            states.add(def.withProperty(getMetadataProperty(), 0).withProperty(allFacings, side).withProperty(active, true));
        }
        return states;
    }

    @Override
    public List<IBlockState> getValidStates()
    {
        return getBlockState().getValidStates();
    }
}
