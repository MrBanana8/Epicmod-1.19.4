package me.notbanana8.epicmod.entity.custom;

import me.notbanana8.epicmod.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class KarinProjectile extends ThrownEntity {
    private final float DAMAGE = 8.0f;


    public KarinProjectile(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    protected KarinProjectile(World world,double x, double y, double z) {
        super(ModEntities.KARIN_PROJ,world);
        updatePosition(x,y,z);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        Entity target = this.getWorld().getClosestPlayer(this,2.5F);
        if (target != null){
            if (!this.getWorld().isClient) {
                target.damage(this.getDamageSources().magic(), DAMAGE);
            }
        }
        this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 1.0f, false, World.ExplosionSourceType.MOB);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        LivingEntity target = (LivingEntity) entityHitResult.getEntity();
        target.damage(this.getDamageSources().magic(), DAMAGE);
        this.getWorld().createExplosion(this, getX(), getY(), getZ(),
                1, false, World.ExplosionSourceType.MOB);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,100), this);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,100), this);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected float getGravity() {
        return 0F;
    }

    @Override
    protected void initDataTracker() {
    }
}
