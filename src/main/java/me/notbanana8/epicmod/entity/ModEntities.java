package me.notbanana8.epicmod.entity;


import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.custom.KarinEntity;
import me.notbanana8.epicmod.entity.custom.NimrodEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<NimrodEntity> NIMROD = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(EpicMod.MOD_ID,"nimrod"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NimrodEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.9f)).build());

    public static final EntityType<KarinEntity> KARIN = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(EpicMod.MOD_ID,"karin"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, KarinEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 4.5f)).build());
}