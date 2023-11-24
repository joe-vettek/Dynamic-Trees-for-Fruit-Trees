package xueluoanping.dtfruittrees;

import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class DTFruitTreesRegistries {

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes (final TypeRegistryEvent<LeavesProperties> event) {
        // DTFruitTrees.LOGGER.debug("正在注册中");
        event.registerType(new ResourceLocation(DTFruitTrees.MOD_ID, "cherry"), CherryLeavesProperties.TYPE);
        event.registerType(new ResourceLocation(DTFruitTrees.MOD_ID, "redlove"), CherryLeavesProperties.TYPE);
    }



}
