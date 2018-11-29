package gtclassic.items.energy;

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
import net.minecraft.item.EnumRarity;
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

public class GTItemZeroPointModule extends ItemBatteryBase {
    
	public GTItemZeroPointModule() 
    {
    	super(0);
        this.setRightClick();
    	this.setRegistryName("zero_point_module");
        this.setUnlocalizedName(GTClassic.MODID + ".zeroPointModule");
        this.setCreativeTab(GTClassic.creativeTabGT);
        this.maxCharge = Integer.MAX_VALUE;
        this.transferLimit = 131072;
        this.tier = 6;
        this.provider = true;
        this.setCreativeTab(GTClassic.creativeTabGT);
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) 
    {
        return 1;
    }

    @Override
    public boolean isDamaged(ItemStack stack) 
    {
        return true;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) 
    {
        return false;
    }

    @Override
    public boolean wantsToPlay(ItemStack stack) 
    {
        return true;
    }

    @Override
    public ResourceLocation createSound(ItemStack stack) 
    {
        return Ic2Sounds.batteryUse;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(ItemStack item) 
    {
        return Ic2Icons.getTextures("gtclassic_items")[55];
    }
    
    @Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.EPIC;
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if (this.isInCreativeTab(tab)) 
		{
			ItemStack full = new ItemStack(this, 1, 0);
			ElectricItem.manager.charge(full, 2.147483647E9D, Integer.MAX_VALUE, true, false);
			items.add(full);
		}
	}
    
}

