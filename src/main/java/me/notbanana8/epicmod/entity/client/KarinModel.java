package me.notbanana8.epicmod.entity.client;


import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.custom.KarinEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class KarinModel extends GeoModel<KarinEntity> {
    @Override
    public Identifier getModelResource(KarinEntity animatable) {
        return new Identifier(EpicMod.MOD_ID, "geo/karin.geo.json");
    }

    @Override
    public Identifier getTextureResource(KarinEntity animatable) {
        return new Identifier(EpicMod.MOD_ID, "textures/entity/karin_texture.png");
    }

    @Override
    public Identifier getAnimationResource(KarinEntity animatable) {
        return new Identifier(EpicMod.MOD_ID, "animations/karin.animation.json");
    }

    //@Override
    //public void setCustomAnimations(KarinEntity animatable, long instanceId, AnimationState<KarinEntity> animationState) {
    //    CoreGeoBone head = getAnimationProcessor().getBone("head");
    //
    //    if (head != null) {
    //        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
    //        head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
    //        head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
    //    }
    //}
}
