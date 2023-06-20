package com.mao.assembly.event;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ItemUseOnRailEventHandle implements InteractionEvent.RightClickBlock {
    @Override
    public EventResult click(PlayerEntity player, Hand hand, BlockPos pos, Direction face) {
        ItemStack MainStack = player.getStackInHand(Hand.MAIN_HAND);
        ItemStack OffStack = player.getStackInHand(Hand.OFF_HAND);
        World world = player.getWorld();
        int MainCount = MainStack.getCount();
        int OffCount = OffStack.getCount();
        BlockState state = world.getBlockState(pos);
        if (state.isOf(Blocks.RAIL) && MainStack.isOf(Items.GOLD_INGOT) && MainCount >= 6
                && OffStack.isOf(Items.REDSTONE) && OffCount >= 1) {
            world.setBlockState(pos, Blocks.POWERED_RAIL.getStateWithProperties(state));
            world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.25f, 1.0f, true);
            if (!player.getAbilities().creativeMode) {
                MainStack.decrement(6);
                OffStack.decrement(1);
            }
        }else if (state.isOf(Blocks.RAIL) && MainStack.isOf(Items.STONE_PRESSURE_PLATE) && MainCount >= 1
                && OffStack.isOf(Items.REDSTONE) && OffCount >= 1) {
            world.setBlockState(pos, Blocks.DETECTOR_RAIL.getStateWithProperties(state));
            world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.25f, 1.0f, true);
            if (!player.getAbilities().creativeMode) {
                MainStack.decrement(1);
                OffStack.decrement(1);
            }
        } else if (state.isOf(Blocks.RAIL) && MainStack.isOf(Items.REDSTONE_TORCH) && MainCount >= 1 && OffStack.isEmpty()) {
            world.setBlockState(pos, Blocks.ACTIVATOR_RAIL.getStateWithProperties(state));
            world.playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.25f, 1.0f, true);
            if (!player.getAbilities().creativeMode) {
                MainStack.decrement(1);
            }
        }
        return AssemblyResult.PASS;
    }
}
