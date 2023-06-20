package com.mao.assembly;

import com.mao.assembly.event.ItemUseOnBoatEventHandle;
import com.mao.assembly.event.ItemUseOnMinecartEventHandle;
import com.mao.assembly.event.ItemUseOnRailEventHandle;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Assembly.MOD_ID)
public class Assembly {
    public static final String MOD_ID = "assembly";

    public Assembly() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::onInitialize);
    }

    public void onInitialize(FMLCommonSetupEvent event) {
        InteractionEvent.INTERACT_ENTITY.register(new ItemUseOnBoatEventHandle());
        InteractionEvent.INTERACT_ENTITY.register(new ItemUseOnMinecartEventHandle());
        InteractionEvent.RIGHT_CLICK_BLOCK.register(new ItemUseOnRailEventHandle());
    }
}
