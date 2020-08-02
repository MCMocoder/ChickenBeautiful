package xyz.mocoder.CxkChicken;

import io.netty.buffer.Unpooled;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

import static xyz.mocoder.CxkChicken.CxkChickenMain.BasketballEntityInstance;
import static xyz.mocoder.CxkChicken.CxkChickenMain.BasketballItemInstance;

public class BasketballEntity extends ThrownItemEntity {

    public BasketballEntity(EntityType<? extends BasketballEntity> entityType, World world) {
        super(entityType, world);
    }

    public BasketballEntity(World world, PlayerEntity owner) {
        super(BasketballEntityInstance,owner,world);
    }


    //等同于Forge的onImpact
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof ChickenEntity ? 2 : 0;
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()),(float)i);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        super.createSpawnPacket();
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(this.getEntityId());
        buf.writeUuid(this.getUuid());
        buf.writeDouble(this.getX());
        buf.writeDouble(this.getY());
        buf.writeDouble(this.getZ());
        return new CustomPayloadS2CPacket(new Identifier("chicken", "basketball"), buf);
    }

    @Override
    protected Item getDefaultItem() {
        return BasketballItemInstance;
        //return null;
    }
}
