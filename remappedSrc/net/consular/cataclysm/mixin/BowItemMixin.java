package net.consular.cataclysm.mixin;

import java.util.List;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

@Mixin(BowItem.class)
public class BowItemMixin extends RangedWeaponItem{

    public BowItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        boolean bl2;
        float f;
        if (!(user instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)user;
        boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        ItemStack itemStack = playerEntity.getProjectileType(stack);
        if (itemStack.isEmpty() && !bl) {
            return;
        }
        if (itemStack.isEmpty()) {
            itemStack = new ItemStack(Items.ARROW);
        }
        if ((double)(f = BowItem.getPullProgress(this.getMaxUseTime(stack) - remainingUseTicks)) < 0.1) {
            return;
        }
        bl2 = bl;
        if (!world.isClient) {
            int k;
            int j;
            ArrowItem arrowItem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
            PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
            persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, f * 3.0f, 1.0f);
            if (f == 1.0f) {
                persistentProjectileEntity.setCritical(true);
            }
            if ((j = EnchantmentHelper.getLevel(Enchantments.POWER, stack)) > 0) {
                persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)j * 0.5 + 0.5 + playerEntity.getAttributeValue(Cataclysm.EntityAttributes.ARROW_DAMAGE));
            }
            if ((k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack)) > 0) {
                persistentProjectileEntity.setPunch(k);
            }
            if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                persistentProjectileEntity.setOnFireFor(100);
            }
            stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
            if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
            world.spawnEntity(persistentProjectileEntity);
        }
        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f);
        if (!bl2 && !playerEntity.getAbilities().creativeMode) {
            itemStack.decrement(1);
            if (itemStack.isEmpty()) {
                playerEntity.getInventory().removeOne(itemStack);
            }
        }
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 15;
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.getItem() == Items.BOW){
            tooltip.add(Text.literal((1.0f) + " Arrow Divergence").formatted(Formatting.BLUE));
            tooltip.add(Text.literal((3.0f) + " Arrow Speed").formatted(Formatting.BLUE));
        }
        
        super.appendTooltip(stack, world, tooltip, context);
    }

}
