package com.leon1236.reforestry.apiculture.genetics.effects;

import java.util.List;

import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import com.leon1236.reforestry.api.apiculture.BeeManager;
import com.leon1236.reforestry.api.apiculture.ForestryBeeEffects;
import com.leon1236.reforestry.api.apiculture.IBeeHousing;
import com.leon1236.reforestry.api.genetics.IEffectData;
import com.leon1236.reforestry.api.genetics.IGenome;

public class GuardianBeeEffect extends ThrottledBeeEffect {
	public GuardianBeeEffect() {
		super(ForestryBeeEffects.GUARDIAN, true, 1200, true, true);
	}

	@Override
	public IEffectData doEffectThrottled(IGenome genome, IEffectData storedData, IBeeHousing housing) {
		List<Player> list = getEntitiesInRange(genome, housing, Player.class);
		for (Player player : list) {
			if (!player.hasEffect(MobEffects.MINING_FATIGUE)) {
				int count = BeeManager.armorApiaristHelper.wearsItems(player, this, true);
				if (count >= 4) {
					continue;
				}
				player.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 6000 - 1500 * count, 2));
				if (player instanceof ServerPlayer serverPlayer) {
					serverPlayer.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.GUARDIAN_ELDER_EFFECT, 1F));
				}
			}
		}
		return storedData;
	}
}
