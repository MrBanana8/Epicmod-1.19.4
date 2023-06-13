package me.notbanana8.epicmod;

import me.notbanana8.epicmod.entity.ModEntities;
import me.notbanana8.epicmod.entity.client.KarinSpawnEntityRenderer;
import me.notbanana8.epicmod.entity.client.KarinRenderer;
import me.notbanana8.epicmod.entity.client.KarinProjRenderer;
import me.notbanana8.epicmod.entity.client.NimrodRenderer;
import me.notbanana8.epicmod.entity.custom.ExplosiveRabbitRenderer;
import me.notbanana8.epicmod.item.Hat;
import me.notbanana8.epicmod.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.item.ItemStack;

public class EpicModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.NIMROD, NimrodRenderer::new);
        EntityRendererRegistry.register(ModEntities.KARIN, KarinRenderer::new);
        EntityRendererRegistry.register(ModEntities.KARIN_PROJ,KarinProjRenderer::new);
        EntityRendererRegistry.register(ModEntities.KARIN_SPAWN, KarinSpawnEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_RABBIT, ExplosiveRabbitRenderer::new);

        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {

            MinecraftClient client = MinecraftClient.getInstance();
            VertexConsumerProvider.Immediate context = client.getBufferBuilders().getEntityVertexConsumers();
            if (client.player != null) {
                ItemStack heldItem = client.player.getMainHandStack();
                ItemStack offHandItem = client.player.getMainHandStack();
                if (heldItem.getItem() == ModItems.HAT || offHandItem.getItem() == ModItems.HAT) {

                    Hat hat = (Hat) heldItem.getItem();
                    hat.renderHud(matrices.getMatrices(), new DrawContext(client,context));
                }
            }
        });
    }
}
