package com.leon1236.reforestry.arboriculture.client;

import java.util.EnumMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;

import org.joml.Quaternionf;

import com.leon1236.reforestry.ReForestry;
import com.leon1236.reforestry.arboriculture.ForestryWoodType;
import com.leon1236.reforestry.arboriculture.entities.IForestryBoat;

@Environment(EnvType.CLIENT)
public class ForestryBoatRenderer<T extends AbstractBoat & IForestryBoat> extends EntityRenderer<T, ForestryBoatRenderer.ForestryBoatRenderState> {
    public static final ModelLayerLocation BOAT_MODEL_LAYER = new ModelLayerLocation(ReForestry.id("boat"), "main");
    public static final ModelLayerLocation CHEST_BOAT_MODEL_LAYER = new ModelLayerLocation(ReForestry.id("chest_boat"), "main");

    private final Model.Simple waterPatchModel;
    private final EntityModel<BoatRenderState> model;
    private final EnumMap<ForestryWoodType, Identifier> textures;

    public ForestryBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context);
        this.shadowRadius = 0.8F;
        this.waterPatchModel = new Model.Simple(context.bakeLayer(ModelLayers.BOAT_WATER_PATCH), t -> RenderTypes.waterMask());
        ModelLayerLocation modelLayer = hasChest ? CHEST_BOAT_MODEL_LAYER : BOAT_MODEL_LAYER;
        this.model = new BoatModel(context.bakeLayer(modelLayer));
        this.textures = new EnumMap<>(ForestryWoodType.class);
        String folder = hasChest ? "textures/entity/chest_boat/" : "textures/entity/boat/";
        for (ForestryWoodType type : ForestryWoodType.VALUES) {
            this.textures.put(type, ReForestry.id(folder + type.getSerializedName() + ".png"));
        }
    }

    @Override
    public void submit(ForestryBoatRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - state.yRot));
        float hurt = state.hurtTime;
        if (hurt > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(hurt) * hurt * state.damageTime / 10.0F * state.hurtDir));
        }

        if (!state.isUnderWater && !Mth.equal(state.bubbleAngle, 0.0F)) {
            poseStack.mulPose(new Quaternionf().setAngleAxis(state.bubbleAngle * (float) (Math.PI / 180.0), 1.0F, 0.0F, 1.0F));
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        submitNodeCollector.submitModel(this.model, state, poseStack, state.texture, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        if (!state.isUnderWater) {
            submitNodeCollector.submitModel(
                    this.waterPatchModel, Unit.INSTANCE, poseStack, state.texture, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        }
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public ForestryBoatRenderState createRenderState() {
        return new ForestryBoatRenderState();
    }

    @Override
    public void extractRenderState(T entity, ForestryBoatRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.yRot = entity.getYRot(partialTicks);
        state.hurtTime = entity.getHurtTime() - partialTicks;
        state.hurtDir = entity.getHurtDir();
        state.damageTime = Math.max(entity.getDamage() - partialTicks, 0.0F);
        state.bubbleAngle = entity.getBubbleAngle(partialTicks);
        state.isUnderWater = entity.isUnderWater();
        state.rowingTimeLeft = entity.getRowingTime(0, partialTicks);
        state.rowingTimeRight = entity.getRowingTime(1, partialTicks);
        state.texture = this.textures.get(entity.getWoodType());
    }

    @Environment(EnvType.CLIENT)
    public static class ForestryBoatRenderState extends BoatRenderState {
        public Identifier texture = Identifier.withDefaultNamespace("missingno");
    }
}
