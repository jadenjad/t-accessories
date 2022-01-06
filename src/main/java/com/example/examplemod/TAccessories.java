package com.example.examplemod;

import net.minecraft.block.Blocks;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings.Type;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

// The value here should match an entry in the META-INF/mods.toml file

@Mod("examplemod")
public class TAccessories
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static Item hermesBoots;
    public static Item cloudInABottle;
    public static Item cobaltShield;
    public static Item obsidianSkull;
    public static Item luckyHorseshoe;
    //public static ContainerType<ContainerCustomPlayer> newInv;
   // public static InventoryCustomPlayer inventory;

    public static final KeyBinding cloudKey = new KeyBinding("key.dbljump", Type.KEYSYM,
      GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories.test");

    public static final KeyBinding invKey = new KeyBinding("key.inv", Type.KEYSYM,
      GLFW.GLFW_KEY_E, "key.categories.test");

    public TAccessories() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
        //ScreenManager.register(newInv, GuiCustomPlayerInventory::new);
        ClientRegistry.registerKeyBinding(cloudKey);
        ClientRegistry.registerKeyBinding(invKey);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.NECKLACE.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().build());

    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Item> blockRegistryEvent) {
            // register a new block here
            Item.Properties bootProp= new Item.Properties();
            bootProp.tab(ItemGroup.TAB_COMBAT);
            hermesBoots = new HermesBoots(bootProp);
            hermesBoots.setRegistryName("hermes");

            Item.Properties cloudProp= new Item.Properties();
            cloudProp.tab(ItemGroup.TAB_COMBAT);
            cloudInABottle = new CloudInABottle(cloudProp);
            cloudInABottle.setRegistryName("cloud");

            Item.Properties cobaltProp = new Item.Properties();
            cobaltProp.tab(ItemGroup.TAB_COMBAT);
            cobaltShield = new CobaltShield(cobaltProp);
            cobaltShield.setRegistryName("cobalt");

            Item.Properties obsidianProp= new Item.Properties();
            obsidianProp.tab(ItemGroup.TAB_COMBAT);
            obsidianSkull = new ObsidianSkull(obsidianProp);
            obsidianSkull.setRegistryName("obsidian");

            Item.Properties horseshoeProp= new Item.Properties();
            horseshoeProp.tab(ItemGroup.TAB_COMBAT);
            luckyHorseshoe = new LuckyHorseshoe(horseshoeProp);
            luckyHorseshoe.setRegistryName("horseshoe");

            blockRegistryEvent.getRegistry().registerAll(hermesBoots, cloudInABottle, obsidianSkull, cobaltShield, luckyHorseshoe);
            MinecraftForge.EVENT_BUS.register(hermesBoots);
            MinecraftForge.EVENT_BUS.register(cloudInABottle);
            MinecraftForge.EVENT_BUS.register(obsidianSkull);
            MinecraftForge.EVENT_BUS.register(cobaltShield);
            MinecraftForge.EVENT_BUS.register(luckyHorseshoe);
            LOGGER.info("HELLO from Register Block");
        }
        public static void containerRegister(final RegistryEvent.Register<ContainerType<?>> containerRegisterEvent) {
            //containerRegisterEvent.getRegistry().registerAll(newInv);
        }
    }
}
