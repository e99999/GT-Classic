package gtclassic.items.tools;

import gtclassic.GTClassic;
import ic2.api.classic.item.IEUReader;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.block.base.tile.TileEntityElecMachine;
import ic2.core.item.base.ItemBatteryBase;
import ic2.core.platform.registry.Ic2Sounds;
import ic2.core.platform.textures.Ic2Icons;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDebugScanner extends ItemBatteryBase implements IEUReader {
    public GTItemDebugScanner() {
    	super(0);
        this.setRightClick();
    	this.setRegistryName("debug_scanner");
        this.setUnlocalizedName(GTClassic.MODID + ".debugScanner");
        this.setCreativeTab(GTClassic.creativeTabGT);
        this.maxCharge = 1000000000;
        this.transferLimit = 1000;
        this.tier = 1;
        this.provider = true;
        this.setCreativeTab(GTClassic.creativeTabGT);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
    	if (!IC2.platform.isSimulating()) {
			return EnumActionResult.PASS;
		} else {

		TileEntity tileEntity = world.getTileEntity(pos);
		IBlockState state = world.getBlockState(pos);
		
		IC2.platform.messagePlayer(player,"-=={ "+player.getDisplayNameString()+" }==-");
		IC2.platform.messagePlayer(player, state.getBlock().toString());
		IC2.platform.messagePlayer(player, player.getPosition().toString());
		
		if (tileEntity instanceof TileEntityBlock) {
			TileEntityBlock te = (TileEntityBlock) tileEntity;
			IC2.platform.messagePlayer(player,"Active=" + te.getActive());
			IC2.platform.messagePlayer(player,"Facing=" + te.getFacing());
		}
		
		if (tileEntity instanceof TileEntityElecMachine) {
			TileEntityElecMachine te3 = (TileEntityElecMachine) tileEntity;
			IC2.platform.messagePlayer(player, "Tier=" + te3.tier);
			IC2.platform.messagePlayer(player, "Energy:" + te3.energy);
			IC2.platform.messagePlayer(player, "Max Input=" + te3.maxInput);
			IC2.platform.messagePlayer(player, "Max Energy=" + te3.maxEnergy);
		}
		
		IC2.platform.messagePlayer(player, "Now get back to work!");
		return EnumActionResult.SUCCESS;
		}
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {
        return true;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public boolean wantsToPlay(ItemStack stack) {
        return true;
    }

    @Override
    public ResourceLocation createSound(ItemStack stack) {
        return Ic2Sounds.batteryUse;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(ItemStack item) {
        return Ic2Icons.getTextures("gtclassic_items")[55];
    }

	@Override
	public boolean isEUReader(ItemStack var1) {
		return true;
	}
	
	@Override
	public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			ItemStack full = new ItemStack(this, 1, 0);
			ElectricItem.manager.charge(full, 2.147483647E9D, Integer.MAX_VALUE, true, false);
			items.add(full);
		}
	}
    
}

