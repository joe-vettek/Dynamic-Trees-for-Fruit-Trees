package xueluoanping.dtfruittrees.data.loot;

import com.ferreusveritas.dynamictrees.api.registry.Registries;
import com.ferreusveritas.dynamictrees.api.registry.SimpleRegistry;
import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.blocks.branches.BranchBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.data.provider.DTLootTableProvider;
import com.ferreusveritas.dynamictrees.deserialisation.JsonDeserialisers;
import com.ferreusveritas.dynamictrees.resources.Resources;
import com.ferreusveritas.dynamictrees.systems.fruit.Fruit;
import com.ferreusveritas.dynamictrees.systems.pod.Pod;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.util.ResourceLocationUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.TableBonus;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
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

    private static final ILootCondition.IBuilder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item()
            .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
    private static final ILootCondition.IBuilder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();
    private static final ILootCondition.IBuilder HAS_SHEARS =
            MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
    private static final ILootCondition.IBuilder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final ILootCondition.IBuilder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();

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
    public void run(DirectoryCache cache) {

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


    private void addTables() {

        LeavesProperties.REGISTRY.dataGenerationStream(modId).forEach(this::addLeavesBlockTable);

    }

    private void writeTables(DirectoryCache cache) {
        Path outputFolder = this.generator.getOutputFolder();
        lootTables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/" + key.getPath());
            try {
                IDataProvider.save(GSON, cache, LootTableManager.serialize(lootTable.build()), path);
            } catch (IOException e) {
                DTFruitTrees.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private final Map<ResourceLocation, LootTable.Builder> lootTables = new HashMap<>();

    private ResourceLocation getFullDropsPath(ResourceLocation path) {
        return ResourceLocationUtils.surround(path, "loot_tables/", ".json");
    }

    // The only changed behavior is here.
    private void addLeavesBlockTable(LeavesProperties leavesProperties) {
        if (leavesProperties.shouldGenerateBlockDrops()) {
            final ResourceLocation leavesBlockTablePath = getFullDropsPath(leavesProperties.getBlockLootTableName());
            if (!existingFileHelper.exists(leavesBlockTablePath, ResourcePackType.SERVER_DATA)) {
                lootTables.put(leavesBlockTablePath, leavesProperties.createBlockDrops().withPool(
                        LootPool.lootPool().setRolls(ConstantRange.exactly(1)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                                .add(ItemLootEntry.lootTableItem(leavesProperties.getFamily().getCommonSpecies().getSeed().get().asItem())
                                        .apply(SetCount.setCount(
                                                RandomValueRange.between(1.0F, 2.0F)
                                        ))
                                        .apply(ExplosionDecay.explosionDecay())
                                        .when(TableBonus.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, 0.05F, 0.0625F, 0.083333336F, 0.1F)))
                ));
            }
        }
    }
}
