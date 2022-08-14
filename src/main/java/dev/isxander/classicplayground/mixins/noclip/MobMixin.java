package dev.isxander.classicplayground.mixins.noclip;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MobMixin extends EntityMixin {
    @Shadow protected boolean isJumping;

    @ModifyConstant(method = "tickLiving", constant = @Constant(doubleValue = 0.08))
    protected double modifyFallSpeed(double fallSpeed) {
        return fallSpeed;
    }

    @ModifyConstant(method = "tickLiving", constant = @Constant(floatValue = 0.91f))
    protected float modifyHorizontalFalloff(float horizontalFalloff) {
        return horizontalFalloff;
    }

    @ModifyConstant(method = "tickLiving", constant = @Constant(floatValue = 0.98f))
    protected float modifyVerticalFalloff(float verticalFalloff) {
        return verticalFalloff;
    }

    @Inject(method = "tickLiving", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;m()Z", shift = At.Shift.AFTER))
    protected void onJumpCheck(CallbackInfo ci) {

    }
}
