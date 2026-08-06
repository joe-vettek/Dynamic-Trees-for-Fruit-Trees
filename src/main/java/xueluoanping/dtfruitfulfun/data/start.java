package xueluoanping.dtfruitfulfun.data;

import com.dtteam.dynamictrees.data.provider.DTBlockTagsProvider;
import com.dtteam.dynamictrees.data.provider.DTItemTagsProvider;
import com.dtteam.dynamictrees.data.provider.DTLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xueluoanping.dtfruitfulfun.DTFruitfulFun;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;


public class start {
    public final static String MODID = DTFruitfulFun.MOD_ID;

    public static void dataGen(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        if (event instanceof GatherDataEvent.Server) {
            DTFruitfulFun.logger("Generate recipe");

            // work it until 1.21
            // generator.addProvider(true,new RecipeDataProvider(packOutput));

            DTBlockTagsProvider blockTags = new DTBlockTagsProvider(packOutput,lookupProvider, MODID);
            generator.addProvider(true,blockTags);
            generator.addProvider(true,new DTItemTagsProvider(packOutput, lookupProvider, MODID));

            generator.addProvider(true,new DTLootTableProvider(packOutput,MODID,lookupProvider));
            // generator.addProvider(new GLMProvider(generator, MODID));


            // generator.addProvider(new SimpleMP(generator));

        }
        if ((Object)event instanceof GatherDataEvent.Client) {
            // generator.addProvider(new BlockStatesDataProvider(generator, helper));
            // generator.addProvider(new ItemModelProvider(generator, helper));
        }


    }
}
