package gtclassic.blocks.cabinet;

import gtclassic.GTClassic;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class GTBlockCabinet extends Block implements ITileEntityProvider {

    public static final int GUI_ID = 1;

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    
    public GTBlockCabinet() {
        super(Material.IRON);
        setRegistryName("cabinet_block"); //texture
        setUnlocalizedName(GTClassic.MODID + ".blockCabinet"); //lang
        setCreativeTab(GTClassic.creativeTabGT);
        setHardness(10.0F);
        setResistance(40.0F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
    }

    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector(
             (float) (entity.posX - clickedBlock.getX()),
             (float) (entity.posY - clickedBlock.getY()),
             (float) (entity.posZ - clickedBlock.getZ()));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCabinet();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof TileEntityCabinet)) {
            return false;
        }
        player.openGui(GTClassic.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
    
    //code below gives items back after breaking the blocks
    public void breakBlock( World worldIn, BlockPos pos, IBlockState state ){
        TileEntity te = worldIn.getTileEntity( pos );
        if( te instanceof TileEntityCabinet ){
            ItemStackHandler ish = (ItemStackHandler)te.getCapability( CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP );
            for( int i=0; i<ish.getSlots(); i++ ){
                if( ish.getStackInSlot( i ) != null ){
                    worldIn.spawnEntity( new EntityItem( worldIn, pos.getX(), pos.getY(), pos.getZ(), ish.getStackInSlot( i ) ) );
                }
            }
        }

        super.breakBlock(worldIn, pos, state);
    }
}
