package com.leon1236.reforestry.core.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import org.jspecify.annotations.Nullable;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.core.blocks.BlockMachine;
import com.leon1236.reforestry.core.tiles.TileAnalyzer;

@Environment(EnvType.CLIENT)
public class RenderAnalyzer implements BlockEntityRenderer<TileAnalyzer, AnalyzerRenderState> {
	private static final String TOWER2 = "tower2";
	private static final String TOWER1 = "tower1";
	private static final String COVER = "cover";
	private static final String PEDESTAL = "pedestal";

	private final SpriteGetter sprites;
	private final ItemModelResolver itemModelResolver;
	private final ModelPart pedestal;
	private final ModelPart cover;
	private final ModelPart tower1;
	private final ModelPart tower2;
	private final SpriteId texturePedestal;
	private final SpriteId textureTower1;
	private final SpriteId textureTower2;

	public RenderAnalyzer(BlockEntityRendererProvider.Context ctx) {
		this.sprites = ctx.sprites();
		this.itemModelResolver = ctx.itemModelResolver();
		ModelPart root = ctx.bakeLayer(ForestryModelLayers.ANALYZER_LAYER);
		this.pedestal = root.getChild(PEDESTAL);
		this.cover = root.getChild(COVER);
		this.tower1 = root.getChild(TOWER1);
		this.tower2 = root.getChild(TOWER2);
		this.texturePedestal = blockSprite("analyzer_pedestal");
		this.textureTower1 = blockSprite("analyzer_tower1");
		this.textureTower2 = blockSprite("analyzer_tower2");
	}

	private static SpriteId blockSprite(String path) {
		return Sheets.BLOCKS_MAPPER.apply(ReForestry.id(path));
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild(PEDESTAL, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 16, 1, 16), PartPose.offset(0, 0, 0));
		root.addOrReplaceChild(COVER, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 16, 1, 16), PartPose.offsetAndRotation(16, 16, 0, 0, 0, Mth.PI));
		root.addOrReplaceChild(TOWER1, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 2, 14, 14), PartPose.offset(0, 1, 1));
		root.addOrReplaceChild(TOWER2, CubeListBuilder.create().texOffs(0, 0)
				.addBox(0, 0, 0, 2, 14, 14), PartPose.offset(14, 1, 1));

		return LayerDefinition.create(mesh, 64, 32);
	}

	@Override
	public AnalyzerRenderState createRenderState() {
		return new AnalyzerRenderState();
	}

	@Override
	public void extractRenderState(
			TileAnalyzer analyzer,
			AnalyzerRenderState state,
			float partialTicks,
			Vec3 cameraPosition,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		BlockEntityRenderer.super.extractRenderState(analyzer, state, partialTicks, cameraPosition, breakProgress);
		state.facing = analyzer.getBlockState().hasProperty(BlockMachine.FACING)
				? analyzer.getBlockState().getValue(BlockMachine.FACING)
				: Direction.NORTH;

		ItemStack displayStack = analyzer.getIndividualOnDisplay();
		state.displayItem = new ItemStackRenderState();
		if (!displayStack.isEmpty()) {
			int seed = (int) analyzer.getBlockPos().asLong();
			this.itemModelResolver.updateForTopItem(
					state.displayItem,
					displayStack,
					ItemDisplayContext.GROUND,
					analyzer.getLevel(),
					null,
					seed);
			Level level = analyzer.getLevel();
			if (level != null) {
				float smoothTick = level.getGameTime() + partialTicks;
				state.itemBob = Mth.sin(smoothTick / 10.0f) * 0.1f + 0.1f;
				state.itemSpin = smoothTick / 20.0f;
			}
		}
	}

	@Override
	public void submit(
			AnalyzerRenderState state,
			PoseStack poseStack,
			SubmitNodeCollector submitNodeCollector,
			CameraRenderState camera
	) {
		poseStack.pushPose();
		RenderUtil.rotateByHorizontalDirection(poseStack, state.facing);

		submitPart(submitNodeCollector, poseStack, this.pedestal, this.texturePedestal, state.lightCoords, state.breakProgress);
		submitPart(submitNodeCollector, poseStack, this.cover, this.texturePedestal, state.lightCoords, state.breakProgress);
		submitPart(submitNodeCollector, poseStack, this.tower1, this.textureTower1, state.lightCoords, state.breakProgress);
		submitPart(submitNodeCollector, poseStack, this.tower2, this.textureTower2, state.lightCoords, state.breakProgress);

		poseStack.popPose();

		if (!state.displayItem.isEmpty()) {
			poseStack.pushPose();
			poseStack.translate(0.5f, 0.2f + state.itemBob, 0.5f);
			poseStack.mulPose(Axis.YP.rotation(state.itemSpin));
			state.displayItem.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
			poseStack.popPose();
		}
	}

	private void submitPart(
			SubmitNodeCollector collector,
			PoseStack poseStack,
			ModelPart part,
			SpriteId spriteId,
			int light,
			ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
	) {
		TextureAtlasSprite sprite = this.sprites.get(spriteId);
		collector.submitModelPart(
				part,
				poseStack,
				spriteId.renderType(RenderTypes::entityCutout),
				light,
				OverlayTexture.NO_OVERLAY,
				sprite,
				-1,
				breakProgress);
	}
}
