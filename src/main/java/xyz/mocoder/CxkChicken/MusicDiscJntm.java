package xyz.mocoder.CxkChicken;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;

public class MusicDiscJntm extends MusicDiscItem {

    private final int comparatorOutput;
    private final SoundEvent sound;

    protected MusicDiscJntm(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, sound, settings);
        this.comparatorOutput = comparatorOutput;
        this.sound = sound;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public SoundEvent getSound() {
        return this.sound;
    }
}
