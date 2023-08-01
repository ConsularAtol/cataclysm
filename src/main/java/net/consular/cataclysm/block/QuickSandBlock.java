package net.consular.cataclysm.block;

import java.util.Optional;

import net.consular.cataclysm.registry.ModBlocks;
import net.consular.cataclysm.registry.ModDamageSources;
import net.consular.cataclysm.registry.ModItems;
import net.consular.cataclysm.registry.ModTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;


public class QuickSandBlock extends Block
implements FluidDrainable {
    private static final VoxelShape FALLING_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.9f, 1.0);

    public QuickSandBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.1f, 0.1, 0.1f));
            if (world.isClient) {
                boolean bl;
                Random random = world.getRandom();
                bl = entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ();
                if (bl && random.nextBoolean()) {
                    world.addParticle(new DustParticleEffect(Vec3d.unpackRgb(0xffe3ab).toVector3f(), 1f), entity.getX(), pos.getY() + 1, entity.getZ(), MathHelper.nextBetween(random, -1.0f, 1.0f) * 0.083333336f, 0.05f, MathHelper.nextBetween(random, -1.0f, 1.0f) * 0.083333336f);
                }
            }
        }
        if (!world.isClient) {
            if (world.getBlockState(new BlockPos(entity.getBlockX(), entity.getBlockY() + 1, entity.getBlockZ())) == ModBlocks.QUICK_SAND.getDefaultState()){
                entity.damage(ModDamageSources.getSource(entity.getDamageSources(), ModDamageSources.QUICK_SAND), 1);
            }
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if ((double)fallDistance < 4.0 || !(entity instanceof LivingEntity)) {
            return;
        }
        LivingEntity livingEntity = (LivingEntity)entity;
        LivingEntity.FallSounds fallSounds = livingEntity.getFallSounds();
        SoundEvent soundEvent = (double)fallDistance < 7.0 ? fallSounds.small() : fallSounds.big();
        entity.playSound(soundEvent, 1.0f, 1.0f);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Entity entity;
        if (context instanceof EntityShapeContext && (entity = ((EntityShapeContext)context).getEntity()) != null) {
            if (entity.fallDistance > 2.5f) {
                return FALLING_SHAPE;
            }
            boolean bl = entity instanceof FallingBlockEntity;
            if (bl || QuickSandBlock.canWalkOnQuickSand(entity) && context.isAbove(VoxelShapes.fullCube(), pos, false) && !context.isDescending()) {
                return super.getCollisionShape(state, world, pos, context);
            }
        }
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public static boolean canWalkOnQuickSand(Entity entity) {
        if (entity.getType().isIn(ModTags.CAN_WALK_ON_QUICKSAND)) {
            return true;
        }
        if (entity instanceof LivingEntity) {
            return ((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS);
        }
        return false;
    }

    @Override
    public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
        if (!world.isClient()) {
            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));
        }
        return new ItemStack(ModItems.QUICKSAND_BUCKET);
    }

    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL_POWDER_SNOW);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }
}
