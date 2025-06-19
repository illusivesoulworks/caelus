package com.illusivesoulworks.caelus.api;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.ApiStatus;

public class GlidingDamageEvent extends LivingEvent {

  private final List<Pair<ItemStack, Consumer<Item>>> originalGliders;
  private final List<Pair<ItemStack, Consumer<Item>>> gliders;

  private boolean modified = false;

  public GlidingDamageEvent(LivingEntity entity, List<Pair<ItemStack, Consumer<Item>>> gliders) {
    super(entity);
    this.originalGliders = gliders;
    this.gliders = this.originalGliders;
  }

  public List<ItemStack> getOriginalGliders() {
    return this.originalGliders.stream().map(Pair::getFirst).toList();
  }

  public List<ItemStack> getGliders() {
    return this.gliders.stream().map(Pair::getFirst).toList();
  }

  public void addGlider(ItemStack glider) {
    this.gliders.add(new Pair<>(glider, item -> {
    }));
    this.modified = true;
  }

  public void addGlider(ItemStack glider, Consumer<Item> damager) {
    this.gliders.add(new Pair<>(glider, damager));
    this.modified = true;
  }

  @ApiStatus.Internal
  public List<Pair<ItemStack, Consumer<Item>>> getResults() {
    return this.gliders;
  }

  public boolean isModified() {
    return this.modified;
  }
}
