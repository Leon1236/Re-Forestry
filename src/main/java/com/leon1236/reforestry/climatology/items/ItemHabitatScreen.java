package com.leon1236.reforestry.climatology.items;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import com.leon1236.reforestry.api.IForestryApi;
import com.leon1236.reforestry.api.climate.IClimateHousing;
import com.leon1236.reforestry.api.climate.IClimateState;
import com.leon1236.reforestry.core.features.ClimatologyDataComponents;

public class ItemHabitatScreen extends Item {
	public ItemHabitatScreen(Properties properties) {
		super(properties.stacksTo(1));
	}

	public static boolean isPreviewModeActive(ItemStack stack) {
		return stack.getOrDefault(ClimatologyDataComponents.HABITAT_PREVIEW.type(), false);
	}

	public static void setPreviewMode(ItemStack stack, boolean preview) {
		stack.set(ClimatologyDataComponents.HABITAT_PREVIEW.type(), preview);
	}

	@Nullable
	public static BlockPos getLinkedPos(ItemStack stack) {
		return stack.get(ClimatologyDataComponents.HABITAT_LINKED_POS.type());
	}

	@Nullable
	public static ResourceKey<Level> getLinkedDimension(ItemStack stack) {
		return stack.get(ClimatologyDataComponents.HABITAT_LINKED_DIMENSION.type());
	}

	public static boolean isValid(ItemStack stack, @Nullable Level level) {
		BlockPos pos = getLinkedPos(stack);
		ResourceKey<Level> dimension = getLinkedDimension(stack);
		if (pos == null || level == null || dimension == null || !dimension.equals(level.dimension()) || !level.isLoaded(pos)) {
			return false;
		}
		return level.getBlockEntity(pos) instanceof IClimateHousing;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!player.isShiftKeyDown()) {
			boolean active = isPreviewModeActive(stack);
			setPreviewMode(stack, !active);
			if (!level.isClientSide()) {
				player.sendSystemMessage(Component.translatable(!active ? "for.habitat_screen.mode.active" : "for.habitat_screen.mode.inactive"));
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();
		ItemStack stack = context.getItemInHand();
		if (player != null && player.isShiftKeyDown()) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof IClimateHousing) {
				stack.set(ClimatologyDataComponents.HABITAT_LINKED_POS.type(), pos.immutable());
				stack.set(ClimatologyDataComponents.HABITAT_LINKED_DIMENSION.type(), level.dimension());
				return InteractionResult.SUCCESS;
			}
		}
		if (!level.isClientSide() && player != null) {
			IClimateState state = IForestryApi.get().getClimateManager().getExactBiomeState(level, pos);
			if (level instanceof ServerLevel serverLevel) {
				IClimateState overlay = IForestryApi.get().getClimateManager().getWorldClimate(serverLevel).getState(pos);
				if (overlay != null && overlay.isPresent()) {
					state = overlay;
				}
			}
			if (state != null && state.isPresent()) {
				player.sendSystemMessage(Component.translatable(
						"for.habitat_screen.status.state",
						ChatFormatting.GOLD + String.format("%.0f%%", state.getTemperature() * 100.0F),
						ChatFormatting.BLUE + String.format("%.0f%%", state.getHumidity() * 100.0F)));
			} else {
				player.sendSystemMessage(Component.translatable("for.habitat_screen.status.nostate"));
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag flag) {
		tooltip.accept(Component.translatable(isPreviewModeActive(stack) ? "for.habitat_screen.mode.active" : "for.habitat_screen.mode.inactive"));
		BlockPos pos = getLinkedPos(stack);
		if (pos != null) {
			ResourceKey<Level> dimension = getLinkedDimension(stack);
			String dim = dimension != null ? dimension.identifier().toString() : "?";
			tooltip.accept(Component.translatable("for.habitat_screen.state.linked", pos.getX(), pos.getY(), pos.getZ(), dim));
		} else {
			tooltip.accept(Component.translatable("for.habitat_screen.state.fail"));
		}
	}
}
