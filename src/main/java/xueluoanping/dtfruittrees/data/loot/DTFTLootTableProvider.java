package xueluoanping.dtfruittrees.data.loot;


import com.ferreusveritas.dynamictrees.data.provider.DTLootTableProvider;
import com.ferreusveritas.dynamictrees.deserialisation.JsonDeserialisers;
import com.ferreusveritas.dynamictrees.resources.Resources;

import com.ferreusveritas.dynamictrees.util.ResourceLocationUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.data.DataGenerator;

import net.minecraft.data.HashCache;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import xueluoanping.dtfruittrees.DTFruitTrees;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//  I inherited DTLootTableProvider, but many of its functions are private,
//  so I have to copy them to facilitate modification.

public class DTFTLootTableProvider extends DTLootTableProvider {

    private final DataGenerator generator;
    private final String modId;
    private final ExistingFileHelper existingFileHelper;

    public DTFTLootTableProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
        this.generator = generator;
        this.modId = modId;
        this.existingFileHelper = existingFileHelper;
    }

    // The reason why these functions appear is that
    // the loot table of the leaves block needs to be overwritten.
    @Override
    public void run(HashCache cache) {

        Resources.MANAGER.reload(Resources.MANAGER.prepareReload(null, null));
        // Resources.MANAGER.gatherData();
        // Resources.MANAGER.setup();
        // First generate the default
        super.run(cache);

        // Now overwrite and generate the parts that need to be customized.
        // Not need any more
        // addTables();
        // writeTables(cache);
    }

}
