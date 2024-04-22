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

package top.theillusivec4.caelus.common.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import top.theillusivec4.caelus.api.CaelusApi;

public class CPacketFlight {

  public static void encode(CPacketFlight msg, FriendlyByteBuf buf) {
  }

  public static CPacketFlight decode(FriendlyByteBuf buf) {
    return new CPacketFlight();
  }

  @SuppressWarnings("ConstantConditions")
  public static void handle(ServerPlayer serverPlayer) {

    if (serverPlayer != null) {
      serverPlayer.stopFallFlying();

      if (!serverPlayer.onGround() && !serverPlayer.isFallFlying() && !serverPlayer.isInWater() &&
          !serverPlayer.hasEffect(MobEffects.LEVITATION) &&
          CaelusApi.getInstance().canFallFly(serverPlayer) != CaelusApi.TriState.DENY) {
        serverPlayer.startFallFlying();
      }
    }
  }
}
