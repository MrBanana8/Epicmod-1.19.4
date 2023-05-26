package me.notbanana8.epicmod.block;

import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.ModEntities;
import me.notbanana8.epicmod.entity.custom.KarinSpawnEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.function.MaterialPredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;


public class SummoningBlock extends HorizontalFacingBlock
        implements Equipment {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final Predicate<BlockState> IS_KARIN_HEAD_PREDICATE = state -> state != null && (state.isOf(ModBlocks.KARIN_SUMMONING_BLOCK));
    @Nullable
    private BlockPattern karinPattern;
    @Nullable
    private BlockPattern karinFloorPattern;

    public SummoningBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (oldState.isOf(state.getBlock())) {
            return;
        }
        this.trySpawnEntity(world, pos);
    }

    private void trySpawnEntity(World world, BlockPos pos) {
        BlockPos posOffsetFloor = pos.offset(Direction.Axis.X,-3).offset(Direction.Axis.Z,-3).offset(Direction.Axis.Y,-4);
        BlockPos posOffsetPillar = pos.offset(Direction.Axis.X,-2).offset(Direction.Axis.Z,-2).offset(Direction.Axis.Y,-3);
        BlockPos posOffsetFire = pos.offset(Direction.Axis.X,-2).offset(Direction.Axis.Z,-2).offset(Direction.Axis.Y,-1);
        BlockPattern.Result result = this.getKarinPattern().searchAround(world, pos);
        if (result != null && floorCheck(world, posOffsetFloor) && pillarCheck(world, posOffsetPillar) && fireCheck(world,posOffsetFire)) {
            EpicMod.LOGGER.info("trying to spawn: passed");
            KarinSpawnEntity karin = ModEntities.KARIN_SPAWN.create(world);
            if (karin != null) {
                SummoningBlock.spawnEntity(world, result, karin, result.translate(1, 4, 0).getBlockPos());
            }
        }
    }

    private static void spawnEntity(World world, BlockPattern.Result patternResult, Entity entity, BlockPos pos) {
        SummoningBlock.breakPatternBlocks(world, patternResult);
        entity.refreshPositionAndAngles((double)pos.getX() + 0.5, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0f, 0.0f);
        world.spawnEntity(entity);
        for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, entity.getBoundingBox().expand(5.0))) {
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
        }
        SummoningBlock.updatePatternBlocks(world, patternResult);
    }

    public static void breakPatternBlocks(World world, BlockPattern.Result patternResult) {
        for (int i = 0; i < patternResult.getWidth(); ++i) {
            for (int j = 0; j < patternResult.getHeight(); ++j) {
                CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
                world.setBlockState(cachedBlockPosition.getBlockPos(), Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, cachedBlockPosition.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition.getBlockState()));
            }
        }
    }

    public static void updatePatternBlocks(World world, BlockPattern.Result patternResult) {
        for (int i = 0; i < patternResult.getWidth(); ++i) {
            for (int j = 0; j < patternResult.getHeight(); ++j) {
                CachedBlockPosition cachedBlockPosition = patternResult.translate(i, j, 0);
                world.updateNeighbors(cachedBlockPosition.getBlockPos(), Blocks.AIR);
            }
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    private BlockPattern getKarinPattern() {
        if (this.karinPattern == null) {
            this.karinPattern = BlockPatternBuilder.start().aisle(
            "-^-",
                    "###",
                    "-#-",
                    "-#-")
                    .where('^', CachedBlockPosition.matchesBlockState(IS_KARIN_HEAD_PREDICATE))
                    .where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.BASALT)))
                    .where('-', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR))).build();
        }
        return this.karinPattern;
    }
    private boolean floorCheck(World world, BlockPos pos) {
        int count = 0;
        boolean isValid = true;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (world.getBlockState(pos.offset(Direction.Axis.X, j).offset(Direction.Axis.Z, i)).isOf(Blocks.NETHER_BRICKS)) {
                    count++;
                } else {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                break;
            }
        }
        return count >= 49 && isValid;
    }
    private boolean pillarCheck(World world, BlockPos pos) {
        int count = 0;
        for (int h = 0; h < 2; h++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (world.getBlockState(pos.offset(Direction.Axis.X, j).offset(Direction.Axis.Z, i).offset(Direction.Axis.Y, h)).isOf(Blocks.OBSIDIAN)) {
                        count++;
                    }
                }
            }
        }
        return count >= 8;
    }
    private boolean fireCheck(World world, BlockPos pos) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (world.getBlockState(pos.offset(Direction.Axis.X, j).offset(Direction.Axis.Z, i)).isOf(Blocks.FIRE)) {
                    count++;
                }
            }
        }
        EpicMod.LOGGER.info(count + " ");
        return count >= 4;
    }


    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}
