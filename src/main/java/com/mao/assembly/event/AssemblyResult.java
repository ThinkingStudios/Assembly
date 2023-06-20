package com.mao.assembly.event;

import dev.architectury.event.EventResult;
import net.minecraft.util.ActionResult;

public class AssemblyResult {
    protected static final EventResult SUCCESS = EventResult.interrupt(EventResult.interruptDefault().asMinecraft() == ActionResult.SUCCESS);

    protected static final EventResult PASS = EventResult.pass();
}
