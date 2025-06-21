package com.illusivesoulworks.caelus.mixin;

import com.illusivesoulworks.caelus.api.GlidingDamageEvent;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.Util;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class MixinHooks {

  public static List<EquipmentSlot> damageGliders(
      LivingEntity livingEntity, List<EquipmentSlot> slots, MutableBoolean flag) {
    flag.setFalse();
    List<Pair<ItemStack, Consumer<Item>>> stacks = new ArrayList<>();

    for (EquipmentSlot slot : slots) {
      ItemStack stack = livingEntity.getItemBySlot(slot);

      if (!stack.isEmpty()) {
        stacks.add(new Pair<>(stack, item -> livingEntity.onEquippedItemBroken(item, slot)));
      }
    }
    GlidingDamageEvent evt = NeoForge.EVENT_BUS.post(new GlidingDamageEvent(livingEntity, stacks));

    if (evt.isModified()) {
      Pair<ItemStack, Consumer<Item>> glider =
          Util.getRandom(evt.getResults(), livingEntity.getRandom());

      if (livingEntity.level() instanceof ServerLevel serverLevel) {
        ServerPlayer serverPlayer = livingEntity instanceof ServerPlayer player ? player : null;
        glider.getFirst().hurtAndBreak(1, serverLevel, serverPlayer, glider.getSecond());
      }
      flag.setTrue();
    }

    if (slots.isEmpty()) {
      return List.of(EquipmentSlot.HEAD);
    }
    return slots;
  }
}
