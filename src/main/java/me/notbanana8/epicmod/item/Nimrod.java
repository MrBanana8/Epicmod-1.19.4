package me.notbanana8.epicmod.item;

import me.notbanana8.epicmod.entity.ModEntities;
import me.notbanana8.epicmod.sound.ModSounds;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Nimrod extends Item {
    public Nimrod(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(hand == Hand.MAIN_HAND){
            user.playSound(ModSounds.NIMROD,1.0f,1.0f);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
