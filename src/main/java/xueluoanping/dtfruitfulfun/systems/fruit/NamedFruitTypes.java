package xueluoanping.dtfruitfulfun.systems.fruit;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class NamedFruitTypes extends Fruit {
    public static final TypedRegistry.EntryType<Fruit> TYPE = TypedRegistry.newType(NamedFruitTypes::new);


    public NamedFruitTypes(Identifier registryName) {
        super(registryName);
    }


    public static FruitBlock createBlock(Identifier id, BlockBehaviour.Properties properties, Fruit fruit) {
        return new NamedFruitBlock(id, properties, fruit);
    }
}
