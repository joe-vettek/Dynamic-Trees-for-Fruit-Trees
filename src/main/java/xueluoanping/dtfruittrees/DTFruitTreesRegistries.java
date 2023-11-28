package xueluoanping.dtfruittrees;

import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.FeatureCanceller;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;

import com.ferreusveritas.dynamictrees.systems.genfeatures.GenFeature;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import snownee.fruits.Hook;
import snownee.fruits.block.FruitLeavesBlock;
import snownee.fruits.mixin.MixinBeeEntity;
import xueluoanping.dtfruittrees.systems.featuregen.CherryFeatures;
import xueluoanping.dtfruittrees.systems.leaves.CherryLeavesProperties;
import xueluoanping.dtfruittrees.systems.leaves.FruitsLeavesProperties;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DTFruitTreesRegistries {

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(final TypeRegistryEvent<LeavesProperties> event) {
        // DTFruitTrees.LOGGER.debug("正在注册中");
        event.registerType(new ResourceLocation(DTFruitTrees.MOD_ID, "cherry"), CherryLeavesProperties.TYPE);
        event.registerType(new ResourceLocation(DTFruitTrees.MOD_ID, "fruittrees"), FruitsLeavesProperties.TYPE);
    }


    public static final FeatureCanceller FRUIT_TREES_CANCELLER = new FeatureCanceller(new ResourceLocation(DTFruitTrees.MOD_ID, "fruittrees")) {
        @Override
        public boolean shouldCancel(ConfiguredFeature<?, ?> configuredFeature, BiomePropertySelectors.FeatureCancellations featureCancellations) {
            // Note it not in ForgeRegistries.FEATURES
            final ResourceLocation featureName = WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature);
            if (featureName == null) {
                return false;
            }

            // DTFruitTrees.LOGGER.debug("testName," +  (WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature)));
            // if (FruitsConfig.worldGen)
            //     FruitsConfig.worldGen = false;
            // FruitsConfig.worldGen = true;
           return featureCancellations.shouldCancelNamespace(featureName.getNamespace())
                   &&(WorldGenRegistries.CONFIGURED_FEATURE.getKey(configuredFeature)+"").startsWith("fruittrees");
        }

    };

    @SubscribeEvent
    public static void onFeatureCancellerRegistry(final RegistryEvent<FeatureCanceller> event) {
        event.getRegistry().registerAll(FRUIT_TREES_CANCELLER);
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GenFeature> event) {
        CherryFeatures.register(event.getRegistry());
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void handleBlockColor(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockState birchLeaves = Blocks.BIRCH_LEAVES.defaultBlockState();
            Block CHERRY_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:cherry_leaves"));
            Block MANDARIN_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:mandarin_leaves"));
            Block LIME_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:lime_leaves"));
            Block CITRON_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:citron_leaves"));
            Block POMELO_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:pomelo_leaves"));
            Block ORANGE_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:orange_leaves"));
            Block LEMON_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:lemon_leaves"));
            Block GRAPEFRUIT_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:grapefruit_leaves"));
            Block REDLOVE_LEAVES = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("dtfruittrees:redlove_leaves"));
            Minecraft.getInstance().getBlockColors().register((state, worlds, poss, i) -> {
                if (i == 0) {
                    return Minecraft.getInstance().getBlockColors().getColor(birchLeaves, worlds, poss, i);
                }
                if (i == 1) {
                    int stage = state.getValue(FruitLeavesBlock.AGE);
                    if (stage < 3) {
                        return Minecraft.getInstance().getBlockColors().getColor(birchLeaves, worlds, poss, i);
                    }
                    Block block = state.getBlock();
                    if (block == CITRON_LEAVES)
                        return 0xDDCC58;
                    if (block == GRAPEFRUIT_LEAVES)
                        return 0xF4502B;
                    if (block == LEMON_LEAVES)
                        return 0xEBCA4B;
                    if (block == LIME_LEAVES)
                        return 0xCADA76;
                    if (block == MANDARIN_LEAVES)
                        return 0xF08A19;
                    if (block == ORANGE_LEAVES)
                        return 0xF08A19;
                    if (block == POMELO_LEAVES)
                        return 0xF7F67E;
                    if (block == REDLOVE_LEAVES)
                        return 0xC22626;
                    if (block == CHERRY_LEAVES)
                        return 0xE45B55;
                }
                return FoliageColors.getEvergreenColor();
            }, CHERRY_LEAVES, MANDARIN_LEAVES, LIME_LEAVES, CITRON_LEAVES, POMELO_LEAVES, ORANGE_LEAVES, LEMON_LEAVES, GRAPEFRUIT_LEAVES, REDLOVE_LEAVES);
        });
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerJsonColorMultipliers(ColorHandlerEvent.Block event) {
        // Register programmable custom block color providers for LeavesPropertiesJson

        BlockState birchLeaves = Blocks.BIRCH_LEAVES.defaultBlockState();
        BlockColors blockColors = event.getBlockColors();

        // BlockColorMultipliers.register("fruitleaves", (state, world, pos, tineIndex) -> {
        //     if (tineIndex == 0) {
        //         return blockColors.getColor(birchLeaves, world, pos, tineIndex);
        //     }
        //     if (tineIndex == 1) {
        //         int stage = state.getValue(FruitLeavesBlock.AGE);
        //         if (stage < 3) {
        //             return blockColors.getColor(birchLeaves, world, pos, tineIndex);
        //         }
        //         return 0xE45B55;
        //     }
        //     return 0xE45B55;
        // });
        //
        // BlockColorMultipliers.register("cherryleaves", (state, world, pos, tineIndex) -> {
        //     if (tineIndex == 0) {
        //         return blockColors.getColor(birchLeaves, world, pos, tineIndex);
        //     }
        //     if (tineIndex == 1) {
        //         int stage = state.getValue(FruitLeavesBlock.AGE);
        //         if (stage < 3) {
        //             return blockColors.getColor(birchLeaves, world, pos, tineIndex);
        //         }
        //         // if (block == REDLOVE_LEAVES)
        //         //     return 0xC22626;
        //         // if (block == CHERRY_LEAVES)
        //
        //         return 0xE45B55;
        //     }
        //     return -1;
        // });

    }
}
