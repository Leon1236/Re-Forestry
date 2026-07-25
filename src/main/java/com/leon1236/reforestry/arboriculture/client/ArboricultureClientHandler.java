package com.leon1236.reforestry.arboriculture.client;

import java.util.List;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.minecraft.network.chat.Component;

import com.leon1236.reforestry.api.client.IClientModuleHandler;
import com.leon1236.reforestry.arboriculture.blocks.ForestryLeafType;
import com.leon1236.reforestry.arboriculture.features.ArboricultureBlocks;
import com.leon1236.reforestry.arboriculture.features.ArboricultureTiles;
import com.leon1236.reforestry.arboriculture.items.ItemGrafter;

public class ArboricultureClientHandler implements IClientModuleHandler {
    @Override
    public void registerClient() {
        ModelLoadingPlugin.register(ctx -> {
            ctx.registerBlockStateResolver(ArboricultureBlocks.LEAVES.block(), new LeafBlockStateResolver());
            ctx.registerBlockStateResolver(ArboricultureBlocks.SAPLING.block(), new SaplingBlockStateResolver());
        });
        BlockColorRegistry.register(List.of(new LeafFoliageTintSource()), ArboricultureBlocks.LEAVES.block());
        for (ForestryLeafType type : ForestryLeafType.VALUES) {
            DefaultLeafFoliageTintSource tint = new DefaultLeafFoliageTintSource(type);
            BlockColorRegistry.register(List.of(tint),
                    ArboricultureBlocks.LEAVES_DEFAULT.get(type).block(),
                    ArboricultureBlocks.LEAVES_DEFAULT_FRUIT.get(type).block(),
                    ArboricultureBlocks.LEAVES_DECORATIVE.get(type).block());
        }

        BlockEntityRenderers.register(ArboricultureTiles.SIGN.type(), StandingSignRenderer::new);

        ItemTooltipCallback.EVENT.register((stack, context, tooltipFlag, lines) -> {
            if (stack.getItem() instanceof ItemGrafter && !stack.isDamaged()) {
                Component uses = Component.translatable("item.reforestry.uses", stack.getMaxDamage() + 1)
                        .withStyle(ChatFormatting.GRAY);
                if (lines.isEmpty()) {
                    lines.add(uses);
                } else {
                    lines.add(1, uses);
                }
            }
        });
    }
}
