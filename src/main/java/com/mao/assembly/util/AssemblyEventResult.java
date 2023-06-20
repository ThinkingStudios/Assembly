package com.mao.assembly.util;

import dev.architectury.event.EventResult;
import net.minecraft.util.ActionResult;

public class AssemblyEventResult {
    public static final EventResult SUCCESS = EventResult.interrupt(EventResult.interruptDefault().asMinecraft() == ActionResult.SUCCESS);

    public static final EventResult PASS = EventResult.pass();
}
