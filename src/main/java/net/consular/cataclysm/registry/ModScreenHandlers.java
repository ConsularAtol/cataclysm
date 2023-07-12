package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.consular.cataclysm.screen.BewitchingScreenHandler;
import net.consular.cataclysm.screen.FletchingScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<FletchingScreenHandler> FLETCHING_SCREEN_HANDLER;
    public static ScreenHandlerType<BewitchingScreenHandler> BEWITCHING_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        FLETCHING_SCREEN_HANDLER =
        // This is depracated, but for some reason using the new method causes a server crash.
                ScreenHandlerRegistry.registerSimple(new Identifier(Cataclysm.MODID, "fletching_table"),
                        FletchingScreenHandler::new);
        BEWITCHING_SCREEN_HANDLER =
        // This is depracated, but for some reason using the new method causes a server crash.
                ScreenHandlerRegistry.registerSimple(new Identifier(Cataclysm.MODID, "bewitching_table"),
                        BewitchingScreenHandler::new);
        
        //BEWITCHING_SCREEN_HANDLER = new ScreenHandlerType<>(BewitchingScreenHandler::new);
    }
}
