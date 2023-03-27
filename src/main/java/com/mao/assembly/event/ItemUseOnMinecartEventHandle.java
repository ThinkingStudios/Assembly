package com.mao.assembly.event;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ItemUseOnMinecartEventHandle implements UseEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        if (entity instanceof MinecartEntity minecartEntity) {
            if (stack.isOf(Items.CHEST) && player.isSneaky()) {
                minecartEntity.discard();
                ChestMinecartEntity chestMinecartEntity = new ChestMinecartEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
                chestMinecartEntity.setYaw(minecartEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(chestMinecartEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.CHEST));
                return ActionResult.SUCCESS;
            } else if (stack.isOf(Items.HOPPER) && player.isSneaky()) {
                minecartEntity.discard();
                HopperMinecartEntity hopperMinecartEntity = new HopperMinecartEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
                hopperMinecartEntity.setYaw(minecartEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(hopperMinecartEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                    player.incrementStat(Stats.USED.getOrCreateStat(Items.HOPPER));
                    return ActionResult.SUCCESS;
                }
            } else if (stack.isOf(Items.TNT) && player.isSneaky()) {
                minecartEntity.discard();
                TntMinecartEntity tntMinecartEntity = new TntMinecartEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
                tntMinecartEntity.setYaw(minecartEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(tntMinecartEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.TNT));
                return ActionResult.SUCCESS;
            } else if (stack.isOf(Items.FURNACE) && player.isSneaky()) {
                minecartEntity.discard();
                FurnaceMinecartEntity furnaceMinecartEntity = new FurnaceMinecartEntity(world, hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
                furnaceMinecartEntity.setYaw(minecartEntity.getYaw());
                if (!world.isClient) {
                    world.spawnEntity(furnaceMinecartEntity);
                    world.emitGameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getPos());
                    if (!player.getAbilities().creativeMode) {
                        stack.decrement(1);
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.FURNACE));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
