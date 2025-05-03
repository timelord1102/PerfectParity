package com.perfectparity.entity.utils;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;

public record WolfSoundSet(
        Holder<SoundEvent> ambientSound,
        Holder<SoundEvent> deathSound,
        Holder<SoundEvent> growlSound,
        Holder<SoundEvent> hurtSound,
        Holder<SoundEvent> pantSound,
        Holder<SoundEvent> whineSound
) {
    public Holder<SoundEvent> ambientSound() {
        return this.ambientSound;
    }

    public Holder<SoundEvent> deathSound() {
        return this.deathSound;
    }

    public Holder<SoundEvent> growlSound() {
        return this.growlSound;
    }

    public Holder<SoundEvent> hurtSound() {
        return this.hurtSound;
    }

    public Holder<SoundEvent> pantSound() {
        return this.pantSound;
    }

    public Holder<SoundEvent> whineSound() {
        return this.whineSound;
    }
}

