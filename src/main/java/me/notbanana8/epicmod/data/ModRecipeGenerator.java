package me.notbanana8.epicmod.data;

import me.notbanana8.epicmod.block.ModBlocks;
import me.notbanana8.epicmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerReversibleCompactingRecipes(exporter,RecipeCategory.BUILDING_BLOCKS,ModItems.NIMROD,
                RecipeCategory.DECORATIONS,ModBlocks.NIMROD_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModBlocks.KARIN_SUMMONING_BLOCK)
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .input('N', Items.NETHER_STAR)
                .criterion(FabricRecipeProvider.hasItem(Items.NETHER_STAR),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHER_STAR))
                .offerTo(exporter,new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.KARIN_SUMMONING_BLOCK)));
    }
}
