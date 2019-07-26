package com.enderwing.tutorialmod;

import com.enderwing.tutorialmod.blocks.FirstBlock;
import com.enderwing.tutorialmod.blocks.FirstBlockTile;
import com.enderwing.tutorialmod.blocks.ModBlocks;
import com.enderwing.tutorialmod.items.FirstItem;
import com.enderwing.tutorialmod.setup.ClientProxy;
import com.enderwing.tutorialmod.setup.IProxy;
import com.enderwing.tutorialmod.setup.ModSetup;
import com.enderwing.tutorialmod.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("tutorialmod")
public class TutorialMod {

    private static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup modSetup = new ModSetup();

    // run first, basically the first step of setup for the mod
    public TutorialMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    // guaranteed to run after all registering of blocks, items, etc. is complete for all mods
    private void setup(final FMLCommonSetupEvent event) {
        modSetup.init(); // don't have to use ModSetup file, just stylistic
        proxy.init(); //currently empty, here for future use
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    // runs before most other setup, should contain block, item, etc. registering
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(new FirstBlock());
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties properties = new Item.Properties()
                    .group(modSetup.itemGroup);
            event.getRegistry().register(new BlockItem(ModBlocks.FIRSTBLOCK, properties).setRegistryName("firstblock"));
            event.getRegistry().register(new FirstItem());
        }

        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {

            //TODO: rewind tutorial and figure out how to fix this
            event.getRegistry().register(TileEntityType.Builder.create(FirstBlockTile::new, ModBlocks.FIRSTBLOCK).build(null).setRegistryName("firstblock"));
        }
    }
}
