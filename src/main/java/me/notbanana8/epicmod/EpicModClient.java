package me.notbanana8.epicmod;


import me.notbanana8.epicmod.entity.ModEntities;
import me.notbanana8.epicmod.entity.client.NimrodRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EpicModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.NIMROD, NimrodRenderer::new);
    }
}
