package baguchan.sleepable_create;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllInteractionBehaviours;
import com.simibubi.create.AllMovementBehaviours;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SleepableCreate.MODID)
public class SleepableCreate
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "sleepable_create";
    public SleepableCreate()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        AllInteractionBehaviours.registerBehaviour(Blocks.RED_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.BLACK_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.BLUE_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.BROWN_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.CYAN_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.GRAY_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.GREEN_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.LIGHT_BLUE_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.LIGHT_GRAY_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.LIME_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.MAGENTA_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.ORANGE_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.PINK_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.PURPLE_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.WHITE_BED, new SleepableMovementBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.YELLOW_BED, new SleepableMovementBehaviour());
    }
}
