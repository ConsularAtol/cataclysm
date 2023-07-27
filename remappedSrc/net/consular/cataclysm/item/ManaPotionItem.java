package net.consular.cataclysm.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.consular.cataclysm.registry.ModEffects;
import net.consular.cataclysm.util.MagicUser;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class ManaPotionItem extends Item{

    Integer revoveryAmount;

    public ManaPotionItem(Item.Settings settings, Integer amount) {
        super(settings);
        revoveryAmount = amount;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            stack.decrement(1);
        }
        if (!world.isClient() && user instanceof PlayerEntity player && player instanceof MagicUser magicUser) {
            magicUser.addMana(revoveryAmount);
        }
        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        if (Math.random() < (revoveryAmount / 100)){
            user.addStatusEffect(new StatusEffectInstance(ModEffects.DRAINED, 20 * 5, 0), user);
        }
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.literal("Recovers " + revoveryAmount + " mana").formatted(Formatting.DARK_AQUA));
	}
    
}
