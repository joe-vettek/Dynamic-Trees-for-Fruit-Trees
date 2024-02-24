package xueluoanping.dtfruitfulfun.systems.fruit;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;

import com.ferreusveritas.dynamictrees.block.FruitBlock;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class FruitTypes extends Fruit {
    public static final TypedRegistry.EntryType<Fruit> TYPE = TypedRegistry.newType(FruitTypes::new);


    public FruitTypes(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected FruitBlock createBlock(BlockBehaviour.Properties properties) {
        return new PassableFruitBlock( properties,this) ;
    }


}
