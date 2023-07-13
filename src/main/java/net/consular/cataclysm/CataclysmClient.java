package net.consular.cataclysm;

import net.consular.cataclysm.entity.render.HornetRenderer;
import net.consular.cataclysm.entity.render.MagicProjectileEntityRenderer;
import net.consular.cataclysm.entity.render.MagicSwordEntityRenderer;
import net.consular.cataclysm.entity.render.ModArrowEntityRenderer;
import net.consular.cataclysm.entity.render.StingerRenderer;
import net.consular.cataclysm.entity.render.WandOfSparkingEntityRenderer;
import net.consular.cataclysm.registry.ModEntities;
import net.consular.cataclysm.registry.ModItems;
import net.consular.cataclysm.registry.ModScreenHandlers;
import net.consular.cataclysm.screen.BewitchingScreen;
import net.consular.cataclysm.screen.FletchingScreen;
import net.consular.cataclysm.util.EventHandler;
import net.consular.cataclysm.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionUtil;

public class CataclysmClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EventHandler.clientEvents();
        ModModelPredicateProvider.registerModModels();
        HandledScreens.register(ModScreenHandlers.FLETCHING_SCREEN_HANDLER, FletchingScreen::new);
        HandledScreens.register(ModScreenHandlers.BEWITCHING_SCREEN_HANDLER, BewitchingScreen::new);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x3495eb, ModItems.SHULK_CHESTPLATE);

        registerTippedArrow(ModItems.TIPPED_IRON_ARROW);
        registerTippedArrow(ModItems.TIPPED_DIAMOND_ARROW);
        registerTippedArrow(ModItems.TIPPED_NETHERITE_ARROW);
        registerTippedArrow(ModItems.TIPPED_ENDERITE_ARROW);
        registerTippedArrow(ModItems.TIPPED_SCULK_ARROW);
        
        entityRenderers();
    }

    private void entityRenderers(){
        EntityRendererRegistry.register(ModEntities.WAND_OF_SPARKING, WandOfSparkingEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MAGIC_PROJECTILE, MagicProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MAGIC_SWORD, MagicSwordEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOD_ARROW, ModArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.STINGER, StingerRenderer::new);
        EntityRendererRegistry.register(ModEntities.HORNET, HornetRenderer::new);
    }

    private void registerTippedArrow(Item item){
      ColorProviderRegistry.ITEM.register((stack, layer) -> {
        return layer == 0 ? PotionUtil.getColor(stack) : 0xFFFFFF;
      }, item);
    }
}
