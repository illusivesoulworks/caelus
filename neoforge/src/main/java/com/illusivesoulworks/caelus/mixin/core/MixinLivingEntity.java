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

import com.illusivesoulworks.caelus.api.CaelusApi;
import com.illusivesoulworks.caelus.mixin.MixinHooks;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

  @Unique
  private final MutableBoolean caelus$flag = new MutableBoolean(false);

  public MixinLivingEntity(EntityType<?> pEntityType, Level pLevel) {
    super(pEntityType, pLevel);
  }

  @SuppressWarnings("ConstantConditions")
  @Inject(
      at = @At(
          value = "FIELD",
          target = "net/minecraft/world/entity/EquipmentSlot.VALUES:Ljava/util/List;"),
      method = "canGlide",
      cancellable = true
  )
  private void caelus$canGlide(CallbackInfoReturnable<Boolean> cir) {
    CaelusApi.TriState state = CaelusApi.getInstance().canFallFly((LivingEntity) (Object) this);

    if (state == CaelusApi.TriState.ALLOW) {
      cir.setReturnValue(true);
    } else if (state == CaelusApi.TriState.DENY) {
      cir.setReturnValue(false);
    }
  }

  @ModifyArg(
      at = @At(
          value = "INVOKE",
          target = "net/minecraft/Util.getRandom(Ljava/util/List;Lnet/minecraft/util/RandomSource;)Ljava/lang/Object;"
      ),
      method = "updateFallFlying"
  )
  private List<EquipmentSlot> caelus$damageGliders(List<EquipmentSlot> slots) {
    return MixinHooks.damageGliders((LivingEntity) (Object) this, slots, this.caelus$flag);
  }

  @ModifyVariable(
      at = @At(
          value = "INVOKE",
          target = "net/minecraft/world/entity/LivingEntity.getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"
      ),
      method = "updateFallFlying"
  )
  private EquipmentSlot caelus$substituteSlot(EquipmentSlot equipmentSlot) {

    if (this.caelus$flag.booleanValue()) {
      this.caelus$flag.setFalse();
      return null;
    }
    return equipmentSlot;
  }
}
