package net.consular.cataclysm.item;

import java.util.List;

import javax.annotation.Nullable;

import net.consular.cataclysm.entity.MagicSwordEntity;
import net.consular.cataclysm.util.MagicUser;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MagicSwordItem extends SwordItem{

    public MagicSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    private static Integer manaCost(){
        return 4;
    }

    @Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.literal("Mana Cost: " + manaCost()).formatted(Formatting.DARK_AQUA));
	}

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        MagicUser user = (MagicUser) attacker;
        user.addMana(1);
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        MagicUser user = (MagicUser) player;
        if (user.getMana() >= manaCost()){
            player.getItemCooldownManager().set(this, 5);
            user.addMana(-manaCost());
            player.setCurrentHand(hand);
            MagicSwordEntity sword = new MagicSwordEntity(player, world);
		    sword.setVelocity(player, player.getPitch(), player.getYaw(), player.getRoll(), 2F, 0F);
		    world.spawnEntity(sword);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            itemStack.damage(1, player, p -> p.sendToolBreakStatus(hand));
            player.swingHand(hand, true);
            return TypedActionResult.success(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }
}
