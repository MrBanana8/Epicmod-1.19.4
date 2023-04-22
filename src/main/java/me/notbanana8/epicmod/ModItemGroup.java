package me.notbanana8.epicmod;

import me.notbanana8.epicmod.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup NIMROD;

    public static void registerItemGroups(){
        NIMROD = FabricItemGroup.builder(new Identifier(EpicMod.MOD_ID, "nimrod"))
                .displayName(Text.literal("Epic Mod"))
                .icon(() -> new ItemStack(ModItems.NIMROD)).build();
    }

    public static  void addItemToItemGroup(){
        addToItemGroup(ModItemGroup.NIMROD, ModItems.NIMROD);
        addToItemGroup(ModItemGroup.NIMROD, ModItems.BANANA);
    }
    public static void addToItemGroup(ItemGroup group, Item item){
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
}
