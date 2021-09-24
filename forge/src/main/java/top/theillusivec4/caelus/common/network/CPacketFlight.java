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

package top.theillusivec4.caelus.common.network;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import top.theillusivec4.caelus.api.CaelusApi;

public final class CPacketFlight {

  public static void encode(CPacketFlight msg, FriendlyByteBuf buf) {
  }

  public static CPacketFlight decode(FriendlyByteBuf buf) {
    return new CPacketFlight();
  }

  @SuppressWarnings("ConstantConditions")
  public static void handle(CPacketFlight msg, Supplier<NetworkEvent.Context> ctx) {
    ctx.get().enqueueWork(() -> {
      ServerPlayer sender = ctx.get().getSender();

      if (sender != null) {
        sender.stopFallFlying();

        if (!sender.isOnGround() && !sender.isFallFlying() && !sender.isInWater() && !sender
            .hasEffect(MobEffects.LEVITATION) && CaelusApi.getInstance().canFly(sender)) {
          sender.startFallFlying();
        }
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
