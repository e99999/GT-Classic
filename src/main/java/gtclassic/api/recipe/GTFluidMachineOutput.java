package gtclassic.api.recipe;

import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.core.item.misc.ItemDisplayIcon;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class GTFluidMachineOutput extends MachineOutput {
    protected List<FluidStack> fluids;
    public GTFluidMachineOutput(NBTTagCompound meta, List<ItemStack> items, List<FluidStack> fluids) {
        super(meta, items);
        assert !fluids.contains(null);
        this.fluids = fluids;
    }

    public GTFluidMachineOutput(NBTTagCompound meta, ItemStack[] items, List<FluidStack> fluids) {
        super(meta, items);
        assert !fluids.contains(null);
        this.fluids = fluids;
    }

    public GTFluidMachineOutput(NBTTagCompound meta, List<FluidStack> fluids) {
        this(meta, new ItemStack[]{(ItemDisplayIcon.createWithFluidStack(new FluidStack(FluidRegistry.WATER, 1000)))}, fluids);
    }

    public List<FluidStack> getFluids() {
        return fluids;
    }

    @Override
    public MachineOutput copy()
    {
        return new GTFluidMachineOutput(copyNBT(metadata), copyItems(items), copyFluids(fluids));
    }

    @Override
    public MachineOutput overrideOutput(List<ItemStack> list)
    {
        if(!canOverride())
        {
            return copy();
        }
        return new GTFluidMachineOutput(copyNBT(metadata), copyItems(list), copyFluids(fluids));
    }

    public MachineOutput overrideFluidOutput(List<FluidStack> list)
    {
        if(!canOverride())
        {
            return copy();
        }
        return new GTFluidMachineOutput(copyNBT(metadata), copyItems(items), copyFluids(list));
    }

    protected List<FluidStack> copyFluids(List<FluidStack> list)
    {
        List<FluidStack> newList = new ArrayList<FluidStack>(list.size());
        for(FluidStack fluid : list)
        {
            newList.add(fluid.copy());
        }
        return newList;
    }
}
