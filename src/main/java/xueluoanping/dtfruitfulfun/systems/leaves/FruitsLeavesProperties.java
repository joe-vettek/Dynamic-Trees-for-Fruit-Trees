package xueluoanping.dtfruitfulfun.systems.leaves;


import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.leaves.DynamicLeavesBlock;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class FruitsLeavesProperties extends LeavesProperties {

    public static final TypedRegistry.EntryType<LeavesProperties> TYPE = TypedRegistry.newType(FruitsLeavesProperties::new);

    public FruitsLeavesProperties(Identifier registryName) {
        super(registryName);
    }




    @Override
    protected DynamicLeavesBlock createDynamicLeaves(BlockBehaviour.Properties properties) {
        return new DynamicFruitLeavesBlock(getBlockRegistryName(),this, properties) ;
    }

}
