package net.consular.cataclysm.world.gen;

import net.consular.cataclysm.world.feature.ModOreGeneration;

public class ModWorldGeneration {
    public static void generateModWorldGen(){
        ModOreGeneration.generateOres();
        ModTreeGeneration.generateTrees();
    }
}
