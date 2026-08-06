package xueluoanping.dtfruitfulfun.systems.leaves;


import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import xueluoanping.dtfruitfulfun.ModConstants;
import xueluoanping.dtfruitfulfun.util.RegisterFinderUtil;

import javax.annotation.Nonnull;

public class NamedVanillaCherryLeaveProperties extends LeavesProperties {
    public static final TypedRegistry.EntryType<LeavesProperties> TYPE = TypedRegistry.newType(NamedVanillaCherryLeaveProperties::new);

    public NamedVanillaCherryLeaveProperties(Identifier registryName) {
        super(registryName);
    }

    @Nonnull
    protected DynamicLeavesBlock createDynamicLeaves(@Nonnull BlockBehaviour.Properties properties) {
        return new DynamicLeavesBlock(getBlockRegistryName(),this, properties) {
            @Override
            public void animateTick(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull RandomSource random) {
                super.animateTick(state, level, pos, random);
                if (random.nextInt(10) == 0) {
                    BlockPos blockpos = pos.below();
                    BlockState blockstate = level.getBlockState(blockpos);
                    if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                        ParticleUtils.spawnParticleBelow(level, pos, random, ParticleTypes.CHERRY_LEAVES);
                    }
                }

            }

            @Override
            public Item asItem() {
                return ModConstants.CHERRY_SEED_V.get();
            }
        };
    }
}
