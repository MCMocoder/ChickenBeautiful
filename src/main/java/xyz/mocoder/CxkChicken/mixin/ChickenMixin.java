package xyz.mocoder.CxkChicken.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static xyz.mocoder.CxkChicken.CxkChickenMain.BasketballItemInstance;
//import static xyz.mocoder.CxkChicken.CxkChickenMain.cxkGetHit;

@Mixin(ChickenEntity.class)
public abstract class ChickenMixin extends LivingEntity{

    protected ChickenMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /*@Overwrite
    public SoundEvent getAmbientSound() {
        return cxkGetHit;
    }

    @Overwrite
    public SoundEvent getDeathSound() {
        return cxkGetHit;
    }*/

    @Redirect(
            method="tickMovement",
            at=@At(value="INVOKE",
                    target="Lnet/minecraft/entity/passive/ChickenEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;"
            )
    )
    public ItemEntity dropItemRedirect(ChickenEntity entity,ItemConvertible item) {
        int rd=(int)(Math.random()*10.0);
        if(rd==0)
        {
            return entity.dropItem(BasketballItemInstance,0);
        }
        else
        {
            return entity.dropItem(Items.EGG,0);
        }
    }
}
