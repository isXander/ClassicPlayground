package dev.isxander.classicplayground;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Keybind;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Keybindings {
    public static KeybindWrapper DESCEND = new KeybindWrapper("Descend", Keyboard.KEY_LSHIFT, (client, down) -> {
        ((PlayerHolder) client.player).setDescending(down);
    });

    public static KeybindWrapper SPEED_UP = new KeybindWrapper("NoClip Speed Up", Keyboard.KEY_UP, (client, down) -> {
        if (down) {
            PlayerHolder player = (PlayerHolder) client.player;
            player.setNoClipSpeed(player.getNoClipSpeed() + 0.5f * player.getNoClipSpeed());
        }
    });

    public static KeybindWrapper SPEED_DOWN = new KeybindWrapper("NoClip Speed Down", Keyboard.KEY_DOWN, (client, down) -> {
        if (down) {
            PlayerHolder player = (PlayerHolder) client.player;
            player.setNoClipSpeed(2 * player.getNoClipSpeed() / 3f);
        }
    });

    public static KeybindWrapper[] CUSTOM_KEYBINDS = { DESCEND, SPEED_UP, SPEED_DOWN };

    public static Keybind[] addCustomKeybinds(Keybind[] vanilla) {
        List<Keybind> vanillaList = new ArrayList<>(Arrays.asList(vanilla));
        vanillaList.addAll(Arrays.stream(CUSTOM_KEYBINDS).map(KeybindWrapper::getKeybind).collect(Collectors.toList()));
        return vanillaList.toArray(new Keybind[0]);
    }

    public static class KeybindWrapper {
        private final String name;
        private final int key;
        private final BiConsumer<MinecraftClient, Boolean> consumer;

        private final Keybind keybind;

        public KeybindWrapper(String name, int key, BiConsumer<MinecraftClient, Boolean> consumer) {
            this.name = name;
            this.key = key;
            this.consumer = consumer;
            this.keybind = new Keybind(name, key);
        }

        public String getName() {
            return name;
        }

        public int getKey() {
            return key;
        }

        public BiConsumer<MinecraftClient, Boolean> getConsumer() {
            return consumer;
        }

        public Keybind getKeybind() {
            return keybind;
        }
    }
}
