package dev.isxander.classicplayground.mixins.noclip;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow public boolean noClip;

    @Shadow public double motionY;

    @ModifyVariable(method = "moveInDirection", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    protected float modifyXSpeed(float x) {
        return x;
    }

    @ModifyVariable(method = "moveInDirection", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    protected float modifyZSpeed(float z) {
        return z;
    }

    @ModifyVariable(method = "moveInDirection", at = @At("HEAD"), argsOnly = true, ordinal = 2)
    protected float modifyRelativeSpeed(float speed) {
        return speed;
    }
}
