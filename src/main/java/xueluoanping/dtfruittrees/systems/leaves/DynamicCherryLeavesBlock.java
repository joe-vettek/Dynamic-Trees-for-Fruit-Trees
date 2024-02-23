package xueluoanping.dtfruittrees.systems.leaves;


import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import snownee.fruits.cherry.CherryModule;

import java.util.Random;

public class DynamicCherryLeavesBlock extends DynamicFruitLeavesBlock {


    public DynamicCherryLeavesBlock(LeavesProperties leavesProperties, Properties properties) {
        super(leavesProperties, properties);
    }



    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        int i = rand.nextInt(15);
        boolean raining = worldIn.isRainingAt(pos.above());
        if (raining && i == 1) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = worldIn.getBlockState(blockpos);
            if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(worldIn, blockpos, Direction.UP)) {
                double d0 = pos.getX() + rand.nextFloat();
                double d1 = pos.getY() - 0.05D;
                double d2 = pos.getZ() + rand.nextFloat();
                worldIn.addParticle(ParticleTypes.DRIPPING_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        } else if (i == 2 || i == 3 && raining) {
            double d0 = pos.getX() + rand.nextFloat();
            double d1 = pos.getY() + rand.nextFloat();
            double d2 = pos.getZ() + rand.nextFloat();
            // DTFruitTrees.LOGGER.debug(stateIn.getBlock()+""+CherryModule.CHERRY_LEAVES.getBlock()+"");
            worldIn.addParticle(getProperties(stateIn).getPrimitiveLeaves().getBlock() == CherryModule.CHERRY_LEAVES.get() ? CherryModule.PETAL_CHERRY.get() : CherryModule.PETAL_REDLOVE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
}

