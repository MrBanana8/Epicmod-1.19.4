package me.notbanana8.epicmod.item;

import com.mojang.blaze3d.systems.RenderSystem;
import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.ModEntities;
import me.notbanana8.epicmod.entity.custom.ExplosiveRabbit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hat extends Item implements Equipment {
    private int counter = 5;
    private static final Identifier RABBIT = new Identifier(EpicMod.MOD_ID,
            "textures/hud/rabbit.png");
    public Hat(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("A Totally Normal Hat!"));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.EPIC;
    }


    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(!user.getWorld().isClient && entity instanceof RabbitEntity && counter <10){
            for (int i = 0; i < 10; i++) {
                entity.getWorld().addParticle(ParticleTypes.CLOUD,entity.getX(),entity.getY()+0.5,entity.getZ(),0,0.1,0);
            }
            counter++;
            entity.remove(Entity.RemovalReason.DISCARDED);
        }
        return TypedActionResult.success(user.getStackInHand(hand)).getResult();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && counter > 0 && counter <= 10) {
            Vec3d lookDir = user.getRotationVector().multiply(1.5);

            ExplosiveRabbit rabbit = new ExplosiveRabbit(ModEntities.EXPLOSIVE_RABBIT,world);
            rabbit.setPosition(user.getX(),user.getEyeY(),user.getZ());
            rabbit.setVelocity(lookDir.x, lookDir.y, lookDir.z);

            counter--;
            world.spawnEntity(rabbit);
            user.getItemCooldownManager().set(this,20);
        }

        return super.use(world, user, hand);
    }

    public void renderHud(MatrixStack matrixStack, DrawContext context) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, RABBIT);

        for (int i = 0; i < counter; i++) {
            context.drawTexture(RABBIT, x + (i * 9) + 8, y - 52, 0, 0, 12, 12, 12, 12);
        }
    }


    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}
