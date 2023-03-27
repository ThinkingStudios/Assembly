package com.mao.assembly;

import com.mao.assembly.event.ItemUseOnBoatEventHandle;
import com.mao.assembly.event.ItemUseOnMinecartEventHandle;
import com.mao.assembly.event.ItemUseOnRailEventHandle;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;

public class Assembly implements ModInitializer {
    public static final String MOD_ID = "assembly";

    @Override
    public void onInitialize() {
        UseEntityCallback.EVENT.register(new ItemUseOnBoatEventHandle());
        UseEntityCallback.EVENT.register(new ItemUseOnMinecartEventHandle());
        UseBlockCallback.EVENT.register(new ItemUseOnRailEventHandle());
    }
}
