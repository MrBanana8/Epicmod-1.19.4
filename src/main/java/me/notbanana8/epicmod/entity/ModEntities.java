package me.notbanana8.epicmod.entity;


import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.custom.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
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
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,(EntityType.EntityFactory<KarinEntity>)KarinEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 4.5f)).fireImmune().build());

    public static final EntityType<KarinSpawnEntity> KARIN_SPAWN = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(EpicMod.MOD_ID,"karin_spawn"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, KarinSpawnEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static final EntityType<KarinProjectile> KARIN_PROJ = Registry.register(
            Registries.ENTITY_TYPE,new Identifier(EpicMod.MOD_ID,"karin_proj"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, KarinProjectile::new)
                    .dimensions(EntityDimensions.fixed(0.4F, 0.4F)).build());

    public static final EntityType<ExplosiveRabbit> EXPLOSIVE_RABBIT = Registry.register(
            Registries.ENTITY_TYPE,new Identifier(EpicMod.MOD_ID,"explosive_rabbit"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ExplosiveRabbit::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.5f)).build());

}