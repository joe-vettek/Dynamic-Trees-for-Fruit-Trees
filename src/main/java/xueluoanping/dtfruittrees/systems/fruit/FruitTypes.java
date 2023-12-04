package xueluoanping.dtfruittrees.systems.fruit;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.blocks.FruitBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.ResourceLocation;
import xueluoanping.dtfruittrees.systems.leaves.CherryLeavesProperties;
import xueluoanping.dtfruittrees.systems.leaves.DynamicCherryLeavesBlock;

public class FruitTypes extends Fruit {
    public static final TypedRegistry.EntryType<Fruit> TYPE = TypedRegistry.newType(FruitTypes::new);


    public FruitTypes(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected FruitBlock createBlock(AbstractBlock.Properties properties) {
        return new PassableFruitBlock( properties,this) ;
    }



}
