package dev.isxander.classicplayground.mixins.noclip;

import dev.isxander.classicplayground.PlayerHolder;
import net.minecraft.entity.player.Player;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin extends MobMixin implements PlayerHolder {
    @Unique
    private boolean noclip$descending = false;

    @Unique
    private float noclip$speed = 0.5f;

    @Override
    public void setDescending(boolean descending) {
        noclip$descending = descending;
    }

    @Override
    public boolean getDescending() {
        return noclip$descending;
    }

    @Override
    public void setNoClipSpeed(float noClipSpeed) {
        noclip$speed = noClipSpeed;
    }

    @Override
    public float getNoClipSpeed() {
        return noclip$speed;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void disablePhysics(World world, CallbackInfo ci) {
        noClip = true;
    }

    @Override
    protected double modifyFallSpeed(double fallSpeed) {
        if (noClip)
            return 0.0;
        return fallSpeed;
    }

    @Override
    protected float modifyHorizontalFalloff(float horizontalFalloff) {
        if (noClip)
            return 0;
        return horizontalFalloff;
    }

    @Override
    protected float modifyVerticalFalloff(float verticalFalloff) {
        if (noClip)
            return 0;
        return verticalFalloff;
    }

    @Override
    protected float modifyRelativeSpeed(float speed) {
        if (noClip)
            return getNoClipSpeed() / 0.5f;
        return speed;
    }

    @Override
    protected void onJumpCheck(CallbackInfo ci) {
        if (noClip) {
            PlayerHolder player = (PlayerHolder) (Player) (Object) this;

            if (isJumping)
                motionY = player.getNoClipSpeed();

            if (player.getDescending())
                motionY = -player.getNoClipSpeed();

            if (isJumping && player.getDescending()) {
                motionY = 0;
            }
        }
    }
}
