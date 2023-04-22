package me.notbanana8.epicmod.sound;

import me.notbanana8.epicmod.EpicMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static SoundEvent NIMROD = registerSoundEvent("nimrod");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(EpicMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds(){
        System.out.println("Registering ModSounds for" + EpicMod.MOD_ID);
    }
}
