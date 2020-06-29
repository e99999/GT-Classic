package gtclassic.api.block;

import gtclassic.GTMod;
import gtclassic.api.helpers.GTHelperStack;
import gtclassic.api.interfaces.IGTColorBlock;
import gtclassic.api.interfaces.IGTItemContainerTile;
import gtclassic.api.model.GTModelLayeredAnchoredWire;
import gtclassic.api.tile.GTTileBaseCable;
import ic2.core.block.render.model.BlockCopyModel;
import ic2.core.block.wiring.BlockCable;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.platform.registry.Ic2States;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.util.helpers.BlockStateContainerIC2;
import ic2.core.util.misc.StackUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GTBlockBaseCable extends GTBlockBaseConnect implements IGTColorBlock {
    public static final PropertyInteger FOAMED = PropertyInteger.create("foamed", 0, 2);
    public GTBlockBaseCable(){
        this.setHardness(0.2F);
        this.setSoundType(SoundType.CLOTH);
        this.setHarvestLevel("axe", 0);
        setCreativeTab(GTMod.creativeTabGT);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainerIC2(this, active, FOAMED);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof GTTileBaseCable) {
            GTTileBaseCable cable = (GTTileBaseCable)tile;
            return state.withProperty(active, cable.getActive()).withProperty(FOAMED, (int)cable.foamed);
        } else {
            return super.getActualState(state, worldIn, pos);
        }
    }

    @Override
    public IBlockState getDefaultBlockState() {
        IBlockState state = this.getDefaultState().withProperty(active, false).withProperty(FOAMED, 0);

        return state;
    }

    @Override
    public List<IBlockState> getValidStateList() {
        IBlockState def = this.getDefaultState();
        List<IBlockState> states = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            states.add(def.withProperty(active, false).withProperty(FOAMED, i));
            states.add(def.withProperty(active, true).withProperty(FOAMED, i));
        }
        return states;
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        try {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof GTTileBaseCable) {
                GTTileBaseCable cable = (GTTileBaseCable)tile;
                if (cable.foamed > 1) {
                    return new BlockStateContainerIC2.IC2BlockState(state, cable.storage.getQuads());
                }
                return new BlockStateContainerIC2.IC2BlockState(state, cable.getConnections());
            }
        } catch (Exception var6) {
        }

        return super.getExtendedState(state, world, pos);
    }


    public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        return tile instanceof GTTileBaseCable && ((GTTileBaseCable)tile).foamed > 0 ? 255 : 0;
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
        state = this.getActualState(state, worldIn, pos);
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof GTTileBaseCable){
            GTTileBaseCable cable = (GTTileBaseCable) tile;
            if (cable.foamed > 0 && player.isSneaking()){
                cable.changeFoam((byte)0);
                world.markAndNotifyBlock(pos, world.getChunkFromBlockCoords(pos), state, state, world.isRemote ? 11 : 3);
                return false;
            }
        }
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> list = new ArrayList<>();
        TileEntity te = this.getLocalTile() == null ? world.getTileEntity(pos) : this.getLocalTile();
        if (te instanceof IGTItemContainerTile){
            list.addAll(((IGTItemContainerTile) te).getDrops());
            return list;
        }
        return super.getDrops(world, pos, state, fortune);
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return true;
    }

    @Override
    public boolean hasFacing() {
        return false;
    }

    @Override
    public TextureAtlasSprite[] getIconSheet(int i) {
        return null;
    }

    protected int[] getSize(IBlockState state) {
        int var = (16 - getThickness(state)) / 2;
        return new int[] {var, 16 - var };
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BaseModel getModelFromState(IBlockState state) {
        if (state.getValue(FOAMED) == 1) {
            return BlockCopyModel.getFoamModel(Ic2States.constructionFoamCable);
        } else {
            return state.getValue(FOAMED) == 2 ? BlockCopyModel.getFoamModel(Ic2Icons.getTextures("bcable")[195]) : new GTModelLayeredAnchoredWire(state, Ic2Icons.getTextures("bcable")[277], getSize(state));
        }
    }


    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof GTTileBaseCable) {
            int foamed = ((GTTileBaseCable)tile).foamed;
            if (foamed > 0) {
                return BlockFaceShape.SOLID;
            }
        }

        return BlockFaceShape.UNDEFINED;
    }

    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof GTTileBaseCable) {
            GTTileBaseCable cable = (GTTileBaseCable)tile;
            if (cable.foamed > 1) {
                IBlockState state = cable.storage.getEntry(side.getIndex()).getModelState();
                if (state != null) {
                    return state.isSideSolid(world, pos, side);
                }
            }
        }

        return super.isSideSolid(base_state, world, pos, side);
    }

    public abstract int getThickness(IBlockState state);

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (!(tile instanceof GTTileBaseCable)) {
            return new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
        } else {
            GTTileBaseCable cable = (GTTileBaseCable) tile;
            if (cable.foamed > 0) {
                return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            }
            double thickness = getThickness(state) / 32.0D;
            double minX = 0.5D - thickness;
            double minY = 0.5D - thickness;
            double minZ = 0.5D - thickness;
            double maxX = 0.5D + thickness;
            double maxY = 0.5D + thickness;
            double maxZ = 0.5D + thickness;
            if (cable.connection.contains(EnumFacing.WEST) || cable.anchors.contains(EnumFacing.WEST)) {
                minX = 0.0D;
            }
            if (cable.connection.contains(EnumFacing.DOWN) || cable.anchors.contains(EnumFacing.DOWN)) {
                minY = 0.0D;
            }
            if (cable.connection.contains(EnumFacing.NORTH) || cable.anchors.contains(EnumFacing.NORTH)) {
                minZ = 0.0D;
            }
            if (cable.connection.contains(EnumFacing.EAST) || cable.anchors.contains(EnumFacing.EAST)) {
                maxX = 1.0D;
            }
            if (cable.connection.contains(EnumFacing.UP) || cable.anchors.contains(EnumFacing.UP)) {
                maxY = 1.0D;
            }
            if (cable.connection.contains(EnumFacing.SOUTH) || cable.anchors.contains(EnumFacing.SOUTH)) {
                maxZ = 1.0D;
            }
            return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof GTTileBaseCable) {
            GTTileBaseCable cable = (GTTileBaseCable) tile;
            ItemStack stack = playerIn.getHeldItem(hand);
            if (cable.foamed == 1 && GTHelperStack.matchOreDict(stack, "sand")) {
                cable.changeFoam((byte) 2);
                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }

                return true;
            }

            if (cable.foamed == 0 && StackUtil.isStackEqual(stack, Ic2Items.constructionFoam.copy())) {
                cable.changeFoam((byte) 1);
                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }

                return true;
            }

            if (StackUtil.isStackEqual(stack, Ic2Items.miningPipe)) {
                EnumFacing rotation = (new BlockCable.ClickHelper(hitX, hitY, hitZ, (float) this.getThickness(state) / 16)).getFacing(facing);
                if (rotation != null && cable.addAnchor(rotation)) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }

                    return true;
                }
            }
        }
        return false;
    }
}
