package dev.isxander.classicplayground.mixins.keybinds;

import dev.isxander.classicplayground.Keybindings;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/Input;processKeyEvent(IZ)V"))
    private void onKeyEvent(CallbackInfo ci) {
        for (Keybindings.KeybindWrapper keybind : Keybindings.CUSTOM_KEYBINDS) {
            if (Keyboard.getEventKey() == keybind.getKey()) {
                keybind.getConsumer().accept((MinecraftClient) (Object) this, Keyboard.getEventKeyState());
            }
        }
    }
}
