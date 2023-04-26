package me.notbanana8.epicmod.entity.client;

import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.custom.NimrodEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class NimrodModel extends GeoModel<NimrodEntity> {
    @Override
    public Identifier getModelResource(NimrodEntity animatable) {
        return new Identifier(EpicMod.MOD_ID, "geo/nimrod.geo.json");
    }

    @Override
    public Identifier getTextureResource(NimrodEntity animatable) {
        return new Identifier(EpicMod.MOD_ID, "textures/entity/nimrod_texture.png");
    }

    @Override
    public Identifier getAnimationResource(NimrodEntity animatable) {
        return new Identifier(EpicMod.MOD_ID, "animations/nimrod.animation.json");
    }

    @Override
    public void setCustomAnimations(NimrodEntity animatable, long instanceId, AnimationState<NimrodEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
