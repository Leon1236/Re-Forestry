package com.leon1236.reforestry.arboriculture.items;

import java.util.function.Consumer;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.features.ArboricultureDataComponents;
import com.leon1236.reforestry.arboriculture.genetics.ITreeSpecies;
import com.leon1236.reforestry.arboriculture.genetics.TreeChromosomes;
import com.leon1236.reforestry.arboriculture.genetics.TreeGeneticsTooltips;
import com.leon1236.reforestry.arboriculture.genetics.TreePollenType;
import com.leon1236.reforestry.arboriculture.tiles.TileSapling;
import com.leon1236.reforestry.core.genetics.GeneticsTooltips;

public class ItemGermlingGE extends Item {
    private final String lifeStage;

    public ItemGermlingGE(Properties properties, String lifeStage) {
        super(properties);
        this.lifeStage = lifeStage;
    }

    public String lifeStage() {
        return lifeStage;
    }

    @Override
    public Component getName(ItemStack stack) {
        IGenome genome = stack.get(ArboricultureDataComponents.TREE_GENOME.type());
        if (genome == null) {
            return super.getName(stack);
        }
        ITreeSpecies species = genome.getActiveAllele(TreeChromosomes.SPECIES).value();
        Component speciesName = Component.translatable("allele.reforestry.tree_species." + species.id().getPath());
        Component typeName = Component.translatable("for.trees.grammar." + lifeStage + ".type");
        return Component.translatable("for.trees.grammar." + lifeStage, speciesName, typeName);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
            Consumer<Component> tooltip, TooltipFlag flag) {
        GeneticsTooltips.appendGeneticsTooltip(stack, tooltip, genome -> TreeGeneticsTooltips.addAnalyzedTooltip(genome, tooltip));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if ("pollen".equals(lifeStage)) {
            return usePollen(context);
        }
        if (!"sapling".equals(lifeStage)) {
            return InteractionResult.PASS;
        }

        ItemStack stack = context.getItemInHand();
        IGenome genome = stack.get(ArboricultureDataComponents.TREE_GENOME.type());
        if (genome == null) {
            return InteractionResult.PASS;
        }

        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockPos plantPos = context.getClickedFace().getAxis().isVertical()
                ? clickedPos.relative(context.getClickedFace())
                : clickedPos.above();

        BlockState existing = level.getBlockState(plantPos);
        if (!existing.canBeReplaced()) {
            return InteractionResult.PASS;
        }
        if (!level.getBlockState(plantPos.below()).is(BlockTags.SUPPORTS_VEGETATION)) {
            return InteractionResult.PASS;
        }

        level.setBlock(plantPos, ArboricultureBlocks.SAPLING.block().defaultBlockState(), 3);
        if (level.getBlockEntity(plantPos) instanceof TileSapling sapling) {
            sapling.setGenome(genome);
        }

        Player player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

    private InteractionResult usePollen(UseOnContext context) {
        ItemStack stack = context.getItemInHand();
        IGenome pollen = stack.get(ArboricultureDataComponents.TREE_GENOME.type());
        if (pollen == null) {
            return InteractionResult.PASS;
        }

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (!TreePollenType.INSTANCE.tryPollinate(level, pos, pollen, level.getRandom())) {
            return InteractionResult.PASS;
        }

        Player player = context.getPlayer();
        if (player != null && !player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
