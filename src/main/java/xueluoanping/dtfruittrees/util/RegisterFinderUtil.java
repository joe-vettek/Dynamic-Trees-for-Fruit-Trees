package xueluoanping.dtfruittrees.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterFinderUtil {


    public static Block getBlock(String s) {
        return getBlock(new ResourceLocation(s));
    }

    public static Block getBlock(ResourceLocation rs) {
        return ForgeRegistries.BLOCKS.getValue(rs);
    }

    public static Item getItem(String s) {
        return getItem(new ResourceLocation(s));
    }

    public static Item getItem(ResourceLocation rs) {
        return ForgeRegistries.ITEMS.getValue(rs);
    }
}
