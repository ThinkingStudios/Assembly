package com.mao.assembly.event;

import com.mao.assembly.util.AssemblyEventResult;
import dev.architectury.event.EventResult;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ItemUseOnMinecartEventHandle {
    public static EventResult interact(PlayerEntity player, Entity entity, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        World world = player.getWorld();
        EntityHitResult entityHitResult = new EntityHitResult(entity);
        if (entity instanceof MinecartEntity minecartEntity) {
            if (stack.isOf(Items.CHEST) && player.isSneaky()) {
                minecartEntity.discard();
                ChestMinecartEntity chestMinecartEntity = new ChestMinecartEntity(world, entityHitResult.getPos().x, entityHitResult.getPos().y, entityHitResult.getPos().z);
                chestMinecartEntity.setYaw(minecartEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(chestMinecartEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, entityHitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.CHEST));
                return AssemblyEventResult.SUCCESS;
            } else if (stack.isOf(Items.HOPPER) && player.isSneaky()) {
                minecartEntity.discard();
                HopperMinecartEntity hopperMinecartEntity = new HopperMinecartEntity(world, entityHitResult.getPos().x, entityHitResult.getPos().y, entityHitResult.getPos().z);
                hopperMinecartEntity.setYaw(minecartEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(hopperMinecartEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, entityHitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                    player.incrementStat(Stats.USED.getOrCreateStat(Items.HOPPER));
                    return AssemblyEventResult.SUCCESS;
                }
            } else if (stack.isOf(Items.TNT) && player.isSneaky()) {
                minecartEntity.discard();
                TntMinecartEntity tntMinecartEntity = new TntMinecartEntity(world, entityHitResult.getPos().x, entityHitResult.getPos().y, entityHitResult.getPos().z);
                tntMinecartEntity.setYaw(minecartEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(tntMinecartEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, entityHitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.TNT));
                return AssemblyEventResult.SUCCESS;
            } else if (stack.isOf(Items.FURNACE) && player.isSneaky()) {
                minecartEntity.discard();
                FurnaceMinecartEntity furnaceMinecartEntity = new FurnaceMinecartEntity(world, entityHitResult.getPos().x, entityHitResult.getPos().y, entityHitResult.getPos().z);
                furnaceMinecartEntity.setYaw(minecartEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(furnaceMinecartEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, entityHitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.FURNACE));
                return AssemblyEventResult.SUCCESS;
            }
        }
        return AssemblyEventResult.PASS;
    }
}
