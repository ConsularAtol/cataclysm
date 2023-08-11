package net.consular.cataclysm.registry;

import net.consular.cataclysm.Cataclysm;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModSounds {

    public static SoundEvent MAGIC_MIRROR_USE = registerSoundEvent("magic_mirror.use");
    public static SoundEvent SNEAK_ATTACK = registerSoundEvent("sneak_attack");
    public static SoundEvent STUDDED_LEATHER_EQUIP = registerSoundEvent("armor.equip.studded_leather");

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = new Identifier(Cataclysm.MODID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds(){
        Cataclysm.LOGGER.info("Registering sounds");;
    }
}
