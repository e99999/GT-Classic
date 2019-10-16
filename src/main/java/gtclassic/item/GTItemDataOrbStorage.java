package gtclassic.item;

import java.util.List;

import ic2.core.util.misc.StackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTItemDataOrbStorage extends GTItemComponent {

	public GTItemDataOrbStorage() {
		super("data_orb_storage", 11, 0);
		this.maxStackSize = 1;
		setCreativeTab(null);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt = StackUtil.getNbtData(stack);
		NBTTagList list = nbt.getTagList("Items", 10);
		if (list.hasNoTags()) {
			tooltip.add(I18n.format("No Data Stored... How did you get this?"));
			return;
		}
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound data = list.getCompoundTagAt(i);
			ItemStack contained = new ItemStack(data);
			tooltip.add(I18n.format(contained.getDisplayName() + " x" + contained.getCount()));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
