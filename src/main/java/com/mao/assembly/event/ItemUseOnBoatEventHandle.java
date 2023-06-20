package com.mao.assembly.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ItemUseOnBoatEventHandle implements InteractionEvent.InteractEntity {
    @Override
    public EventResult interact(PlayerEntity player, Entity entity, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        World world = player.getWorld();
        EntityHitResult entityHitResult = new EntityHitResult(entity);
        if (entity instanceof BoatEntity boatEntity && !(entity instanceof ChestBoatEntity )) {
            if (stack.isOf(Items.CHEST) && player.isSneaky()) {
                boatEntity.discard();
                ChestBoatEntity chestBoatEntity = new ChestBoatEntity(world, entityHitResult.getPos().x, entityHitResult.getPos().y, entityHitResult.getPos().z);
                chestBoatEntity.setVariant(boatEntity.getVariant());
                chestBoatEntity.setYaw(boatEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(chestBoatEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, entityHitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.CHEST));
                return AssemblyResult.SUCCESS;
            }
        }
        return AssemblyResult.PASS;
    }
}
