package me.notbanana8.epicmod.block;

import me.notbanana8.epicmod.EpicMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block NIMROD_BLOCK = registerBlock("nimrod_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).requiresTool().strength(5.0f, 6.0f)));
    public static final Block KARIN_SUMMONING_BLOCK = registerBlock("karin_summoning_block",
            new SummoningBlock(FabricBlockSettings.copyOf(Blocks.MOSS_BLOCK).strength(0.5F)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(EpicMod.MOD_ID,name),block);
    }

    private static Item registerBlockItem(String name, Block block){
        Item item = Registry.register(Registries.ITEM, new Identifier(EpicMod.MOD_ID,name)
                ,new BlockItem(block,new FabricItemSettings()));
        return item;
    }

    public static void registerBlocks(){
        EpicMod.LOGGER.info("Registering Mod Blocks for " + EpicMod.MOD_ID);

    }
}
