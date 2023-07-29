package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class ModGamerules {
    public static GameRules.Key<GameRules.BooleanRule> CATACLYSM_MODE;

    public static void registerGamerules(){
        CATACLYSM_MODE = GameRuleRegistry.register("cataclysmMode", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

        Cataclysm.LOGGER.info("Registering Gamerules");
    }
}
