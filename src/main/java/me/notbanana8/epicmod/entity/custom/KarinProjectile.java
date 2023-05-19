package me.notbanana8.epicmod.entity.custom;

import me.notbanana8.epicmod.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class KarinProjectile extends ThrownEntity {


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
            if (!this.world.isClient) {
                target.damage(this.getDamageSources().magic(), 5.0f);
            }
        }
        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 1.0f, false, World.ExplosionSourceType.MOB);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity target = entityHitResult.getEntity();
        Vec3d targetPos = target.getEyePos();
        Vec3d shootDirection = targetPos.subtract(targetPos).normalize();
        target.damage(this.getDamageSources().magic(), 5.0f);
        this.world.createExplosion(this, getX(), getY(), getZ(), 1, false, World.ExplosionSourceType.MOB);
        //target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON,200),this);
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
