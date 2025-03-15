package gtclassic.common.item;

import java.util.LinkedHashMap;
import java.util.Map;

import gtclassic.api.interfaces.IGTDebuggableTile;
import gtclassic.api.interfaces.IGTMultiTileStatus;
import gtclassic.api.world.GTBedrockOreHandler;
import gtclassic.common.GTConfig;
import ic2.api.classic.item.IEUReader;
import ic2.api.classic.tile.machine.IProgressMachine;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySink;
import ic2.core.IC2;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class GTItemMagnifyingGlass extends GTItemComponent implements IEUReader {

	public GTItemMagnifyingGlass() {
		super("magnifying_glass", 3, 2);
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ, EnumHand hand) {
		Block block = world.getBlockState(pos).getBlock();
		if (player.isSneaking() || hand == EnumHand.OFF_HAND) {
			return EnumActionResult.PASS;
		}
		if (IC2.platform.isRendering()) {
			return EnumActionResult.SUCCESS;
		}
		IBlockState state = world.getBlockState(pos);
		IC2.platform.messagePlayer(player,TextFormatting.YELLOW + "" + state.getBlock().getLocalizedName());
		TileEntity tileEntity = world.getTileEntity(pos);
		//tell us about the biome for people looking for ores
		if (tileEntity == null) {
			StringBuilder result = new StringBuilder();
			Biome biome = world.getBiome(pos);	
			for (Type types : BiomeDictionary.getTypes(biome)) {
				result.append(types.toString() + " ");
			}
			IC2.platform.messagePlayer(player, "This is a " + result.toString() + "biome");
		}
		if (GTConfig.general.enableMagnifyingGlassGivesEUTooltips && tileEntity instanceof IEnergySink) {
			IEnergySink euSink = (IEnergySink) tileEntity;
			IC2.platform.messagePlayer(player, "Input Tier: " + euSink.getSinkTier());
			IC2.platform.messagePlayer(player, "Input Max: " + EnergyNet.instance.getPowerFromTier(euSink.getSinkTier())
					+ " EU");
		}
		if (tileEntity instanceof IProgressMachine) {
			IProgressMachine progress = (IProgressMachine) tileEntity;
			IC2.platform.messagePlayer(player, TextFormatting.ITALIC + "Progress: "
					+ +(Math.round((progress.getProgress() / progress.getMaxProgress()) * 100)) + "%");
		}
		if (tileEntity instanceof IGTMultiTileStatus) {
			IGTMultiTileStatus multi = (IGTMultiTileStatus) tileEntity;
			boolean isValid = multi.getStructureValid();
			IC2.platform.messagePlayer(player, (isValid ? TextFormatting.GREEN : TextFormatting.RED) +  "Correct Strucuture: " + isValid);
		}
		if (tileEntity instanceof IGTDebuggableTile) {
			LinkedHashMap<String, Boolean> data = new LinkedHashMap<>();
			IGTDebuggableTile debug = (IGTDebuggableTile) tileEntity;
			debug.getData(data);
			for (Map.Entry<String, Boolean> entry : data.entrySet()) {
				if (!entry.getValue()) {
					IC2.platform.messagePlayer(player, entry.getKey());
				}
			}
		}
		if (GTBedrockOreHandler.isBedrockOre(block)) {
			ItemStack resource = GTBedrockOreHandler.getResource(block);
			String amount = resource.getCount() > 1 ? " x " + resource.getCount() : "";
			IC2.platform.messagePlayer(player, "Contains: " + GTBedrockOreHandler.getResource(block).getDisplayName()
					+ amount);
		}
		world.playSound(null, player.getPosition(), SoundEvents.ENTITY_VILLAGER_AMBIENT, SoundCategory.PLAYERS, 1.0F, 1.0F);
		return EnumActionResult.SUCCESS;
	}

	@Override
	public boolean isEUReader(ItemStack var1) {
		return GTConfig.general.enableMagnifyingGlassGivesEUTooltips;
	}
}
