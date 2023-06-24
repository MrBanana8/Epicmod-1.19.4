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
        KarinEntity karin = new KarinEntity(this.getWorld(),this.getX(),this.getY(),this.getZ());
        Entity closestPlayer = this.getWorld().getClosestPlayer(this,maxRange);
        if(closestPlayer == null) return;
        
        if(this.squaredDistanceTo(closestPlayer) > maxRange && this.squaredDistanceTo(closestPlayer) < maxRange + 2.0F){
            closestPlayer.setVelocity(this.getPos().subtract(closestPlayer.getPos()).normalize());
        }

        if (this.squaredDistanceTo(closestPlayer) <= maxRange){
            t++;
            if(t == 100) {
                spawned = true;
                karin.setPosition(getX(),getY()+1,getZ());
                this.getWorld().createExplosion(this,this.getX(),this.getY() + 1.5,this.getZ(),5, World.ExplosionSourceType.MOB);
                this.getWorld().spawnEntity(karin);
                this.remove(RemovalReason.DISCARDED);
                t = 0;
            }
            for(int i = 0; i < t; i++){
                this.getWorld().addParticle(ParticleTypes.DRAGON_BREATH,
                        this.getX() + Math.cos(t) * 3,
                        this.getY() + t*0.04,
                        this.getZ() + Math.sin(t) * 3,
                        0,  0 , 0);
                //spawnParticle(this.getWorld(),this.getBlockPos());
            }

            if(t % 20 == 0){
                this.getWorld().playSound(closestPlayer,this.getBlockPos(), SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.HOSTILE, 5F,0);}
            else if (t == 99) {
                this.getWorld().playSound(closestPlayer,this.getBlockPos(),SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT,
                        SoundCategory.HOSTILE, 1F,2);
            }
        }
    }
    /*
    private void spawnParticle(World world, BlockPos pos) {
        Vec3d spawnPos = pos.toCenterPos();
        pos = pos.offset(Direction.Axis.X, -2).offset(Direction.Axis.Z, -2).offset(Direction.Axis.Y, 3);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (world.getBlockState(pos.offset(Direction.Axis.X, j).offset(Direction.Axis.Z, i).offset(Direction.Axis.Y, -1)).isOf(Blocks.OBSIDIAN)) {
                    double offsetX = (j - 2) * 0.6;  // Offset in X direction
                    double offsetY = 0.1;  // Offset in Y direction
                    double offsetZ = (i - 2) * 0.6;  // Offset in Z direction

                    Vec3d dir = new Vec3d(offsetX, offsetY, offsetZ);
                    dir = dir.normalize().multiply(-1);  // Invert the direction vector

                    this.getWorld().addParticle(ParticleTypes.FLAME,
                            pos.offset(Direction.Axis.X, j).getX(),
                            pos.getY(),
                            pos.offset(Direction.Axis.Z, i).getZ(),
                            dir.getX()*0.5, dir.getY()*0.5, dir.getZ()*0.5);
                }
            }
        }
    }*/

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
