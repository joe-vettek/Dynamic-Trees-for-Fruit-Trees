package xueluoanping.dtfruitfulfun.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class RegisterFinderUtil {


    public static Block getBlock(String s) {
        return getBlock( Identifier.tryParse(s));
    }

    // BuiltInRegistries
    public static Block getBlock(Identifier rs) {
        return BuiltInRegistries.BLOCK.getValue(rs);
    }

    public static Item getItem(String s) {
        return getItem(Identifier.tryParse(s));
    }

    public static Item getItem(Identifier rs) {
        return BuiltInRegistries.ITEM.getValue(rs);
    }

    public static Item getItem(String s, String s2) {
        return getItem(Identifier.fromNamespaceAndPath(s, s2));
    }

    public static Identifier getItemKey(Item s) {
        return BuiltInRegistries.ITEM.getKey(s);
    }

    public static Identifier getBlockKey(Block s) {
        return BuiltInRegistries.BLOCK.getKey(s);
    }
}
