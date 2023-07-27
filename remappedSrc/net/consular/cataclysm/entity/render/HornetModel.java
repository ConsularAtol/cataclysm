package net.consular.cataclysm.entity.render;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.entity.HornetEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class HornetModel extends DefaultedEntityGeoModel<HornetEntity> {

    public HornetModel(Identifier assetSubpath) {
        super(new Identifier(Cataclysm.MODID, "hornet"));
    }

}
