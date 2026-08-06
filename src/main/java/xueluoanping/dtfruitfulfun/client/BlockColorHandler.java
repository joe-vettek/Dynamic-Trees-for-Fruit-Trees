package xueluoanping.dtfruitfulfun.client;


import com.dtteam.dynamictrees.client.BlockColorMultipliers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import snownee.fruits.block.FruitLeavesBlock;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;

import java.util.List;

import static xueluoanping.dtfruitfulfun.ModConstants.*;

@EventBusSubscriber(value = Dist.CLIENT)
public class BlockColorHandler {


    @SubscribeEvent
    public static void onRegisterColorHandlersEvent_Block(RegisterColorHandlersEvent.BlockTintSources event) {
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


        event.register(
                List.of(new BlockTintSource() {
                    @Override
                    public int color(BlockState state) {
                        return -1;
                    }

                    @Override
                    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                        Block block = state.getBlock();
                        if (block == REDLOVE_LEAVES.get())
                            return -1;
                        if (block == CHERRY_LEAVES.get())
                            return -1;
                        return Minecraft.getInstance().getBlockColors().getTintSource(birchLeaves, 1).colorInWorld(state, level, pos);
                    }
                }, new BlockTintSource() {
                    @Override
                    public int color(BlockState state) {
                        Block block = state.getBlock();
                        if (block == CITRON_LEAVES.get())
                            return 0xDDCC58;
                        if (block == GRAPEFRUIT_LEAVES.get())
                            return 0xF4502B;
                        if (block == LEMON_LEAVES.get())
                            return 0xEBCA4B;
                        if (block == LIME_LEAVES.get())
                            return 0xCADA76;
                        if (block == TANGERINE_LEAVES.get())
                            return 0xF08A19;
                        if (block == ORANGE_LEAVES.get())
                            return 0xF08A19;
                        if (block == POMEGRANATE_LEAVES.get())
                            return 0xB23733;
                        if (block == POMELO_LEAVES.get())
                            return 0xF7F67E;
                        if (block == REDLOVE_LEAVES.get())
                            return 0xC22626;
                        if (block == CHERRY_LEAVES.get())
                            return 0xE45B55;
                        return FoliageColor.FOLIAGE_EVERGREEN;
                    }

                    @Override
                    public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                        int stage = state.getValue(FruitLeavesBlock.AGE);
                        if (stage < 3) {
                            return Minecraft.getInstance().getBlockColors().getTintSource(birchLeaves, 1).colorInWorld(state, level, pos);
                        }
                        return BlockTintSource.super.colorInWorld(state, level, pos);
                    }
                }), CHERRY_LEAVES.get(), TANGERINE_LEAVES.get(), LIME_LEAVES.get(), CITRON_LEAVES.get(), POMEGRANATE_LEAVES.get(), POMELO_LEAVES.get(), ORANGE_LEAVES.get(), LEMON_LEAVES.get(), GRAPEFRUIT_LEAVES.get(), REDLOVE_LEAVES.get());


        event.register(List.of(
                new BlockTintSource() {
                    @Override
                    public int color(BlockState state) {
                        Block block = state.getValue(BlockStateProperties.AGE_3) == 0 ?
                                Blocks.AIR : state.getBlock();

                        if (block == CHERRY.get())
                            return 0xE45B55;
                        // if (i == 0)
                        {
                            if (block == CITRON.get())
                                return 0xDDCC58;
                            if (block == GRAPEFRUIT.get())
                                return 0xF4502B;
                            if (block == LEMON.get())
                                return 0xEBCA4B;
                            if (block == LIME.get())
                                return 0xCADA76;
                            if (block == TANGERINE.get())
                                return 0xF08A19;
                            if (block == ORANGE.get())
                                return 0xF08A19;
                            if (block == POMEGRANATE.get())
                                return 0xB23733;
                            if (block == POMELO.get())
                                return 0xF7F67E;
                            if (block == REDLOVE.get())
                                return 0xC22626;
                        }
                        // else
                        //     return FoliageColors.getBirchColor();

                        return -1;
                    }
                }
        ), CHERRY.get(), TANGERINE.get(), LIME.get(), CITRON.get(), POMEGRANATE.get(), POMELO.get(), ORANGE.get(), LEMON.get(), GRAPEFRUIT.get(), REDLOVE.get());

    }
}
