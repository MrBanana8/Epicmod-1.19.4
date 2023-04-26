package me.notbanana8.epicmod.entity.client;

import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.custom.NimrodEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NimrodRenderer extends GeoEntityRenderer<NimrodEntity> {
    public NimrodRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new NimrodModel());
    }

    @Override
    public Identifier getTextureLocation(NimrodEntity animatable) {
        return new Identifier(EpicMod.MOD_ID, "textures/entity/nimrod_texture.png");
    }

    @Override
    public void render(NimrodEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
