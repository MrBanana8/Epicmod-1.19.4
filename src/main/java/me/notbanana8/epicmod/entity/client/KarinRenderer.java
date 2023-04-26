package me.notbanana8.epicmod.entity.client;

import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.custom.KarinEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class KarinRenderer extends GeoEntityRenderer<KarinEntity> {
    public KarinRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new KarinModel());
    }

    @Override
    public Identifier getTextureLocation(KarinEntity animatable){
        return new Identifier(EpicMod.MOD_ID,"textures/entity/karin_texture.png");
    }

    @Override
    public void render(KarinEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}
