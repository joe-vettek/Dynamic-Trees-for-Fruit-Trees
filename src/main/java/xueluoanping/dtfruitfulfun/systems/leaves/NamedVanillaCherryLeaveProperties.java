package xueluoanping.dtfruitfulfun.systems.leaves;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class NamedVanillaCherryLeaveProperties extends LeavesProperties{
    public static final TypedRegistry.EntryType<LeavesProperties> TYPE = TypedRegistry.newType(NamedVanillaCherryLeaveProperties::new);

    public NamedVanillaCherryLeaveProperties(ResourceLocation registryName) {
        super(registryName);
    }

    @Nonnull
    protected DynamicLeavesBlock createDynamicLeaves(@Nonnull BlockBehaviour.Properties properties) {
        return new DynamicLeavesBlock(this, properties) {
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
                return BuiltInRegistries.ITEM.get(new ResourceLocation(DynamicTrees.MOD_ID, "cherry_seed"));
            }
        };
    }
}
