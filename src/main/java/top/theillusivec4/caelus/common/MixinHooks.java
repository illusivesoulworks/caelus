/*
 * Copyright (C) 2019  C4
 *
 * This file is part of Caelus, a mod made for Minecraft.
 *
 * Caelus is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Caelus is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Caelus.  If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.caelus.common;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effects;
import top.theillusivec4.caelus.api.CaelusApi;

public class MixinHooks {

  public static boolean canFly(LivingEntity livingEntity, boolean flag) {
    boolean bl = flag;
    bl = bl && !livingEntity.isOnGround() && !livingEntity.isPassenger() && !livingEntity
        .isPotionActive(Effects.LEVITATION) && CaelusApi.canElytraFly(livingEntity);
    return bl;
  }

  public static boolean startFlight(ClientPlayerEntity clientPlayerEntity) {

    if (!clientPlayerEntity.isOnGround() && !clientPlayerEntity.isElytraFlying()
        && !clientPlayerEntity.isInWater() && !clientPlayerEntity.isPotionActive(Effects.LEVITATION)
        && CaelusApi.canElytraFly(clientPlayerEntity)) {
      clientPlayerEntity.startFallFlying();
      return true;
    }
    return false;
  }
}
