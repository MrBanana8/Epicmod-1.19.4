package me.notbanana8.epicmod.entity.client;

import me.notbanana8.epicmod.entity.custom.KarinSpawnEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KarinSpawnEntityRenderer extends EntityRenderer<KarinSpawnEntity> {
    public KarinSpawnEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(KarinSpawnEntity entity) {
        return null;
    }
}
