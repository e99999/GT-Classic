package gtclassic.items.tools;

import java.util.Arrays;
import java.util.List;

import gtclassic.GTClassic;
import ic2.api.item.ElectricItem;
import ic2.core.audio.AudioSource;
import ic2.core.item.base.ItemElectricTool;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemTeslaStaff extends ItemElectricTool implements IStaticTexturedItem {

	//public int soundTicker;
	//public AudioSource sound;

	public GTItemTeslaStaff() {
		super(0.0F, 2.0F, ToolMaterial.IRON);
		//this.func_77627_a(true);
		//this.func_77656_e(0);
		//this.field_77865_bY = 1.0F;
		//this.soundTicker = 0;
		this.attackDamage = 1.0F;
		this.maxCharge = 10000000;
		this.transferLimit = 2048;
		this.operationEnergyCost = 10000000;
		this.tier = 4;
		this.setRegistryName("tesla_staff");
		this.setUnlocalizedName(GTClassic.MODID + ".teslaStaff");
		this.setCreativeTab(GTClassic.creativeTabGT);
	}
	
	 	@Override
	    public List<Integer> getValidVariants() {
	        return Arrays.asList(0);
	    }

	    @Override
	    @SideOnly(Side.CLIENT)
	    public TextureAtlasSprite getTexture(int meta) {
	        return Ic2Icons.getTextures("gtclassic_items")[47];
	    }

	    public EnumEnchantmentType getType(ItemStack item) {
			return EnumEnchantmentType.WEAPON;
		}
	    
	    @Override
	    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
	        if (!(attacker instanceof EntityPlayer)) {
	            return true;
	        } else {
	            if (ElectricItem.manager.use(stack, (double)this.operationEnergyCost, (EntityPlayer)attacker)) {
	                target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)attacker), 1000.0F);
	            } else {
	                target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)attacker), 1.0F);
	            }

	            return false;
	        }
	    }
	    
	    

}
