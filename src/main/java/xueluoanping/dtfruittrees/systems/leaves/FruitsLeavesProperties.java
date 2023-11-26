package xueluoanping.dtfruittrees.systems.leaves;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.blocks.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.ResourceLocation;

public class FruitsLeavesProperties extends LeavesProperties {

    public static final TypedRegistry.EntryType<LeavesProperties> TYPE = TypedRegistry.newType(FruitsLeavesProperties::new);

    public FruitsLeavesProperties(ResourceLocation registryName) {
        super(registryName);
    }


    @Override
    protected DynamicLeavesBlock createDynamicLeaves(AbstractBlock.Properties properties) {
        return new DynamicFruitLeavesBlock(this, properties) ;

    }

}
