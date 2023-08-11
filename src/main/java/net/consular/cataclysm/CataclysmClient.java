package net.consular.cataclysm;

import net.consular.cataclysm.entity.render.HornetRenderer;
import net.consular.cataclysm.entity.render.MagicProjectileEntityRenderer;
import net.consular.cataclysm.entity.render.MagicSwordEntityRenderer;
import net.consular.cataclysm.entity.render.ModArrowEntityRenderer;
import net.consular.cataclysm.entity.render.ModFallingBlockEntityRenderer;
import net.consular.cataclysm.entity.render.StingerRenderer;
import net.consular.cataclysm.entity.render.WandOfSparkingEntityRenderer;
import net.consular.cataclysm.particle.SneakAttackParticle;
import net.consular.cataclysm.registry.ModBlocks;
import net.consular.cataclysm.registry.ModEntities;
import net.consular.cataclysm.registry.ModItems;
import net.consular.cataclysm.registry.ModParticles;
import net.consular.cataclysm.registry.ModScreenHandlers;
import net.consular.cataclysm.screen.BewitchingScreen;
import net.consular.cataclysm.screen.FletchingScreen;
import net.consular.cataclysm.util.EventHandler;
import net.consular.cataclysm.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.potion.PotionUtil;

public class CataclysmClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_MUSHROOM, RenderLayer.getCutout());
		    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_GREEN_MUSHROOM, RenderLayer.getCutout());
		    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_PURPLE_MUSHROOM, RenderLayer.getCutout());

        EventHandler.clientEvents();
        ModModelPredicateProvider.registerModModels();
        HandledScreens.register(ModScreenHandlers.FLETCHING_SCREEN_HANDLER, FletchingScreen::new);
        HandledScreens.register(ModScreenHandlers.BEWITCHING_SCREEN_HANDLER, BewitchingScreen::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.SNEAK_ATTACK, SneakAttackParticle.Factory::new);


        registerTippedArrow(ModItems.TIPPED_IRON_ARROW);
        registerTippedArrow(ModItems.TIPPED_DIAMOND_ARROW);
        registerTippedArrow(ModItems.TIPPED_NETHERITE_ARROW);
        registerTippedArrow(ModItems.TIPPED_ENDERITE_ARROW);
        registerTippedArrow(ModItems.TIPPED_SCULK_ARROW);

        registerDyeable(ModItems.SHULK_HELMET);
        registerDyeable(ModItems.SHULK_CHESTPLATE);
        registerDyeable(ModItems.SHULK_LEGGINGS);
        registerDyeable(ModItems.SHULK_BOOTS);


        entityRenderers();
    }

    private void entityRenderers(){
        EntityRendererRegistry.register(ModEntities.WAND_OF_SPARKING, WandOfSparkingEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MAGIC_PROJECTILE, MagicProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MAGIC_SWORD, MagicSwordEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOD_ARROW, ModArrowEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.STINGER, StingerRenderer::new);
        EntityRendererRegistry.register(ModEntities.HORNET, HornetRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOD_FALLING_BLOCK, ModFallingBlockEntityRenderer::new);
    }

    public static int getColor(ItemStack itemStack) {
		    NbtCompound displayTag = itemStack.getSubNbt("display");
		    if (displayTag != null && displayTag.contains("color", NbtElement.NUMBER_TYPE))
		    	return displayTag.getInt("color");
		    return 0x976797;
	  }

	public static void registerDyeable(Item item) {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : getColor(stack), item);
	}

    private void registerTippedArrow(Item item){
      ColorProviderRegistry.ITEM.register((stack, layer) -> {
        return layer == 0 ? PotionUtil.getColor(stack) : 0xFFFFFF;
      }, item);
    }
}
