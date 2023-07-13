package net.consular.cataclysm.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.consular.cataclysm.util.MagicUser;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MagicItem extends Item {

    public MagicItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        MagicUser user = (MagicUser) player;
        if (user.getMana() >= getManaCost()){
            player.getItemCooldownManager().set(this, 10);
            user.addMana(-getManaCost());
            cast(world, player, hand);
            player.setCurrentHand(hand);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            itemStack.damage(1, player, p -> p.sendToolBreakStatus(hand));
            player.swingHand(hand, true);
            return TypedActionResult.success(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    public void cast(World world, PlayerEntity player, Hand hand){
        
    }

    @Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.literal("Costs " + getManaCost() + " mana to use").formatted(Formatting.DARK_AQUA));
	}
    
    public int getManaCost(){
        return 0;
    }
}
