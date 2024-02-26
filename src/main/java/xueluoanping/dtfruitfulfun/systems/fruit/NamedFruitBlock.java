package xueluoanping.dtfruitfulfun.systems.fruit;


import com.ferreusveritas.dynamictrees.block.FruitBlock;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;
import xueluoanping.dtfruitfulfun.ModConstants;
import xueluoanping.dtfruitfulfun.util.LazyGet;
import xueluoanping.dtfruitfulfun.util.RegisterFinderUtil;

public class NamedFruitBlock extends FruitBlock {
    protected LazyGet<Item> bindFruit = LazyGet.of(() -> {
        Item item = this.fruit.getItemStack().getItem();
        Item result;
        if (item == Items.APPLE) {
            result = ModConstants.APPLE_OAK_SEED.get();
        } else {
            ResourceLocation resourceLocation = RegisterFinderUtil.getItemKey(item);
            String namespace = this.fruit.getRegistryName().getNamespace();
            result = RegisterFinderUtil.getItem(namespace, resourceLocation.getPath() + "_seed");
        }
        return result == Items.AIR ? item : result;
    });

    public NamedFruitBlock(Properties properties, Fruit fruit) {
        super(properties, fruit);
    }

    @Override
    public @NotNull Item asItem() {
        return bindFruit.get();
    }


    @Override
    public @NotNull String getDescriptionId() {
        return this.fruit.getItemStack().getItem().getDescriptionId();
    }
}
