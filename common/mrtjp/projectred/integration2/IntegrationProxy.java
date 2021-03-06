package mrtjp.projectred.integration2;

import static mrtjp.projectred.ProjectRedIntegration.itemPartGate2;
import mrtjp.projectred.core.IProxy;
import codechicken.lib.packet.PacketCustom;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;

public class IntegrationProxy implements IProxy, IPartFactory {

    @Override
    public void preinit() {
        PacketCustom.assignHandler(IntegrationSPH.channel, new IntegrationSPH());
    }
    
    @Override
    public void init() {
        MultiPartRegistry.registerParts(this, new String[]{
                "pr_sgate", "pr_igate", "pr_agate", "pr_bgate"});

        itemPartGate2 = new ItemPartGate(5678);
    }

    @Override
    public void postinit() {
    }

    @Override
    public TMultiPart createPart(String id, boolean client) {
        if(id.equals("pr_sgate"))
            return new SimpleGatePart();
        if(id.equals("pr_igate"))
            return new InstancedRsGatePart();
        if(id.equals("pr_bgate"))
            return new BundledGatePart();
        if(id.equals("pr_agate"))
            return new ArrayGatePart();
        return null;
    }
}
