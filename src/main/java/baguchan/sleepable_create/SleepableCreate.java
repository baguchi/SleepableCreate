package baguchan.sleepable_create;

import com.simibubi.create.AllInteractionBehaviours;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
        AllInteractionBehaviours.registerBehaviour(Blocks.RED_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.BLACK_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.BLUE_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.BROWN_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.CYAN_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.GRAY_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.GREEN_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.LIGHT_BLUE_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.LIGHT_GRAY_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.LIME_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.MAGENTA_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.ORANGE_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.PINK_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.PURPLE_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.WHITE_BED, new SleepableInteractionBehaviour());
        AllInteractionBehaviours.registerBehaviour(Blocks.YELLOW_BED, new SleepableInteractionBehaviour());
    }
}
