/*
 * Copyright (C) 2019-2021 C4
 *
 * This file is part of Caelus.
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
 *
 */

package top.theillusivec4.caelus.mixin.util;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import top.theillusivec4.caelus.api.CaelusApi;

public class MixinHooks {

  @SuppressWarnings("ConstantConditions")
  public static boolean canFly(LivingEntity livingEntity, boolean oldFlag, boolean newFlag) {
    CaelusApi.TriState fallFly = CaelusApi.getInstance().canFallFly(livingEntity);

    if (fallFly == CaelusApi.TriState.DENY) {
      return false;
    } else if (fallFly == CaelusApi.TriState.DEFAULT) {
      return newFlag;
    }
    return !livingEntity.onGround() && !livingEntity.isPassenger() &&
        !livingEntity.hasEffect(MobEffects.LEVITATION) && oldFlag;
  }
}
