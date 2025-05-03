package com.perfectparity.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.perfectparity.entity.utils.WolfSoundSet;
import net.minecraft.sounds.SoundEvent;

public class ModCodecs {
    public static final Codec<WolfSoundSet> WOLF_SOUND_SET_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            SoundEvent.CODEC.fieldOf("ambient_sound").forGetter(WolfSoundSet::ambientSound),
            SoundEvent.CODEC.fieldOf("death_sound").forGetter(WolfSoundSet::deathSound),
            SoundEvent.CODEC.fieldOf("growl_sound").forGetter(WolfSoundSet::growlSound),
            SoundEvent.CODEC.fieldOf("hurt_sound").forGetter(WolfSoundSet::hurtSound),
            SoundEvent.CODEC.fieldOf("pant_sound").forGetter(WolfSoundSet::pantSound),
            SoundEvent.CODEC.fieldOf("whine_sound").forGetter(WolfSoundSet::whineSound)
    ).apply(instance, WolfSoundSet::new));
}
