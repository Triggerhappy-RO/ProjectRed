package mrtjp.projectred.transmission;

import static mrtjp.projectred.ProjectRedTransmission.itemPartFramedWire;
import static mrtjp.projectred.ProjectRedTransmission.itemPartWire;
import static mrtjp.projectred.ProjectRedTransmission.itemWireDebugger;
import codechicken.microblock.MicroMaterialRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TransmissionClientProxy extends TransmissionProxy {

    @Override
    public void init() {
        super.init();
        for (EnumWire w : EnumWire.VALID_WIRE) {
            LanguageRegistry.addName(w.getItemStack(), w.name);
            if (w.hasFramedForm())
                LanguageRegistry.addName(w.getFramedItemStack(), "Framed " + w.name);
        }
        LanguageRegistry.addName(new ItemStack(itemWireDebugger, 1, 0), "Wire debugger");
        MinecraftForgeClient.registerItemRenderer(itemPartWire.itemID, WireItemRenderer.instance);
        MinecraftForgeClient.registerItemRenderer(itemPartFramedWire.itemID, FramedWireItemRenderer.instance);
        MicroMaterialRegistry.registerHighlightRenderer(new JacketedHighlightRenderer());
    }
}
