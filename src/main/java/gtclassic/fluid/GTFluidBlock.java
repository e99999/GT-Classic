package gtclassic.fluid;

import java.util.List;

import javax.annotation.Nonnull;

import gtclassic.GTMod;
import gtclassic.helpers.GTHelperWorld;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import ic2.core.item.armor.standart.ItemHazmatArmor;
import ic2.core.platform.lang.ILocaleBlock;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTFluidBlock extends BlockFluidClassic implements ILocaleBlock, ICustomModeledBlock {

	LocaleComp comp;
	GTMaterial mat;

	public GTFluidBlock(GTMaterial mat) {
		super(FluidRegistry.getFluid(mat.getDisplayName().toLowerCase()), getMaterialType(mat));
		setRegistryName(mat.getDisplayName().toLowerCase() + "_fluidblock");
		setUnlocalizedName(GTMod.MODID + "." + mat.getDisplayName().toLowerCase() + "_fluidblock");
		this.mat = mat;
		this.comp = Ic2Lang.nullKey;
	}

	public static Material getMaterialType(GTMaterial mat) {
		return mat.hasFlag(GTMaterialFlag.GAS) ? GTFluidMaterial.GAS : Material.WATER;
	}

	public LocaleComp getName() {
		return this.comp;
	}

	public Block setUnlocalizedName(LocaleComp name) {
		this.comp = name;
		return super.setUnlocalizedName(name.getUnlocalized());
	}

	@Override
	public Block setUnlocalizedName(String name) {
		this.comp = new LocaleBlockComp("tile." + name);
		return super.setUnlocalizedName(name);
	}

	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BaseModel getModelFromState(IBlockState state) {
		return new GTFluidModel(FluidRegistry.getFluid(mat.getDisplayName().toLowerCase()));
	}

	@Override
	public IBlockState getStateFromStack(ItemStack stack) {
		return this.getDefaultState();
	}

	public GTMaterial getMaterial() {
		return this.mat;
	}
	
	@Override
    public boolean shouldSideBeRendered(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EnumFacing side)
    {
        IBlockState neighbor = world.getBlockState(pos.offset(side));
        if (neighbor.getBlock() == state.getBlock())
        {
            return false;
        }
        if (side == (densityDir < 0 ? EnumFacing.UP : EnumFacing.DOWN))
        {
            return true;
        }
        return super.shouldSideBeRendered(state, world, pos, side);
    }

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!this.getMaterial().equals(GTMaterial.Oxygen)) {
			if (entityIn instanceof EntityLivingBase) {
				EntityLivingBase entity = (EntityLivingBase) entityIn;
				if (this.getMaterial().hasFlag(GTMaterialFlag.GAS)) {
					entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 20, 0, false, false));
				}
				if (!ItemHazmatArmor.isFullHazmatSuit(entity)) {
					entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0, false, false));
					entity.attackEntityFrom(DamageSource.GENERIC, 1.0F);
				}
				if (GTMaterial.isFlammible(this.getMaterial()) && GTHelperWorld.entityHasFlammible(entity)) {
					worldIn.createExplosion(entity, pos.getX(), pos.getY(), pos.getZ(), 2.0F, true);
				}
			}
		}
	}

	
}
