package com.leon1236.reforestry.apiculture.items;

import java.util.function.Consumer;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.apiculture.features.ApicultureDataComponents;
import com.leon1236.reforestry.apiculture.genetics.BeeChromosomes;
import com.leon1236.reforestry.apiculture.genetics.BeeGeneticsTooltips;
import com.leon1236.reforestry.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.core.genetics.GeneticsTooltips;

public class ItemBeeGE extends Item {
    private final String lifeStage;

    public ItemBeeGE(Properties properties, String lifeStage) {
        super(properties);
        this.lifeStage = lifeStage;
    }

    public String lifeStage() {
        return lifeStage;
    }

    @Override
    public Component getName(ItemStack stack) {
        IGenome genome = stack.get(ApicultureDataComponents.BEE_GENOME.type());
        if (genome == null) {
            return super.getName(stack);
        }
        IBeeSpecies species = genome.getActiveAllele(BeeChromosomes.SPECIES).value();
        Component speciesName = Component.translatable("allele.reforestry.bee_species." + species.id().getPath());
        Component typeName = Component.translatable("for.bees.grammar." + lifeStage + ".type");
        return Component.translatable("for.bees.grammar." + lifeStage, speciesName, typeName);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display,
            Consumer<Component> tooltip, TooltipFlag flag) {
        GeneticsTooltips.appendGeneticsTooltip(stack, tooltip, genome -> BeeGeneticsTooltips.addAnalyzedTooltip(genome, tooltip));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        IGenome genome = stack.get(ApicultureDataComponents.BEE_GENOME.type());
        return genome != null && genome.getActiveAllele(BeeChromosomes.SPECIES).value().glint();
    }
}
