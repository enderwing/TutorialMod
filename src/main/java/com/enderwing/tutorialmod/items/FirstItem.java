package com.enderwing.tutorialmod.items;

import com.enderwing.tutorialmod.TutorialMod;
import net.minecraft.item.Item;

public class FirstItem extends Item {

    public FirstItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(TutorialMod.modSetup.itemGroup));
        setRegistryName("firstitem");
    }
}
