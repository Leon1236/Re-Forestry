package com.leon1236.reforestry.climatology.client;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.apiculture.genetics.IBeeSpecies;
import com.leon1236.reforestry.api.climate.ClimateType;
import com.leon1236.reforestry.api.climate.IClimateState;
import com.leon1236.reforestry.api.core.HumidityType;
import com.leon1236.reforestry.api.core.TemperatureType;
import com.leon1236.reforestry.api.genetics.capability.IIndividualHandlerItem;
import com.leon1236.reforestry.climatology.gui.ContainerHabitatFormer;
import com.leon1236.reforestry.climatology.network.SelectClimateTargetPayload;
import com.leon1236.reforestry.core.client.ScreenForestry;
import com.leon1236.reforestry.core.climate.ClimateStateHelper;

@Environment(EnvType.CLIENT)
public class ScreenHabitatFormer extends ScreenForestry<ContainerHabitatFormer> {
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/habitat_former.png");
	private static final float MAX_VALUE = 2.0F;

	private static final String[] PRESET_NAMES = {
			"desert", "end", "forest", "hills", "jungle", "mushroom",
			"nether", "ocean", "plains", "snow", "swamp", "taiga"
	};
	private static final float[] PRESET_TEMPERATURE = {
			2.0F, 0.5F, 0.7F, 0.2F, 0.95F, 0.9F,
			2.0F, 0.5F, 0.8F, 0.0F, 0.8F, 0.25F
	};
	private static final float[] PRESET_HUMIDITY = {
			0.0F, 0.5F, 0.8F, 0.3F, 0.9F, 1.0F,
			0.0F, 0.5F, 0.4F, 0.5F, 0.9F, 0.8F
	};

	@Nullable
	private EditBox temperatureEdit;
	@Nullable
	private EditBox humidityEdit;
	private boolean temperatureFocused;
	private boolean humidityFocused;

	public ScreenHabitatFormer(ContainerHabitatFormer menu, Inventory inventory, Component title) {
		super(menu, inventory, title, 176, 233);
		setHintKey("habitatformer");
		addTankClickRegion(152, 17, 16, 58, 0);
	}

	@Override
	protected void init() {
		super.init();
		String oldTemp = this.temperatureEdit != null ? this.temperatureEdit.getValue() : percentString(menu.getTargetTemperature());
		String oldHumid = this.humidityEdit != null ? this.humidityEdit.getValue() : percentString(menu.getTargetHumidity());
		this.temperatureEdit = createClimateField(leftPos + 17, topPos + 103, oldTemp);
		this.humidityEdit = createClimateField(leftPos + 17, topPos + 127, oldHumid);
		addRenderableWidget(this.temperatureEdit);
		addRenderableWidget(this.humidityEdit);
		this.temperatureFocused = false;
		this.humidityFocused = false;
	}

