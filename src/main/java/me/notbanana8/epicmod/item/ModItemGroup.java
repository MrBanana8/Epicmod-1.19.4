package me.notbanana8.epicmod.item;

import me.notbanana8.epicmod.EpicMod;
import me.notbanana8.epicmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup NIMROD = Registry.register(Registries.ITEM_GROUP, new Identifier(EpicMod.MOD_ID, "nimrod"),
            FabricItemGroup.builder()
                    .displayName(Text.literal("Epic Mod"))
                    .icon(() -> new ItemStack(ModItems.NIMROD)).entries((displayContext, entries) -> {
                        entries.add(ModItems.NIMROD);
                        entries.add(ModItems.BANANA);
                        entries.add(ModItems.HAT);
                        entries.add(ModItems.NIMROD_SPAWN_EGG);
                        entries.add(ModItems.KARIN_SPAWN_EGG);

                        entries.add(ModBlocks.NIMROD_BLOCK);
                        entries.add(ModBlocks.KARIN_SUMMONING_BLOCK);
                    }).build());

    public static void registerItemGroups(){

    }

}
