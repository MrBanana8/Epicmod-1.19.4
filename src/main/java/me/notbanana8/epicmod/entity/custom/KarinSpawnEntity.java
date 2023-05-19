package me.notbanana8.epicmod.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class KarinSpawnEntity extends Entity {
    private float maxRange = 10.0F;
    private float cooldown = 0;
    private float t = 0;


    boolean spawned = false;

    public KarinSpawnEntity(EntityType<?> type, World world) {
        super(type, world);
    }


    public void spawningSequence(){
        if(spawned == true) return;
        KarinEntity karin = new KarinEntity(this.world,this.getX(),this.getY(),this.getZ());
        Entity closestPlayer = this.getWorld().getClosestPlayer(this,maxRange);
        if(closestPlayer == null) return;
        
        if(this.squaredDistanceTo(closestPlayer) > maxRange && this.squaredDistanceTo(closestPlayer) < maxRange + 2.0F){
            closestPlayer.setVelocity(this.getPos().subtract(closestPlayer.getPos()).normalize());
        }

        if (this.squaredDistanceTo(closestPlayer) <= maxRange){
            t++;
            if(t == 100) {
                spawned = true;
                world.spawnEntity(karin);
                this.remove(RemovalReason.DISCARDED);
                t = 0;
            }
            for(int i = 0; i < t ;i++){
                this.world.addParticle(ParticleTypes.DRAGON_BREATH,
                        this.getX() + Math.cos(t) * 3,
                        this.getY() + t*0.04,
                        this.getZ() + Math.sin(t) * 3,
                        0,  0 , 0);
            }

            if(t % 20 == 0){
                this.world.playSound(closestPlayer,this.getBlockPos(), SoundEvents.ENTITY_ARMOR_STAND_HIT, SoundCategory.HOSTILE, 5F,0);}
            else if (t == 99) {
                this.world.playSound(closestPlayer,this.getBlockPos(),SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT,
                        SoundCategory.HOSTILE, 1F,2);
            }
        }
    }

    @Override
    public void tick() {
        spawningSequence();
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
