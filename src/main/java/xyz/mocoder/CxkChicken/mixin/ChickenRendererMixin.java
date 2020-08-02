package xyz.mocoder.CxkChicken.mixin;

import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ChickenEntityRenderer.class)
public class ChickenRendererMixin {

    @Overwrite
    public Identifier getTexture(ChickenEntity entity){
        return new Identifier("chicken:textures/entity/chicken/cxkchicken.png");
    }
}
