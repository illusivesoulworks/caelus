/*
 * Copyright (C) 2019-2024 C4
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

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class CaelusServerPayloadHandler {

  private static final CaelusServerPayloadHandler INSTANCE =
      new CaelusServerPayloadHandler();

  public static CaelusServerPayloadHandler getInstance() {
    return INSTANCE;
  }

  public void handleFlight(CPacketFlightPayload msg, PlayPayloadContext ctx) {
    ctx.workHandler().submitAsync(() -> ctx.player().ifPresent(player -> {
          if (player instanceof ServerPlayer serverPlayer) {
            CPacketFlightPayload.handle(serverPlayer);
          }
        }))
        .exceptionally(e -> {
          ctx.packetHandler()
              .disconnect(Component.translatable("caelus.networking.failed", e.getMessage()));
          return null;
        });
  }
}
