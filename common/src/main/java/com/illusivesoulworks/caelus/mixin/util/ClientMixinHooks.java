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

package com.illusivesoulworks.caelus.mixin.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import com.illusivesoulworks.caelus.api.CaelusApi;
import com.illusivesoulworks.caelus.platform.Services;

public class ClientMixinHooks {

  public static boolean checkFlight() {
    LocalPlayer playerEntity = Minecraft.getInstance().player;

    if (playerEntity != null) {
      CaelusApi.TriState flag = CaelusApi.getInstance().canFallFly(playerEntity);

      if (flag == CaelusApi.TriState.DENY) {
        return false;
      }

      if (!playerEntity.onGround() && !playerEntity.isFallFlying() && !playerEntity.isInWater() &&
          !playerEntity.hasEffect(MobEffects.LEVITATION) && flag == CaelusApi.TriState.ALLOW) {
        playerEntity.startFallFlying();
        Services.CAELUS.sendFlightPacket();
      }
    }
    return true;
  }

  public static boolean canRenderCape(Player playerEntity) {
    return Services.CAELUS.canRenderCape(playerEntity);
  }
}
