package xueluoanping.dtfruitfulfun.data.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;
import xueluoanping.dtfruitfulfun.util.RegisterFinderUtil;

public class Lang_EN extends LangHelper {
    public Lang_EN(PackOutput gen, ExistingFileHelper helper) {
        super(gen, helper, DTFruitfulFun.MOD_ID, "en_us");
    }


    @Override
    protected void addTranslations() {
        add(DTFruitfulFun.MOD_ID, "Dynamic Trees for Fruit Trees");

        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("cherry_sapling")), "Cherry Sapling");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("tangerine_sapling")), "Tangerine Sapling");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("lime_sapling")), "Lime Sapling");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("citron_sapling")), "Citron Sapling");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("pomelo_sapling")), "Pomelo Sapling");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("orange_sapling")), "Orange Sapling");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("lemon_sapling")), "Lemon Sapling");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("grapefruit_sapling")), "Grapefruit Sapling");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("redlove_sapling")), "Redlove Sapling");
        // add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("cherry_branch")), "Cherry Tree");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("tangerine_branch")), "Tangerine Tree");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("lime_branch")), "Lime Tree");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("citron_branch")), "Citron Tree");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("pomelo_branch")), "Pomelo Tree");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("orange_branch")), "Orange Tree");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("lemon_branch")), "Lemon Tree");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("grapefruit_branch")), "Grapefruit Tree");
        add(RegisterFinderUtil.getBlock(DTFruitfulFun.rl("redlove_branch")), "Redlove Tree");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("cherry_seed")), "Cherry Seed");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("tangerine_seed")), "Tangerine Seed");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("lime_seed")), "Lime Seed");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("citron_seed")), "Citron Seed");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("pomelo_seed")), "Pomelo Seed");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("orange_seed")), "Orange Seed");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("lemon_seed")), "Lemon Seed");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("grapefruit_seed")), "Grapefruit Seed");
        add(RegisterFinderUtil.getItem(DTFruitfulFun.rl("redlove_seed")), "Redlove Seed");
        addSpecie("cherry","Cherry Fruit");
        addSpecie("tangerine","Tangerine");
        addSpecie("lime","Lime");
        addSpecie("citron","Citron");
        addSpecie("pomelo","Pomelo");
        addSpecie("orange","Orange");
        addSpecie("lemon","Lemon");
        addSpecie("grapefruit","Grapefruit");
        addSpecie("redlove","Redlove");

        // DTFruitTrees.logger(ForgeRegistries.ITEMS.getEntries().stream().filter(registryKeyBlockEntry -> registryKeyBlockEntry.getValue().getRegistryName().getNamespace().startsWith("dtfruittrees")).map(registryKeyBlockEntry -> registryKeyBlockEntry.getValue().getRegistryName()).collect(Collectors.toList()));
    }
}
