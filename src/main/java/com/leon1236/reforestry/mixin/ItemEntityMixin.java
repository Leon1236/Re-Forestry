package com.leon1236.reforestry.mixin;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.stats.Stats;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.core.genetics.PickupHandlerGenetics;
import com.leon1236.reforestry.lepidopterology.items.ItemButterflyGE;
import com.leon1236.reforestry.storage.PickupHandlerStorage;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
	@Shadow
	@Nullable
	private UUID target;

	@Inject(method = "playerTouch", at = @At("HEAD"), cancellable = true)
	private void reforestry$stowPickup(Player player, CallbackInfo ci) {
		ItemEntity entity = (ItemEntity) (Object) this;
		if (player.level().isClientSide() || entity.hasPickUpDelay()) {
			return;
		}
		if (this.target != null && !this.target.equals(player.getUUID())) {
			return;
		}
		ItemStack stack = entity.getItem();
		if (stack.isEmpty()) {
			return;
		}
		PickupHandlerGenetics.onItemPickup(player, entity);
		Item item = stack.getItem();
		int count = stack.getCount();
		if (PickupHandlerStorage.onItemPickup(player, entity)) {
			player.take(entity, count);
			entity.discard();
			stack.setCount(count);
			player.awardStat(Stats.ITEM_PICKED_UP.get(item), count);
			player.onItemPickup(entity);
			ci.cancel();
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void reforestry$butterflyReFlutter(CallbackInfo ci) {
		ItemButterflyGE.onEntityItemUpdate((ItemEntity) (Object) this);
	}
}
