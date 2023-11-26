package xueluoanping.dtfruittrees.data.lang;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.util.RegisterFinderUtil;

import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Lang_EN extends LangHelper {
    public Lang_EN(DataGenerator gen, ExistingFileHelper helper) {
        super(gen, helper, DTFruitTrees.MOD_ID, "en_us");
    }


    @Override
    protected void addTranslations() {
        add(DTFruitTrees.MOD_ID, "Dynamic Trees for Fruit Trees");

        add(RegisterFinderUtil.getBlock("dtfruittrees:cherry_sapling"), "Cherry Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:mandarin_sapling"), "Mandarin Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:lime_sapling"), "Lime Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:citron_sapling"), "Citron Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:pomelo_sapling"), "Pomelo Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:orange_sapling"), "Orange Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:lemon_sapling"), "Lemon Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:grapefruit_sapling"), "Grapefruit Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:redlove_sapling"), "Redlove Sapling");
        add(RegisterFinderUtil.getBlock("dtfruittrees:cherry_branch"), "Cherry Tree");
        add(RegisterFinderUtil.getBlock("dtfruittrees:mandarin_branch"), "Mandarin Tree");
        add(RegisterFinderUtil.getBlock("dtfruittrees:lime_branch"), "Lime Tree");
        add(RegisterFinderUtil.getBlock("dtfruittrees:citron_branch"), "Citron Tree");
        add(RegisterFinderUtil.getBlock("dtfruittrees:pomelo_branch"), "Pomelo Tree");
        add(RegisterFinderUtil.getBlock("dtfruittrees:orange_branch"), "Orange Tree");
        add(RegisterFinderUtil.getBlock("dtfruittrees:lemon_branch"), "Lemon Tree");
        add(RegisterFinderUtil.getBlock("dtfruittrees:grapefruit_branch"), "Grapefruit Tree");
        add(RegisterFinderUtil.getBlock("dtfruittrees:redlove_branch"), "Redlove Tree");
        add(RegisterFinderUtil.getItem("dtfruittrees:cherry_seed"), "Cherry Seed");
        add(RegisterFinderUtil.getItem("dtfruittrees:mandarin_seed"), "Mandarin Seed");
        add(RegisterFinderUtil.getItem("dtfruittrees:lime_seed"), "Lime Seed");
        add(RegisterFinderUtil.getItem("dtfruittrees:citron_seed"), "Citron Seed");
        add(RegisterFinderUtil.getItem("dtfruittrees:pomelo_seed"), "Pomelo Seed");
        add(RegisterFinderUtil.getItem("dtfruittrees:orange_seed"), "Orange Seed");
        add(RegisterFinderUtil.getItem("dtfruittrees:lemon_seed"), "Lemon Seed");
        add(RegisterFinderUtil.getItem("dtfruittrees:grapefruit_seed"), "Grapefruit Seed");
        add(RegisterFinderUtil.getItem("dtfruittrees:redlove_seed"), "Redlove Seed");
        addSpecie("cherry","Cherry");
        addSpecie("mandarin","Mandarin");
        addSpecie("lime","Lime");
        addSpecie("citron","Citron");
        addSpecie("pomelo","Pomelo");
        addSpecie("orange","Orange");
        addSpecie("lemon","Lemon");
        addSpecie("grapefruit","Grapefruit");
        addSpecie("redlove","Redlove");

        DTFruitTrees.logger(ForgeRegistries.ITEMS.getEntries().stream().filter(registryKeyBlockEntry -> registryKeyBlockEntry.getValue().getRegistryName().getNamespace().startsWith("dtfruittrees")).map(registryKeyBlockEntry -> registryKeyBlockEntry.getValue().getRegistryName()).collect(Collectors.toList()));
    }
}
