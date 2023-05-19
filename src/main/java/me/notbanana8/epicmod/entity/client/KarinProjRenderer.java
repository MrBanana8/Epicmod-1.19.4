package me.notbanana8.epicmod.entity.client;

import me.notbanana8.epicmod.entity.custom.KarinProjectile;
import me.notbanana8.epicmod.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class KarinProjRenderer extends EntityRenderer<KarinProjectile> {
    public static final ItemStack STACK = new ItemStack(ModItems.NIMROD);

    public KarinProjRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(KarinProjectile entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MinecraftClient.getInstance().getItemRenderer().renderItem(
                STACK,
                ModelTransformationMode.FIXED,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,null,0
        );
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(KarinProjectile entity) {
        return null;
    }
}
