package gtclassic.common.block;

import java.util.List;

import gtclassic.api.block.GTBlockBaseDataNode;
import gtclassic.common.GTBlocks;
import gtclassic.common.tile.datanet.GTTileNetworkEnergizer;
import gtclassic.common.tile.datanet.GTTileDigitizerFluid;
import gtclassic.common.tile.datanet.GTTileDigitizerItem;
import gtclassic.common.tile.datanet.GTTileNetworkManager;
import gtclassic.common.tile.datanet.GTTileReconstructorFluid;
import gtclassic.common.tile.datanet.GTTileReconstructorItem;
import ic2.core.block.base.tile.TileEntityBlock;
import ic2.core.platform.lang.components.base.LocaleComp;
import net.minecraft.block.SoundType;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GTBlockDataNode extends GTBlockBaseDataNode {

	String shortName;

	public GTBlockDataNode(String name, int id, LocaleComp comp) {
		super(id);
		this.shortName = name;
		setUnlocalizedName(comp);
		setRegistryName(name);
		this.setHardness(0.2F);
		this.setSoundType(SoundType.CLOTH);
		this.setHarvestLevel("axe", 0);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		for (int i = 0; i < 4; i++) {
			tooltip.add(I18n.format(this.getUnlocalizedName().replace("tile", "tooltip") + i));
		}
	}

	@Override
	public TileEntityBlock createNewTileEntity(World worldIn, int meta) {
		if (this == GTBlocks.tileReconstructorItem) {
			return new GTTileReconstructorItem();
		}
		if (this == GTBlocks.tileReconstructorFluid) {
			return new GTTileReconstructorFluid();
		}
		if (this == GTBlocks.tileNetworkEnergizer) {
			return new GTTileNetworkEnergizer();
		}
		if (this == GTBlocks.tileDigitizerItem) {
			return new GTTileDigitizerItem();
		}
		if (this == GTBlocks.tileDigitizerFluid) {
			return new GTTileDigitizerFluid();
		}
		if (this == GTBlocks.tileNetworkManager) {
			return new GTTileNetworkManager();
		}
		return new GTTileReconstructorItem();
	}

	public String getShortName() {
		return this.shortName;
	}
}
