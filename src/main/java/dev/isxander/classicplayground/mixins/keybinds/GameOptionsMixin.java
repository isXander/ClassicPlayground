package dev.isxander.classicplayground.mixins.keybinds;

import dev.isxander.classicplayground.Keybindings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Keybind;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    @Shadow public Keybind[] keybinds;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void overrideKeybinds(MinecraftClient client, File file, CallbackInfo ci) {
        keybinds = Keybindings.addCustomKeybinds(keybinds);
    }
}
