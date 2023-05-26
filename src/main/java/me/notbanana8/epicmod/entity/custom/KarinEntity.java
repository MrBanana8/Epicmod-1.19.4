package me.notbanana8.epicmod.entity.custom;

import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class KarinEntity extends HostileEntity implements GeoEntity, RangedAttackMob {
    boolean temp = false;
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final ServerBossBar bossBar = (ServerBossBar) new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS).setDarkenSky(true);

    public KarinEntity(World world, double x, double y, double z) {
        super(ModEntities.KARIN, world);
        this.setPosition(x, y, z);
    }

    public KarinEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 250f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 20.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 10.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1f);
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new ProjectileAttackGoal(this, 1.0, 20, 20.0f));
        goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        initCustomGoals();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    private void initCustomGoals(){

        if(this.getTarget() != null){
            if(this.getHealth() <= this.getMaxHealth()/2 && temp == false){
                goalSelector.remove(new ProjectileAttackGoal(this, 1.0, 20, 20.0f));
                goalSelector.add(0, new MeleeAttackGoal(this, 1.0, true));
                this.setPositionTarget(this.getTarget().getBlockPos(),5);
                temp = true;
                EpicMod.LOGGER.info("projectile goal");
            }
            //else if (this.distanceTo(this.getTarget()) <= 10.0 && melee == false){
            //    goalSelector.remove(projectileGoal);
            //    goalSelector.add(1, meleeGoal);
            //    melee = true;
            //    projectile = false;
            //    EpicMod.LOGGER.info("melee goal");
            //}
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,"controller",0,this::predicate));
        controllerRegistrar.add(new AnimationController<>(this,"attackController",0,this::attackPredicate));
    }

    private <T extends GeoAnimatable> PlayState attackPredicate(AnimationState<T> tAnimationState) {
        if(this.handSwinging && tAnimationState.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            tAnimationState.getController().forceAnimationReset();
            tAnimationState.getController().setAnimation(RawAnimation.begin().then
                    ("karin.animation.attack", Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if(tAnimationState.isMoving()){
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("karin.animation.walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("karin.animation.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        if(this.hasCustomName()){
            this.bossBar.setName(this.getDisplayName());
        }
        super.readCustomDataFromNbt(nbt);
    }
    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }
    private void shootAt(Entity target) {
        Vec3d shooterPos = new Vec3d(this.getX(), this.getEyeY(), this.getZ());

        Vec3d targetPos = target.getEyePos();
        Vec3d shootDirection = targetPos.subtract(shooterPos).normalize();

        KarinProjectile proj = new KarinProjectile(this.world,shootDirection.getX(),shootDirection.getY(),shootDirection.getZ());
        proj.updatePosition(this.getX(), this.getEyeY(), this.getZ());
        proj.setVelocity(shootDirection);
        this.world.spawnEntity(proj);
    }

    @Override
    public void attack(LivingEntity target, float pullProgress) {
        if (target != null && this.canSee(target)) {
            this.shootAt(target);
        }
    }

    @Override
    public void onAttacking(Entity target) {
        target.setVelocity(target.getVelocity().add(0.0, 1, 0.0));
        super.onAttacking(target);
    }
}
