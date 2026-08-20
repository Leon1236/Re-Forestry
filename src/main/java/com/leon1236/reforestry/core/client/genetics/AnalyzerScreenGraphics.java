package com.leon1236.reforestry.core.client.genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.api.client.genetics.IAnalyzerGraphics;
import com.leon1236.reforestry.api.core.IProduct;
import com.leon1236.reforestry.api.genetics.ForestrySpeciesTypes;
import com.leon1236.reforestry.api.genetics.IBreedingTracker;
import com.leon1236.reforestry.api.genetics.IGenome;
import com.leon1236.reforestry.api.genetics.IMutation;
import com.leon1236.reforestry.api.genetics.alleles.IAllele;
import com.leon1236.reforestry.api.genetics.alleles.IRegistryAlleleValue;
import com.leon1236.reforestry.api.genetics.chromosomes.IChromosome;
import com.leon1236.reforestry.api.genetics.chromosomes.IRegistryChromosome;
import com.leon1236.reforestry.apiculture.genetics.ApicultureGenetics;
import com.leon1236.reforestry.arboriculture.genetics.ArboricultureGenetics;
import com.leon1236.reforestry.core.client.ScreenPortableAnalyzer;
import com.leon1236.reforestry.core.genetics.mutations.EnumMutateChance;
import com.leon1236.reforestry.core.genetics.mutations.Mutation;
import com.leon1236.reforestry.core.genetics.root.BreedingTrackerManager;

public class AnalyzerScreenGraphics implements IAnalyzerGraphics {
	public static final int COLUMN_1 = 78;
	public static final int COLUMN_2 = 143;

	private static final int LABEL_COLOR = 0xFF404040;
	private static final int RESEARCHED_PLUS_COLOR = 0xFF000000;
	private static final Identifier TEXTURE = ReForestry.id("textures/gui/portablealyzer.png");

	private final GuiGraphicsExtractor graphics;
	private final Font font;
	private final IGenome genome;

	private boolean haploid;
	private int currentX;
	private int currentY;

	public AnalyzerScreenGraphics(GuiGraphicsExtractor graphics, Font font, IGenome genome) {
		this.graphics = graphics;
		this.font = font;
		this.genome = genome;
		this.currentX = 12;
		this.currentY = 12;
	}

	@Override
	public void drawChromosomeRow(IChromosome<? extends IAllele> chromosome) {
		@SuppressWarnings("unchecked")
		IChromosome<IAllele> typed = (IChromosome<IAllele>) chromosome;
		IAllele active = this.genome.getActiveAllele(typed);
		IAllele inactive = this.genome.getInactiveAllele(typed);

		drawColored(typed.getChromosomeDisplayName(), 0, LABEL_COLOR);
		drawColored(typed.getDisplayName(active), COLUMN_1, colorForDominance(active.dominant()));
		if (!this.haploid) {
			drawColored(typed.getDisplayName(inactive), COLUMN_2, colorForDominance(inactive.dominant()));
		}
		addLineSpacing(1);
	}

	@Override
	public void drawSpeciesHeader() {
		drawSpeciesIconsRow(null);
	}

