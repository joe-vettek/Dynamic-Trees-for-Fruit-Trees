package xueluoanping.dtfruittrees.systems.nodemappers;

import com.ferreusveritas.dynamictrees.api.network.NodeInspector;
import com.ferreusveritas.dynamictrees.blocks.branches.BranchBlock;
import com.ferreusveritas.dynamictrees.blocks.leaves.DynamicLeavesBlock;
import com.ferreusveritas.dynamictrees.trees.Species;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ALLAboveNode implements NodeInspector {
    private final List<BlockPos> blockPosList = new ArrayList<>();
    private final Species species;

    public ALLAboveNode(Species species) {
        this.species = species;
    }


    @Override
    public boolean run(BlockState blockState, IWorld world, BlockPos pos, Direction fromDir) {
        if (blockState.getBlock() instanceof BranchBlock && ((BranchBlock) blockState.getBlock()).getFamily(blockState, world, pos).getCommonSpecies() == species) {
            blockPosList.add(pos);
        }
        return false;
    }

    @Override
    public boolean returnRun(BlockState blockState, IWorld world, BlockPos pos, Direction fromDir) {
        return false;
    }

    public List<BlockPos> getBlockPosList() {
        return blockPosList;
    }
}
