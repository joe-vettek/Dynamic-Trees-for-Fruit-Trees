package xueluoanping.dtfruitfulfun.systems.leaves;


import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import snownee.fruits.cherry.CherryModule;
import snownee.fruits.cherry.block.CherryLeavesBlock;

public class DynamicCherryLeavesBlock extends DynamicFruitLeavesBlock {


    public DynamicCherryLeavesBlock(LeavesProperties leavesProperties, Properties properties) {
        super(leavesProperties, properties);
    }


    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        Block original = getProperties(stateIn).getPrimitiveLeaves().getBlock();
        original.animateTick(original.defaultBlockState(), worldIn, pos, rand);
    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState stateIn) {
        super.spawnDestroyParticles(level, player, pos, stateIn);
        if (getProperties(stateIn).getPrimitiveLeaves().getBlock() == CherryModule.CHERRY_LEAVES.get())
            CherryLeavesBlock.spawnDestroyParticles(level, player, pos, CherryModule.PETAL_CHERRY.get());
        else CherryLeavesBlock.spawnDestroyParticles(level, player, pos, CherryModule.PETAL_REDLOVE.get());
    }
}

