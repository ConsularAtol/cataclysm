package net.consular.cataclysm.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.consular.cataclysm.spells.BlankSpell;
import net.consular.cataclysm.spells.MagnesisSpell;
import net.consular.cataclysm.spells.Spell;
import net.consular.cataclysm.util.MagicUser;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
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

public class SpellTomeItem extends Item{

    Spell spell = new BlankSpell();

    public SpellTomeItem(Settings settings, Spell spell){
        super(settings);
        this.spell = spell;
    }

    
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        MagicUser user = (MagicUser) player;
        if (user.getMana() >= spell.getManaCost() && spell.getId() != "blank"){
            player.getItemCooldownManager().set(this, 10);
            if (!player.isCreative())
                user.addMana(-spell.getManaCost());
            spell.cast(world, player, hand);
            player.setCurrentHand(hand);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            player.swingHand(hand, true);
            return TypedActionResult.success(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (this.spell != null) {
            tooltip.add((Text.translatable("spell." + this.spell.getId())).formatted(Formatting.GRAY));
            if (spell.getId() != "blank")
		        tooltip.add(Text.literal("Costs " + spell.getManaCost() + " mana to use").formatted(Formatting.DARK_AQUA));
        }
    }

    @Override
    public String getTranslationKey() {
        return "item.cataclysm.spell_tome";
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (selected)
            spell.tick();
        else if (spell.getId() == "magnesis")
            ((MagnesisSpell)spell).dropBlock();
    }
}
