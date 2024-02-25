package xueluoanping.dtfruitfulfun.systems.fruit;


import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.block.FruitBlock;
import com.ferreusveritas.dynamictrees.compat.season.SeasonHelper;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.util.AgeProperties;
import com.ferreusveritas.dynamictrees.util.LevelContext;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;

public class NamedFruitBlock extends FruitBlock {
    public NamedFruitBlock(Properties properties, Fruit fruit) {
        super(properties, fruit);
    }

    @Override
    public Item asItem() {
        Item item = this.fruit.getItemStack().getItem();
        Item result;
        if (item == Items.APPLE) {
            result = BuiltInRegistries.ITEM.get(new ResourceLocation(DynamicTrees.MOD_ID, "apple_oak_seed"));
        } else {
            ResourceLocation resourceLocation = BuiltInRegistries.ITEM.getKey(item);
            String namespace = this.fruit.getRegistryName().getNamespace();
            result = BuiltInRegistries.ITEM.get(new ResourceLocation(namespace, resourceLocation.getPath() + "_seed"));
        }
        return result == Items.AIR ? item : result;
    }


    @Override
    public String getDescriptionId() {
        return this.fruit.getItemStack().getItem().getDescriptionId();
    }
}
