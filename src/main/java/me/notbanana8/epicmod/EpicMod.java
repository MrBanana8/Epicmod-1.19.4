package me.notbanana8.epicmod;

import me.notbanana8.epicmod.block.ModBlocks;
import me.notbanana8.epicmod.item.ModItems;
import me.notbanana8.epicmod.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpicMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("epicmod");
	public static final String MOD_ID = "epicmod";


	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItemGroup.addItemToItemGroup();
		ModSounds.registerSounds();
		ModBlocks.registerBlocks();
		ModItems.registerItems();
	}
}