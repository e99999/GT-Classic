package gtclassic.items.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.BasicElectricItem;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.ITexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemElectromagnet extends BasicElectricItem implements ITexturedItem {
	
	boolean isActive = false;
	int range = 10;
	double speed = 0.025D;
	double energyCost = 1;
	
	public GTItemElectromagnet() 
    {
		this.maxStackSize = 1;
		this.setRegistryName("electromagnet");
        this.setUnlocalizedName(GTClassic.MODID + ".electroMagnet");
        this.maxCharge = 10000;
        this.transferLimit = 100;
        this.tier = 1;
        this.setCreativeTab(GTClassic.creativeTabGT);
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1.0F, 1.0F);
		if (IC2.platform.isSimulating()) 
		{
			if (!isActive)
			{
				this.isActive = true;
				//turn on magnet
			}
			else 
			{
				this.isActive = false;
				//turn off magnet
			}
			
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(entityIn instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entityIn;
			if(isActive)
			{
				double x = player.posX;
				double y = player.posY + 1.5;
				double z = player.posZ;
				
				List<EntityItem> items = player.world.getEntitiesWithinAABB(EntityItem.class, 
						new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
				
				int pulled = 0;
				for(EntityItem item : items)
					if(canPull(stack))
					{
						if(pulled > 200)
							break;
						item.addVelocity((x - item.posX) * speed, (y - item.posY) * speed, (z - item.posZ) * speed);
						ElectricItem.manager.use(stack, (double)energyCost, (EntityLivingBase)null);
						pulled++;
					}
			}
				
		}
			
	}
	
	public boolean canPull(ItemStack stack) 
    {
        return ElectricItem.manager.canUse(stack, (double)energyCost);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return isActive;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta)
	{
        return Ic2Icons.getTextures("gtclassic_items")[59];
    }

	@Override
    public List<Integer> getValidVariants()
	{
        return Arrays.asList(0);
    }
	
	@Override
	public List<ItemStack> getValidItemVariants()
	{
		return new ArrayList();
	}

	@Override
	public TextureAtlasSprite getTexture(ItemStack var1)
	{
		return null;
	}

	@Override
	public int getTextureEntry(int var1)
	{
		return 0;
	}

	

}
