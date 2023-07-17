package net.consular.cataclysm.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.consular.cataclysm.spells.Spell;
import net.consular.cataclysm.util.MagicUser;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ScrollItem extends Item{

    Spell spell;

    public ScrollItem(Settings settings, Spell spell) {
        super(settings);
        this.spell = spell;
    }

    public Spell getSpell(){
        return spell;
    }
    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        MagicUser user = (MagicUser) player;
        if (user.getMana() >= spell.getManaCost()){
            player.getItemCooldownManager().set(this, 10);
            if (!player.isCreative())
                user.addMana(-spell.getManaCost());
            spell.cast(world, player, hand);
            player.setCurrentHand(hand);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!player.isCreative())
                itemStack.setCount(itemStack.getCount() - 1);
            player.swingHand(hand, true);
            return TypedActionResult.success(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(((MutableText) spell.getName()).formatted(Formatting.GRAY));    
        tooltip.add(Text.literal("Costs " + spell.getManaCost() + " mana to use").formatted(Formatting.DARK_AQUA));
	}

    @Override
    public String getTranslationKey() {
        return "item.cataclysm.scroll";
    }

}
