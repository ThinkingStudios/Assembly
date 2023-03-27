package com.mao.assembly.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class ItemUseOnRailEventHandle implements UseBlockCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack MainStack = player.getStackInHand(Hand.MAIN_HAND);
        ItemStack OffStack = player.getStackInHand(Hand.OFF_HAND);
        int MainCount = MainStack.getCount();
        int OffCount = OffStack.getCount();
        BlockState state = world.getBlockState(hitResult.getBlockPos());
        if (state.isOf(Blocks.RAIL) && MainStack.isOf(Items.GOLD_INGOT) && MainCount >= 6
                && OffStack.isOf(Items.REDSTONE) && OffCount >= 1) {
            world.setBlockState(hitResult.getBlockPos(), Blocks.POWERED_RAIL.getStateWithProperties(state));
            world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.25f, 1.0f, true);
            if (!player.getAbilities().creativeMode) {
                MainStack.decrement(6);
                OffStack.decrement(1);
            }
        }else if (state.isOf(Blocks.RAIL) && MainStack.isOf(Items.STONE_PRESSURE_PLATE) && MainCount >= 1
                && OffStack.isOf(Items.REDSTONE) && OffCount >= 1) {
            world.setBlockState(hitResult.getBlockPos(), Blocks.DETECTOR_RAIL.getStateWithProperties(state));
            world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.25f, 1.0f, true);
            if (!player.getAbilities().creativeMode) {
                MainStack.decrement(1);
                OffStack.decrement(1);
            }
        } else if (state.isOf(Blocks.RAIL) && MainStack.isOf(Items.REDSTONE_TORCH) && MainCount >= 1 && OffStack.isEmpty()) {
            world.setBlockState(hitResult.getBlockPos(), Blocks.ACTIVATOR_RAIL.getStateWithProperties(state));
            world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.25f, 1.0f, true);
            if (!player.getAbilities().creativeMode) {
                MainStack.decrement(1);
            }
        }
        return ActionResult.PASS;
    }
}
