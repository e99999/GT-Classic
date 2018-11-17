package gtclassic.items;

import gtclassic.GTClassic;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.obj.IStaticTexturedItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class GTItemComponents extends Item implements IStaticTexturedItem {
    public enum GTItemComponentTypes{
        ENERGY_FLOW_CIRCUIT(0), 
        DATA_CONTROL_CIRCUIT(1), 
        SUPERCONDUCTOR(2), 
        DATA_STORAGE_CIRCUIT(3), 
        COMPUTER_MONITOR(4), 
        CONVEYOR_MODULE(5), 
        BRAINTECH_AEROSPACE_ARDT(6);
        
    	private int id;

        GTItemComponentTypes(int id){
            this.id = id;
        }

        public int getID(){
            return id;
        }
    }

    GTItemComponentTypes variant;
    public GTItemComponents(GTItemComponentTypes variant){
        this.variant = variant;
        setRegistryName(variant.toString().toLowerCase());
        setUnlocalizedName(GTClassic.MODID + "." + variant.toString().toLowerCase());
        setCreativeTab(GTClassic.creativeTabGT);
    }

    @Override
    public List<Integer> getValidVariants() {
        return Arrays.asList(0);
    }

    @Override
    public TextureAtlasSprite getTexture(int i) {
        return Ic2Icons.getTextures("gtclassic_components")[variant.getID()];
    }
}
