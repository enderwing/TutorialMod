package com.enderwing.tutorialmod.setup;


import com.enderwing.tutorialmod.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

//File is not strictly necessary, it is here for organization
public class ModSetup {

    public ItemGroup itemGroup = new ItemGroup("tutorialmod") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.FIRSTBLOCK);
        }
    };

    public void init() {

    }
}