	private EditBox createClimateField(int x, int y, String value) {
		EditBox field = new EditBox(this.font, x, y, 50, 10, Component.empty());
		field.setMaxLength(3);
		field.setBordered(false);
		field.setTextColor(0xFFFFFF);
		field.setValue(value);
		return field;
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		if (this.temperatureEdit != null) {
			if (this.temperatureFocused && !this.temperatureEdit.isFocused()) {
				applyField(ClimateType.TEMPERATURE, this.temperatureEdit.getValue());
			}
			this.temperatureFocused = this.temperatureEdit.isFocused();
			if (!this.temperatureEdit.isFocused()) {
				String value = percentString(menu.getTargetTemperature());
				if (!value.equals(this.temperatureEdit.getValue())) {
					this.temperatureEdit.setValue(value);
				}
			}
		}
		if (this.humidityEdit != null) {
			if (this.humidityFocused && !this.humidityEdit.isFocused()) {
				applyField(ClimateType.HUMIDITY, this.humidityEdit.getValue());
			}
			this.humidityFocused = this.humidityEdit.isFocused();
			if (!this.humidityEdit.isFocused()) {
				String value = percentString(menu.getTargetHumidity());
				if (!value.equals(this.humidityEdit.getValue())) {
					this.humidityEdit.setValue(value);
				}
			}
		}
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256);
		drawBar(graphics, 61, 33, menu.getCurrentTemperature(), menu.getTargetTemperature(), menu.getDefaultTemperature(), 0xFFD700);
		drawBar(graphics, 61, 57, menu.getCurrentHumidity(), menu.getTargetHumidity(), menu.getDefaultHumidity(), 0x7FF4F4);
		int circleU = menu.isCircular() ? 238 : 220;
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 30, topPos + 37, circleU, 110, 18, 18, 256, 256);
		int range = Mth.clamp(menu.getRange(), 1, 16);
		int sliderY = topPos + 17 + (int) ((range - 1) / 15.0F * (58 - 15));
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 10, sliderY, 176, 92, 12, 15, 256, 256);
		int amount = menu.getTankAmountMb();
		int capacity = menu.getTankCapacityMb();
		if (amount > 0 && capacity > 0) {
			int filled = Math.min(58, 58 * amount / capacity);
			graphics.fill(leftPos + 152, topPos + 17 + (58 - filled), leftPos + 168, topPos + 75, 0xAA3F76E4);
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 15, topPos + 101, 204, 22, 52, 12, 256, 256);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 15, topPos + 125, 204, 34, 52, 12, 256, 256);
		for (int i = 0; i < PRESET_NAMES.length; i++) {
			int x = leftPos + 75 + (i % 4) * 20;
			int y = topPos + 98 + (i / 4) * 20;
			graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 204, 46, 20, 20, 256, 256);
			Identifier icon = ReForestry.id("textures/reforestry/atlas/gui/habitats/" + PRESET_NAMES[i] + ".png");
			graphics.blit(RenderPipelines.GUI_TEXTURED, icon, x + 2, y + 2, 0.0F, 0.0F, 16, 16, 16, 16);
		}
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 143, topPos + 108, 224, 46, 22, 22, 256, 256);
	}

	private void drawBar(GuiGraphicsExtractor graphics, int x, int y, float current, float target, float defaults, int color) {
		int progress = (int) (current * 50 / MAX_VALUE);
		int pointer = (int) (target * 49 / MAX_VALUE);
		int def = (int) (defaults * 49 / MAX_VALUE);
		graphics.fill(leftPos + x + 1, topPos + y + 1, leftPos + x + 1 + progress, topPos + y + 11, 0xFF000000 | color);
		graphics.fill(leftPos + x + 1 + def, topPos + y + 1, leftPos + x + 2 + def, topPos + y + 11, 0xFFFFFFFF);
		graphics.fill(leftPos + x + 1 + pointer, topPos + y + 1, leftPos + x + 2 + pointer, topPos + y + 11, 0xFF000000);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + x + 1, topPos + y + 1, 177, 80, 50, 10, 256, 256);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (this.temperatureEdit != null && this.temperatureEdit.isFocused() && event.isConfirmation()) {
			applyField(ClimateType.TEMPERATURE, this.temperatureEdit.getValue());
			this.temperatureEdit.setFocused(false);
			this.temperatureFocused = false;
			return true;
		}
		if (this.humidityEdit != null && this.humidityEdit.isFocused() && event.isConfirmation()) {
			applyField(ClimateType.HUMIDITY, this.humidityEdit.getValue());
			this.humidityEdit.setFocused(false);
			this.humidityFocused = false;
			return true;
		}
		return super.keyPressed(event);
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		double mouseX = event.x();
		double mouseY = event.y();
		if (mouseX >= leftPos + 30 && mouseX < leftPos + 48 && mouseY >= topPos + 37 && mouseY < topPos + 55) {
			this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, ContainerHabitatFormer.BUTTON_CIRCLE);
			return true;
		}
		if (mouseX >= leftPos + 10 && mouseX < leftPos + 22 && mouseY >= topPos + 17 && mouseY < topPos + 75) {
			float quot = (float) ((mouseY - (topPos + 17)) / 58.0);
			int range = Mth.clamp(1 + Math.round(quot * 15), 1, 16);
			this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, ContainerHabitatFormer.BUTTON_RANGE_BASE + range);
			return true;
		}
		if (handleBarClick(mouseX, mouseY, 61, 33, ClimateType.TEMPERATURE) || handleBarClick(mouseX, mouseY, 61, 57, ClimateType.HUMIDITY)) {
			return true;
		}
		for (int i = 0; i < PRESET_NAMES.length; i++) {
			int x = leftPos + 75 + (i % 4) * 20;
			int y = topPos + 98 + (i / 4) * 20;
			if (mouseX >= x && mouseX < x + 20 && mouseY >= y && mouseY < y + 20) {
				sendTarget(ClimateStateHelper.of(PRESET_TEMPERATURE[i], PRESET_HUMIDITY[i]));
				return true;
			}
		}
		if (mouseX >= leftPos + 143 && mouseX < leftPos + 165 && mouseY >= topPos + 108 && mouseY < topPos + 130) {
			ItemStack carried = this.menu.getCarried();
			var individual = IIndividualHandlerItem.getIndividual(carried);
			if (individual != null && individual.getSpecies() instanceof IBeeSpecies species) {
				sendTarget(ClimateStateHelper.of(midTemp(species.getTemperature()), midHumidity(species.getHumidity())));
				return true;
			}
		}
		return super.mouseClicked(event, doubleClick);
	}

	private boolean handleBarClick(double mouseX, double mouseY, int x, int y, ClimateType type) {
		if (mouseX < leftPos + x + 1 || mouseX > leftPos + x + 51 || mouseY < topPos + y + 1 || mouseY > topPos + y + 11) {
			return false;
		}
		float value = Mth.clamp((float) ((mouseX - (leftPos + x + 1)) / 49.0), 0.0F, 1.0F) * MAX_VALUE;
		float temperature = type == ClimateType.TEMPERATURE ? value : this.menu.getTargetTemperature();
		float humidity = type == ClimateType.HUMIDITY ? value : this.menu.getTargetHumidity();
		sendTarget(ClimateStateHelper.of(temperature, humidity));
		return true;
	}

	private void applyField(ClimateType type, String text) {
		int value;
		try {
			value = Integer.parseInt(text);
		} catch (NumberFormatException exception) {
			value = 0;
		}
		float climateValue = Mth.clamp(value / 100.0F, 0.0F, MAX_VALUE);
		float temperature = type == ClimateType.TEMPERATURE ? climateValue : this.menu.getTargetTemperature();
		float humidity = type == ClimateType.HUMIDITY ? climateValue : this.menu.getTargetHumidity();
		sendTarget(ClimateStateHelper.of(temperature, humidity));
	}

	private void sendTarget(IClimateState state) {
		this.menu.setTargetClimate(state);
		if (this.temperatureEdit != null && !this.temperatureEdit.isFocused()) {
			this.temperatureEdit.setValue(percentString(state.getTemperature()));
		}
		if (this.humidityEdit != null && !this.humidityEdit.isFocused()) {
			this.humidityEdit.setValue(percentString(state.getHumidity()));
		}
		ClientPlayNetworking.send(new SelectClimateTargetPayload(
				this.menu.getTile().getBlockPos(), state.getTemperature(), state.getHumidity()));
	}

	private static String percentString(float climate) {
		return Integer.toString((int) (Mth.clamp(climate, 0.0F, MAX_VALUE) * 100));
	}

	private static float midTemp(TemperatureType type) {
		return switch (type) {
			case HELLISH -> 2.0F;
			case HOT -> 1.25F;
			case WARM -> 0.9F;
			case COLD -> 0.15F;
			case ICY -> 0.0F;
			default -> 0.79F;
		};
	}

	private static float midHumidity(HumidityType type) {
		return switch (type) {
			case DAMP -> 0.9F;
			case ARID -> 0.2F;
			default -> 0.4F;
		};
	}
}
