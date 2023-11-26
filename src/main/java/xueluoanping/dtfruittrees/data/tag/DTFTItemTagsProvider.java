package xueluoanping.dtfruittrees.data.tag;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;

import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;


public class DTFTItemTagsProvider extends ItemTagsProvider {
    public DTFTItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, String modId,  ExistingFileHelper existingFileHelper) {
        super(generator, blockTagsProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }

}