	@Override
	public void drawSpeciesIconsRow(@Nullable Function<Identifier, ItemStack> iconGetter) {
		drawColored(Component.translatable("for.gui.active"), COLUMN_1, LABEL_COLOR);
		if (iconGetter != null) {
			drawIcon(COLUMN_1 + 43, -2, activeSpeciesId(), iconGetter);
		}

		if (!this.haploid) {
			drawColored(Component.translatable("for.gui.inactive"), COLUMN_2, LABEL_COLOR);
			if (iconGetter != null) {
				drawIcon(COLUMN_2 + 43, -2, inactiveSpeciesId(), iconGetter);
			}
		}

		addLineSpacing(2);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S> void drawProductList(Function<S, List<IProduct>> getProducts) {
		S active = (S) activeSpeciesValue();
		S inactive = (S) inactiveSpeciesValue();
		if (active == null) {
			return;
		}

		ArrayList<ItemStack> stacks;
		if (active == inactive || this.haploid) {
			List<IProduct> products = getProducts.apply(active);
			stacks = new ArrayList<>(products.size());
			for (IProduct product : products) {
				stacks.add(product.createStack());
			}
		} else {
			List<IProduct> activeProducts = getProducts.apply(active);
			List<IProduct> inactiveProducts = getProducts.apply(inactive);
			ObjectOpenCustomHashSet<IProduct> seen = new ObjectOpenCustomHashSet<>(activeProducts.size(), IProduct.ITEM_ONLY_STRATEGY);
			stacks = new ArrayList<>(activeProducts.size() + inactiveProducts.size());
			for (IProduct product : activeProducts) {
				seen.add(product);
				stacks.add(product.createStack());
			}
			for (IProduct product : inactiveProducts) {
				if (!seen.contains(product)) {
					stacks.add(product.createStack());
				}
			}
		}

		int x = 0;
		int y = 0;
		for (ItemStack stack : stacks) {
			drawItemStack(x, y, stack);
			x += 18;
			if (x > 208) {
				x = 12;
				y += 18;
			}
		}
	}

	@Override
	public void drawText(Component text) {
		drawText(text, 0);
	}

	@Override
	public void drawText(Component text, int xOffset) {
		drawColored(text, xOffset, LABEL_COLOR);
	}

	@Override
	public void drawColoredText(Component text, int color) {
		drawColored(text, 0, color);
	}

	private void drawColored(Component text, int xOffset, int color) {
		this.graphics.text(this.font, text, this.currentX + xOffset, this.currentY, color, false);
	}

	@Override
	public void addLineSpacing(int lines) {
		this.currentY += 12 * lines;
	}

	private void addHorizontalSpacing(int x) {
		this.currentX += x;
	}

	private void addVerticalSpacing(int y) {
		this.currentY += y;
	}

	@Override
	public void setHaploid(boolean haploid) {
		this.haploid = haploid;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void drawTaxonomyPage(IGenome genome) {
		drawText(Component.translatable("for.gui.alyzer.classification").append(":"));
		addLineSpacing(1);

		IChromosome<?> speciesChromosome = genome.karyotype().speciesChromosome();
		if (!(speciesChromosome instanceof IRegistryChromosome<?> registry)) {
			drawText(Component.translatable("for.gui.alyzer.nodescription"));
			return;
		}

		IRegistryAlleleValue active = genome.getActiveAllele((IRegistryChromosome<IRegistryAlleleValue>) registry).value();
		MutableComponent speciesName = Component.translatable(
				"allele." + registry.id().getNamespace() + '.' + registry.id().getPath() + '.' + active.id().getPath());
		drawColored(speciesName, 0, 0xFF7FFF00);
		addLineSpacing(1);
		drawText(Component.translatable("for.gui.alyzer.authority").append(": Sengir"));
		addLineSpacing(2);
		drawText(Component.translatable("for.gui.alyzer.nodescription"));
	}

	@Override
	@SuppressWarnings("unchecked")
	public void drawMutationsPage(IGenome genome, Function<Identifier, ItemStack> iconGetter) {
		drawText(Component.translatable("for.gui.beealyzer.mutations").append(":"));
		addLineSpacing(1);

		IChromosome<?> speciesChromosome = genome.karyotype().speciesChromosome();
		if (!(speciesChromosome instanceof IRegistryChromosome<?> registry)) {
			return;
		}

		Identifier typeId = registry.id();
		Identifier speciesId = genome.getActiveAllele((IRegistryChromosome<IRegistryAlleleValue>) registry).value().id();
		Player player = Minecraft.getInstance().player;
		if (player == null) {
			return;
		}

		IBreedingTracker tracker = BreedingTrackerManager.INSTANCE.getTracker(typeId, player.level(), player.getGameProfile());
		int col = 0;
		for (IMutation mutation : mutationsFrom(typeId, speciesId)) {
			if (mutation.isSecret()) {
				continue;
			}

			drawMutation(mutation, speciesId, tracker, iconGetter);
			addHorizontalSpacing(50);

			if (++col >= 4) {
				col = 0;
				addHorizontalSpacing(-200);
				addVerticalSpacing(16);
			}
		}
	}

	private void drawMutation(IMutation mutation, Identifier speciesId, IBreedingTracker tracker,
			Function<Identifier, ItemStack> iconGetter) {
		boolean discovered = tracker.isDiscovered(mutation);
		boolean researched = tracker.isResearched(mutation);

		if (discovered) {
			drawIcon(0, 0, mutation.getPartner(speciesId), iconGetter);
			drawIcon(34, 0, mutation.result(), iconGetter);
		} else {
			this.graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.currentX, this.currentY, 78.0f, 240.0f, 16, 16, 256, 256);
			this.graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.currentX + 33, this.currentY, 78.0f, 240.0f, 16, 16, 256, 256);
		}

		int textureU = 100 + 15 * switch (EnumMutateChance.rateChance(mutation.getChance())) {
			case HIGHER -> 1;
			case HIGH -> 2;
			case NORMAL -> 3;
			case LOW -> 4;
			case LOWEST -> 5;
			default -> 0;
		};
		this.graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.currentX + 18, this.currentY + 4,
				(float) textureU, 247.0f, 15, 9, 256, 256);
		if (researched) {
			addVerticalSpacing(5);
			drawColored(Component.literal("+"), 27, RESEARCHED_PLUS_COLOR);
			addVerticalSpacing(-5);
		}
	}

	private static List<Mutation> mutationsFrom(Identifier typeId, Identifier speciesId) {
		if (ForestrySpeciesTypes.BEE.equals(typeId)) {
			return ApicultureGenetics.getMutationsFrom(speciesId);
		}
		if (ForestrySpeciesTypes.TREE.equals(typeId)) {
			return ArboricultureGenetics.getMutationsFrom(speciesId);
		}
		return List.of();
	}

	private void drawIcon(int x, int y, @Nullable Identifier speciesId, Function<Identifier, ItemStack> iconGetter) {
		if (speciesId == null) {
			return;
		}
		ItemStack stack = iconGetter.apply(speciesId);
		if (stack == null || stack.isEmpty()) {
			return;
		}
		drawItemStack(x, y, stack);
	}

	private void drawItemStack(int x, int y, ItemStack stack) {
		int drawX = this.currentX + x;
		int drawY = this.currentY + y;
		this.graphics.fakeItem(stack, drawX, drawY);
		this.graphics.itemDecorations(this.font, stack, drawX, drawY);
	}

	@Nullable
	private IRegistryAlleleValue activeSpeciesValue() {
		return speciesValue(true);
	}

	@Nullable
	private IRegistryAlleleValue inactiveSpeciesValue() {
		return speciesValue(false);
	}

	@Nullable
	private Identifier activeSpeciesId() {
		IRegistryAlleleValue value = activeSpeciesValue();
		return value == null ? null : value.id();
	}

	@Nullable
	private Identifier inactiveSpeciesId() {
		IRegistryAlleleValue value = inactiveSpeciesValue();
		return value == null ? null : value.id();
	}

	@Nullable
	@SuppressWarnings("unchecked")
	private IRegistryAlleleValue speciesValue(boolean active) {
		IChromosome<?> speciesChromosome = this.genome.karyotype().speciesChromosome();
		if (!(speciesChromosome instanceof IRegistryChromosome<?> registry)) {
			return null;
		}
		IRegistryChromosome<IRegistryAlleleValue> typed = (IRegistryChromosome<IRegistryAlleleValue>) registry;
		return active
				? this.genome.getActiveAllele(typed).value()
				: this.genome.getInactiveAllele(typed).value();
	}

	@Override
	public int colorForDominance(boolean dominant) {
		return ScreenPortableAnalyzer.getColorCoding(dominant);
	}
}
