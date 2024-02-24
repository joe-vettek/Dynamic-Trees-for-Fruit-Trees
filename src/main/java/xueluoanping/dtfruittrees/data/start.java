package xueluoanping.dtfruittrees.data;

import com.ferreusveritas.dynamictrees.data.provider.DTBlockTagsProvider;
import com.ferreusveritas.dynamictrees.data.provider.DTItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import xueluoanping.dtfruittrees.DTFruitTrees;
import xueluoanping.dtfruittrees.data.lang.Lang_EN;
import xueluoanping.dtfruittrees.data.lang.Lang_ZH;
import xueluoanping.dtfruittrees.data.loot.DTFTLootTableProvider;


public class start {
    public final static String MODID = DTFruitTrees.MOD_ID;

    public static void dataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        if (event.includeServer()) {
            DTFruitTrees.logger("Generate recipe");

            generator.addProvider(event.includeServer(),new RecipeDataProvider(generator));

            DTBlockTagsProvider blockTags = new DTBlockTagsProvider(generator, MODID, helper);
            generator.addProvider(event.includeServer(),blockTags);
            generator.addProvider(event.includeServer(),new DTItemTagsProvider(generator, MODID, blockTags, helper));

            generator.addProvider(event.includeServer(),new DTFTLootTableProvider(generator,MODID,helper));
            // generator.addProvider(new GLMProvider(generator, MODID));

            generator.addProvider(event.includeServer(),new Lang_EN(generator, helper));
            generator.addProvider(event.includeServer(),new Lang_ZH(generator, helper));

            // generator.addProvider(new SimpleMP(generator));

        }
        if (event.includeClient()) {
            // generator.addProvider(new BlockStatesDataProvider(generator, helper));
            // generator.addProvider(new ItemModelProvider(generator, helper));
        }


    }
}
