package me.notbanana8.epicmod.data;

import me.notbanana8.epicmod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.NIMROD_BLOCK);
        addDrop(ModBlocks.KARIN_SUMMONING_BLOCK);
        copperOreDrops(ModBlocks.NIMROD_ORE);
    }
}
