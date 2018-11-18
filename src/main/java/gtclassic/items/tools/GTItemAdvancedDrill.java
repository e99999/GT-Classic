package gtclassic.items.tools;

import com.google.common.collect.ImmutableSet;
import gtclassic.GTClassic;
import ic2.api.classic.item.IMiningDrill;
import ic2.api.classic.item.IScannerItem;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.item.base.ItemElectricTool;
import ic2.core.platform.lang.storage.Ic2InfoLang;
import ic2.core.platform.registry.Ic2Lang;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import ic2.core.util.helpers.AabbUtil;
import ic2.core.util.misc.StackUtil;
import ic2.core.util.obj.IBootable;
import ic2.core.util.obj.ToolTipType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class GTItemAdvancedDrill extends ItemElectricTool implements IStaticTexturedItem, IBootable, IMiningDrill {
    public GTItemAdvancedDrill(){
        super(0.0F, -3.0F, ToolMaterial.DIAMOND);
        this.setRegistryName("advanced_drill");
        this.hasSubtypes = true;
        this.setUnlocalizedName(GTClassic.MODID + ".advancedDrill");
        this.attackDamage = 8.0F;
        this.maxCharge = 100000;
        this.transferLimit = 150;
        this.tier = 3;
        this.setCreativeTab(GTClassic.creativeTabGT);
    }

    @Override
    public void onLoad() {
    	//required for construction
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return Items.DIAMOND_PICKAXE.canHarvestBlock(state) || Items.DIAMOND_SHOVEL.canHarvestBlock(state);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
        return 3;
    }

    @Override
    public int getEnergyCost(ItemStack stack) {
         return 200;  
    }

    @Override
    public float getMiningSpeed(ItemStack stack) {
        return 16.0F;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "shovel");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
            if (IC2.keyboard.isModeSwitchKeyDown(playerIn)) {
                if (IC2.platform.isRendering()) {
                    return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
                }

                NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
                boolean result = !nbt.getBoolean("dirtMode");
                nbt.setBoolean("dirtMode", result);
                IC2.platform.messagePlayer(playerIn, result ? Ic2InfoLang.enableDrillMode : Ic2InfoLang.disableDrillMode);
                return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
            }

            if (IC2.keyboard.isAltKeyDown(playerIn) && !this.getScanner(playerIn.inventory).isEmpty()) {
                return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
            }
        

        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            IC2.achievements.issueStat((EntityPlayer)entityLiving, "blocksDrilled");
        }

        return super.onBlockDestroyed(stack, worldIn, blockIn, pos, entityLiving);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (IC2.keyboard.isAltKeyDown(player)) {
            ItemStack scanner = this.getScanner(player.inventory);
            if (IC2.platform.isRendering()) {
                return scanner != null ? EnumActionResult.SUCCESS : EnumActionResult.PASS;
            }

            if (scanner != null) {
                IScannerItem item = (IScannerItem)scanner.getItem();
                int range = item.startLayerScan(scanner);
                ElectricItem.manager.chargeFromArmor(scanner, player);
                if (range > 0) {
                    EnumFacing.Axis axis = facing.getAxis();
                    AabbUtil.BoundingBox box = new AabbUtil.BoundingBox(pos);
                    EnumFacing.Axis[] var14 = EnumFacing.Axis.values();
                    int var15 = var14.length;

                    for(int var16 = 0; var16 < var15; ++var16) {
                        EnumFacing.Axis iter = var14[var16];
                        if (axis != iter) {
                            box.expandAxis(iter, 1);
                        }
                    }

                    box.expand(facing.getOpposite(), 3 + range);
                    double found = 0.0D;
                    double value = 0.0D;

                    for(Iterator var18 = box.iterator(); var18.hasNext(); ++found) {
                        BlockPos check = (BlockPos)var18.next();
                        value += (double)item.getOreValue(scanner, worldIn.getBlockState(check));
                    }
                    if (found != 0) {
                    IC2.platform.messagePlayer(player, Ic2InfoLang.drillProbeResult.getLocalizedFormatted(new Object[]{3 + range, (int)(value / found * 1000.0D)}));
                    return EnumActionResult.SUCCESS;
                    	}
                    }
            	}
        	}

        return EnumActionResult.PASS;
    }

    public ItemStack getScanner(InventoryPlayer player) {
        ItemStack result = ItemStack.EMPTY;

        for(int i = 0; i < 9; ++i) {
            ItemStack stack = player.getStackInSlot(i);
            if (stack != null && stack.getItem() instanceof IScannerItem) {
                result = stack;
                break;
            }
        }

        if (result.isEmpty()) {
            ItemStack stack = player.player.getHeldItemOffhand();
            if (stack != null && stack.getItem() instanceof IScannerItem) {
                result = stack;
            }
        }

        return result;
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta) {
        return Ic2Icons.getTextures("gtclassic_items")[60];
    }

    @Override
    public EnumEnchantmentType getType(ItemStack itemStack) {
        return EnumEnchantmentType.DIGGER;
    }

    @Override
    public void onSortedItemToolTip(ItemStack stack, EntityPlayer player, boolean debugTooltip, List<String> tooltip, Map<ToolTipType, List<String>> sortedTooltip) {
            NBTTagCompound nbt = StackUtil.getNbtData(stack);
            tooltip.add((nbt.getBoolean("dirtMode") ? Ic2InfoLang.enableDrillMode : Ic2InfoLang.disableDrillMode).getLocalized());
            if (nbt.getBoolean("Rockcutter")) {
                tooltip.add(Ic2InfoLang.drillRockCutter.getLocalized());
            }

            List<String> ctrlTip = (List)sortedTooltip.get(ToolTipType.Ctrl);
            ctrlTip.add(Ic2Lang.onItemRightClick.getLocalized());
            ctrlTip.add(Ic2Lang.pressTo.getLocalizedFormatted(new Object[]{IC2.keyboard.getKeyName(2), Ic2InfoLang.drillModeSwitch.getLocalized()}));
            ctrlTip.add("");
            ctrlTip.add(Ic2Lang.onBlockClick.getLocalized());
            ctrlTip.add(Ic2Lang.pressTo.getLocalizedFormatted(new Object[]{IC2.keyboard.getKeyName(0), Ic2InfoLang.drillProbing.getLocalized()}));
     
    }

    @Override
    public boolean isBasicDrill(ItemStack d) {
        return !d.isItemEnchantable();
    }

    @Override
    public int getExtraSpeed(ItemStack d) {
        int pointBoost = this.getPointBoost(d);
        return 0 + pointBoost;
    }

    private int getPointBoost(ItemStack drill) {
        int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, drill);
        return lvl <= 0 ? 0 : lvl * lvl + 1;
    }

    @Override
    public int getExtraEnergyCost(ItemStack d) {
        int points = this.getEnergyChange(d);
        return points > 0 ? points : 0;
    }

    public int getEnergyChange(ItemStack drill) {
        int eff = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, drill);
        int unb = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, drill);
        int points = eff * eff + 1;
        points -= unb * (unb + unb);
        return points;
    }

    @Override
    public void useDrill(ItemStack d) {
        ElectricItem.manager.use(d, (double)this.getEnergyCost(d), (EntityLivingBase)null);
    }

    @Override
    public boolean canMine(ItemStack d) {
        return ElectricItem.manager.canUse(d, (double)this.getEnergyCost(d));
    }

    @Override
    public boolean canMineBlock(ItemStack d, IBlockState state, IBlockAccess access, BlockPos pos) {
        return ForgeHooks.canToolHarvestBlock(access, pos, d);
    }
}
