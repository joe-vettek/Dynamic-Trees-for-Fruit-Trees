package xueluoanping.dtfruittrees;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.blocks.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import snownee.fruits.cherry.CherryModule;

import java.util.Random;

public class CherryLeavesProperties extends LeavesProperties {

    public static final TypedRegistry.EntryType<LeavesProperties> TYPE = TypedRegistry.newType(CherryLeavesProperties::new);

    public CherryLeavesProperties(ResourceLocation registryName) {
        super(registryName);
    }


    @Override
    protected DynamicLeavesBlock createDynamicLeaves(AbstractBlock.Properties properties) {
        return new DynamicLeavesBlock(this, properties) {
            @Override
            @OnlyIn(Dist.CLIENT)
            public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
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
                    worldIn.addParticle(getProperties(stateIn).getPrimitiveLeaves().getBlock()==CherryModule.CHERRY_LEAVES.getBlock()?CherryModule.PETAL_CHERRY:CherryModule.PETAL_REDLOVE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        };
    }

}
