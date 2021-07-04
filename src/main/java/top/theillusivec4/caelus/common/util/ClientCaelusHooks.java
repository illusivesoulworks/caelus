/*
 * Copyright (c) 2019-2020 C4
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

package top.theillusivec4.caelus.common.util;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import top.theillusivec4.caelus.common.CaelusNetwork;

public class ClientCaelusHooks {

  public static void checkFlight() {
    ClientPlayerEntity playerEntity = MinecraftClient.getInstance().player;

    if (playerEntity != null && CommonCaelusHooks.startFlight(playerEntity)) {
      if (ClientPlayNetworking.canSend(CaelusNetwork.START_FLYING)) {
        ClientPlayNetworking.send(CaelusNetwork.START_FLYING, PacketByteBufs.create());
      } else {
        ClientPlayNetworkHandler networkHandler = MinecraftClient.getInstance().getNetworkHandler();
        if (networkHandler != null) {
          networkHandler.sendPacket(new ClientCommandC2SPacket(playerEntity, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
        }
      }
    }
  }
}
