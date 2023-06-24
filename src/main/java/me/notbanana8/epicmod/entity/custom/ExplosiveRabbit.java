package me.notbanana8.epicmod.entity.custom;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.world.World;


public class ExplosiveRabbit extends RabbitEntity {

    public ExplosiveRabbit(EntityType<? extends RabbitEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return RabbitEntity.createRabbitAttributes();
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void checkBlockCollision() {
        super.checkBlockCollision();
        if(!this.getWorld().isClient() && !this.getWorld().getBlockState(getBlockPos()).isOf(Blocks.AIR)){
            this.getWorld().createExplosion(this,this.getX(),this.getY(),this.getZ(),1, World.ExplosionSourceType.MOB);
            this.remove(RemovalReason.DISCARDED);
        }
    }
}
