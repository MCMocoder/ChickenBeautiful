package xyz.mocoder.CxkChicken;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.EntityType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import xyz.mocoder.CxkChicken.CxkChickenMain;

import java.util.UUID;

import static xyz.mocoder.CxkChicken.CxkChickenMain.BasketballEntityInstance;

@Environment(EnvType.CLIENT)
public class CxkChickenClient implements ClientModInitializer {

    public static final EntityType<BasketballEntity> Basketballentity=BasketballEntityInstance;

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Basketballentity, (entityRenderDispatcher, context) -> new FlyingItemEntityRenderer<BasketballEntity>(entityRenderDispatcher,context.getItemRenderer()));
        ClientSidePacketRegistry.INSTANCE.register(new Identifier("chicken","basketball"),CxkChickenClient::spawnEntity);
    }

    private static void spawnEntity(PacketContext context, PacketByteBuf buffer) {
        int entityId = buffer.readInt();
        UUID uuid = buffer.readUuid();
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        context.getTaskQueue().execute(() -> {
            BasketballEntity entity = new BasketballEntity(BasketballEntityInstance,context.getPlayer().world);
            entity.setPos(x, y, z);
            entity.setEntityId(entityId);
            entity.setUuid(uuid);
            entity.updateTrackedPosition(x, y, z);
            ((ClientWorld) context.getPlayer().world).addEntity(entityId, entity);
        });
    }
}
