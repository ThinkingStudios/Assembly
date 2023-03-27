package com.mao.assembly.event;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ItemUseOnBoatEventHandle implements UseEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (entity instanceof BoatEntity boatEntity && !(entity instanceof ChestBoatEntity )) {
            if (stack.isOf(Items.CHEST) && player.isSneaky()) {
                boatEntity.discard();
                ChestBoatEntity chestBoatEntity = new ChestBoatEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
                chestBoatEntity.setVariant(boatEntity.getVariant());
                chestBoatEntity.setYaw(boatEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(chestBoatEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.CHEST));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
