/*
 * Copyright (C) 2019-2023 C4
 *
 * Caelus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Caelus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * and the GNU Lesser General Public License along with Caelus.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package com.illusivesoulworks.caelus.mixin.core;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import com.illusivesoulworks.caelus.mixin.util.MixinHooks;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

  public MixinLivingEntity(EntityType<?> pEntityType, Level pLevel) {
    super(pEntityType, pLevel);
  }

  @SuppressWarnings("ConstantConditions")
  @ModifyArg(
      at = @At(
          value = "INVOKE",
          target = "net/minecraft/world/entity/LivingEntity.setSharedFlag(IZ)V"),
      method = "updateFallFlying"
  )
  private boolean caelus$setFlag(boolean value) {
    return MixinHooks.canFly((LivingEntity) (Object) this, this.getSharedFlag(7), value);
  }
}
