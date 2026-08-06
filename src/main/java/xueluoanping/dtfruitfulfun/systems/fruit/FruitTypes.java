package xueluoanping.dtfruitfulfun.systems.fruit;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;

import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class FruitTypes extends Fruit {
    public static final TypedRegistry.EntryType<Fruit> TYPE = TypedRegistry.newType(FruitTypes::new);


    public FruitTypes(Identifier registryName) {
        super(registryName);
    }

    // @Override
    // protected FruitBlock createBlock(BlockBehaviour.Properties properties) {
    //     return new PassableFruitBlock( properties,this) ;
    // }

    public static FruitBlock createBlock(Identifier id, BlockBehaviour.Properties properties, Fruit fruit) {
        return new PassableFruitBlock(id, properties, fruit);
    }
}
