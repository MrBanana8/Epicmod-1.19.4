package me.notbanana8.epicmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;

public class Hat extends Item {
    public Hat(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        for (int i = 0; i < 10; i++) {
            entity.getWorld().addParticle(ParticleTypes.CLOUD,entity.getX(),entity.getY()+0.5,entity.getZ(),0,0.1,0);
        }
        entity.remove(Entity.RemovalReason.DISCARDED);
        return TypedActionResult.success(user.getStackInHand(hand)).getResult();
    }
}
