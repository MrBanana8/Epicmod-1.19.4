package me.notbanana8.epicmod;

import me.notbanana8.epicmod.entity.ModEntities;
import me.notbanana8.epicmod.entity.client.KarinSpawnEntityRenderer;
import me.notbanana8.epicmod.entity.client.KarinRenderer;
import me.notbanana8.epicmod.entity.client.KarinProjRenderer;
import me.notbanana8.epicmod.entity.client.NimrodRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EpicModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.NIMROD, NimrodRenderer::new);
        EntityRendererRegistry.register(ModEntities.KARIN, KarinRenderer::new);
        EntityRendererRegistry.register(ModEntities.KARIN_PROJ,KarinProjRenderer::new);
        EntityRendererRegistry.register(ModEntities.KARIN_SPAWN, KarinSpawnEntityRenderer::new);
    }
}
