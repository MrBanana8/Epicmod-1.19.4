package me.notbanana8.epicmod.item;

import me.notbanana8.epicmod.EpicMod;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item BANANA = new Item(new Item.Settings().maxCount(16).food(FoodComponents.BEEF));
    public static final Item NIMROD = new Nimrod(new Item.Settings());
    public static void registerItems(){
        EpicMod.LOGGER.info("Registering Mod Items for " + EpicMod.MOD_ID);
        Registry.register(Registries.ITEM,new Identifier(EpicMod.MOD_ID, "banana"), BANANA);
        Registry.register(Registries.ITEM,new Identifier(EpicMod.MOD_ID, "nimrod"), NIMROD);

    }
}
