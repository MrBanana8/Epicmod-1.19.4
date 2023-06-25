package me.notbanana8.epicmod.item;

import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item BANANA = new Item(new Item.Settings().maxCount(16).food(FoodComponents.BEEF));
    public static final Item NIMROD = new Nimrod(new Item.Settings());
    public static final Item HAT = new Hat(new FabricItemSettings().maxCount(1));
    public static final Item NIMROD_SPAWN_EGG = registerItem("nimrod_spawn_egg",
            new SpawnEggItem(ModEntities.NIMROD, 0xf8ad86, 0xFFC0CB,
                    new FabricItemSettings()));

    public static final Item KARIN_SPAWN_EGG = registerItem("karin_spawn_egg",
            new SpawnEggItem(ModEntities.KARIN, 0x121111, 0xb53636,
                    new FabricItemSettings()));

    public static final Item NIMROD_SWORD = registerItem("nimrod_sword",
            new SwordItem(ModToolMaterials.NIMROD, 4,-2.4F,(new FabricItemSettings().fireproof())));
    public static final Item NIMROD_PICKAXE = registerItem("nimrod_pickaxe",
            new PickaxeItem(ModToolMaterials.NIMROD, 1,-2.8F,(new FabricItemSettings().fireproof())));
    public static final Item NIMROD_AXE = registerItem("nimrod_axe",
            new AxeItem(ModToolMaterials.NIMROD, 6,-3.0F,(new FabricItemSettings().fireproof())));
    public static final Item NIMROD_SHOVEL = registerItem("nimrod_shovel",
            new ShovelItem(ModToolMaterials.NIMROD, 1,-3.0F,(new FabricItemSettings().fireproof())));
    public static final Item NIMROD_HOE = registerItem("nimrod_hoe",
            new HoeItem(ModToolMaterials.NIMROD, -4,0F,(new FabricItemSettings().fireproof())));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(EpicMod.MOD_ID, name), item);
    }


    public static void registerItems(){
        EpicMod.LOGGER.info("Registering Mod Items for " + EpicMod.MOD_ID);
        Registry.register(Registries.ITEM,new Identifier(EpicMod.MOD_ID, "banana"), BANANA);
        Registry.register(Registries.ITEM,new Identifier(EpicMod.MOD_ID, "nimrod"), NIMROD);
        Registry.register(Registries.ITEM,new Identifier(EpicMod.MOD_ID, "hat"), HAT);
    }
}
