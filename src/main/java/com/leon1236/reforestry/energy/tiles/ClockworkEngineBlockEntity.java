package com.leon1236.reforestry.energy.tiles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import com.leon1236.reforestry.core.damage.CoreDamageTypes;
import com.leon1236.reforestry.core.tiles.TemperatureState;
import com.leon1236.reforestry.energy.EnergyConstants;
import com.leon1236.reforestry.energy.features.EnergyTiles;

public class ClockworkEngineBlockEntity extends EngineBlockEntity {
	private float tension;
	private int delay;

	public ClockworkEngineBlockEntity(BlockPos pos, BlockState state) {
		super(EnergyTiles.CLOCKWORK_ENGINE.type(), pos, state, "",
				EnergyConstants.ENGINE_CLOCKWORK_HEAT_MAX, EnergyConstants.ENGINE_CLOCKWORK_MAX_ENERGY);
	}

	@Override
	protected boolean hasGui() {
		return false;
	}

	@Override
	public void openGui(Player player) {
		if (!(player instanceof ServerPlayer)) {
			return;
		}

		if (this.tension <= 0) {
			this.tension = EnergyConstants.ENGINE_CLOCKWORK_WIND_TENSION_BASE;
		} else if (this.tension < EnergyConstants.ENGINE_CLOCKWORK_WIND_MAX
				+ EnergyConstants.ENGINE_CLOCKWORK_WIND_TENSION_BASE) {
			this.tension += (EnergyConstants.ENGINE_CLOCKWORK_WIND_MAX
					+ EnergyConstants.ENGINE_CLOCKWORK_WIND_TENSION_BASE - this.tension)
					/ (EnergyConstants.ENGINE_CLOCKWORK_WIND_MAX + EnergyConstants.ENGINE_CLOCKWORK_WIND_TENSION_BASE)
					* EnergyConstants.ENGINE_CLOCKWORK_WIND_TENSION_BASE;
		} else {
			return;
		}

		player.causeFoodExhaustion(EnergyConstants.ENGINE_CLOCKWORK_WIND_EXHAUSTION);
		if (this.tension > EnergyConstants.ENGINE_CLOCKWORK_WIND_MAX
				+ 0.1 * EnergyConstants.ENGINE_CLOCKWORK_WIND_TENSION_BASE
				&& this.level instanceof ServerLevel serverLevel) {
			player.hurtServer(serverLevel, CoreDamageTypes.source(serverLevel, CoreDamageTypes.CLOCKWORK),
					EnergyConstants.ENGINE_CLOCKWORK_OVERWIND_DAMAGE);
		}
		this.tension = Math.min(this.tension,
				EnergyConstants.ENGINE_CLOCKWORK_WIND_MAX + EnergyConstants.ENGINE_CLOCKWORK_WIND_TENSION_BASE);
		this.delay = EnergyConstants.ENGINE_CLOCKWORK_WIND_DELAY;
		setChanged();
		syncToClient();
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		this.tension = input.getFloatOr("tension", 0.0f);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		output.putFloat("tension", this.tension);
	}

	@Override
	public boolean isRedstoneActivated() {
		return true;
	}

	@Override
	public void dissipateHeat() {
	}

	@Override
	public void generateHeat() {
	}

	@Override
	public boolean mayBurn() {
		return true;
	}

	@Override
	public void burn() {
		this.heat = (int) (this.tension * 10000);

		if (this.delay > 0) {
			this.delay--;
			return;
		}

		if (!isBurning()) {
			return;
		}

		if (this.tension > 0.01f) {
			this.tension *= 0.9995f;
		} else {
			this.tension = 0;
		}
		generateEnergy(Math.max(0, (int) (EnergyConstants.ENGINE_CLOCKWORK_ENERGY_PER_CYCLE * this.tension)));
		this.level.updateNeighbourForOutputSignal(this.worldPosition, getBlockState().getBlock());
	}

	@Override
	protected boolean isBurning() {
		return this.tension > 0;
	}

	@Override
	public TemperatureState getTemperatureState() {
		TemperatureState state = TemperatureState.getState(this.heat / 10000, EnergyConstants.ENGINE_CLOCKWORK_WIND_MAX);
		if (state == TemperatureState.MELTING) {
			state = TemperatureState.OVERHEATING;
		}
		return state;
	}

	@Override
	protected float getPistonSpeed() {
		if (this.delay > 0) {
			return 0;
		}

		float fromClockwork = this.tension / EnergyConstants.ENGINE_CLOCKWORK_WIND_MAX
				* EnergyConstants.ENGINE_PISTON_SPEED_MAX;
		return Math.round(fromClockwork * 100f) / 100f;
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return null;
	}
}
