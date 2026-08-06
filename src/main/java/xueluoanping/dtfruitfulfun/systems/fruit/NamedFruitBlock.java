package xueluoanping.dtfruitfulfun.systems.fruit;


import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import net.minecraft.resources.Identifier;
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
            Identifier resourceLocation = RegisterFinderUtil.getItemKey(item);
            String namespace = this.fruit.getRegistryName().getNamespace();
            result = RegisterFinderUtil.getItem(namespace, resourceLocation.getPath() + "_seed");
        }
        return result == Items.AIR ? item : result;
    });

    public NamedFruitBlock(Identifier id, Properties properties, Fruit fruit) {
        super(id, properties, fruit);
    }

    @Override
    public @NotNull Item asItem() {
        return bindFruit.get();
    }


    // @Override
    // public @NotNull String getDescriptionId() {
    //     return this.fruit.getItemStack().getItem().getDescriptionId();
    // }
}
