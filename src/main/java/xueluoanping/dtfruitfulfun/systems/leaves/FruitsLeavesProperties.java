package xueluoanping.dtfruitfulfun.systems.leaves;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class FruitsLeavesProperties extends LeavesProperties {

    public static final TypedRegistry.EntryType<LeavesProperties> TYPE = TypedRegistry.newType(FruitsLeavesProperties::new);

    public FruitsLeavesProperties(ResourceLocation registryName) {
        super(registryName);
    }




    @Override
    protected DynamicLeavesBlock createDynamicLeaves(BlockBehaviour.Properties properties) {
        return new DynamicFruitLeavesBlock(this, properties) ;
    }

}
