package me.notbanana8.epicmod;

import me.notbanana8.epicmod.block.ModBlocks;
import me.notbanana8.epicmod.entity.ModEntities;
import me.notbanana8.epicmod.entity.custom.KarinEntity;
import me.notbanana8.epicmod.entity.custom.NimrodEntity;
import me.notbanana8.epicmod.item.ModItemGroup;
import me.notbanana8.epicmod.item.ModItems;
import me.notbanana8.epicmod.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpicMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("epicmod");
	public static final String MOD_ID = "epicmod";


	@Override
	public void onInitialize() {
		LOGGER.info("Allegedly. Please don't sue me :)");
		ModItemGroup.registerItemGroups();
		ModItemGroup.addItemToItemGroup();
		ModSounds.registerSounds();
		ModBlocks.registerBlocks();
		ModItems.registerItems();

		FabricDefaultAttributeRegistry.register(ModEntities.NIMROD,NimrodEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.KARIN, KarinEntity.setAttributes());
	}
}