package xyz.mocoder.CxkChicken;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class CxkChickenMain implements ModInitializer {

	/*public static final SoundEvent cxkGetHit = (SoundEvent) Registry.register(
			Registry.SOUND_EVENT,
			"cxkchicken.hit",
			new SoundEvent(new Identifier("cxkchicken.hit"))
	);*/

	public static final SoundEvent jntm = new SoundEvent(new Identifier("chicken:jntmsong"));

	public static final BasketballItem BasketballItemInstance = new BasketballItem(
			new Item.Settings().group(ItemGroup.MISC).maxCount(16));

	public static final EntityType<BasketballEntity> BasketballEntityInstance = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("chicken","basketball"),
			FabricEntityTypeBuilder.create(
					SpawnGroup.MISC,
					(EntityType.EntityFactory<BasketballEntity>)BasketballEntity::new
					)
					.dimensions(EntityDimensions.fixed(0.5f,0.5f))
					.build()
	);

	public static final MusicDiscJntm MUSIC_DISC_ITEM = new MusicDiscJntm(
			15,
			jntm,
			new Item.Settings().group(ItemGroup.MISC).maxCount(1).rarity(Rarity.RARE));

	@Override
	public void onInitialize() {
		/*EntityRendererRegistry.INSTANCE.register(BasketballEntityInstance, (dispatcher,context)->{
			return new FlyingItemEntityRenderer(dispatcher,context.getItemRenderer());
		});*/
		Registry.register(Registry.SOUND_EVENT,new Identifier("chicken","jntmsong"),jntm);
		Registry.register(Registry.ITEM, new Identifier("chicken", "basketball_item"),BasketballItemInstance);
		Registry.register(Registry.ITEM,new Identifier("chicken","jntm"),MUSIC_DISC_ITEM);
	}
}
