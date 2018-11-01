package gtclassic.items;

import gtclassic.ModCore;
import gtclassic.ModBlocks;
import gtclassic.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCreditAlk extends Item {

    //basic information about this item
	public ItemCreditAlk() {
		
		this.maxStackSize = 1;
		this.setMaxDamage(64);
		setRegistryName("alk_credit");      
        setUnlocalizedName(ModCore.MODID + ".creditAlk");
        setCreativeTab(ModItems.tabGTClassic);
    }
    
    //init texture
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
    
    @Override
	public EnumRarity getRarity(ItemStack thisItem) {
		return EnumRarity.EPIC;
	}
    
    @Override
	public EnumActionResult onItemUse(EntityPlayer entity, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY,
			float hitZ) {
		pos = pos.offset(facing);
		ItemStack itemstack = entity.getHeldItem(hand);
		if (!entity.canPlayerEdit(pos, facing, itemstack)) {
			return EnumActionResult.FAIL;
		} else {
			if (world.isAirBlock(pos)) {
				world.playSound(entity, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				ModBlocks.portal.trySpawnPortal(world, pos);
			}
			itemstack.damageItem(1, entity);
			return EnumActionResult.SUCCESS;
		}

    }
}

