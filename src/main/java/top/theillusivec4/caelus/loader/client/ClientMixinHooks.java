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

package top.theillusivec4.caelus.loader.client;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import top.theillusivec4.caelus.api.RenderElytraInfo;
import top.theillusivec4.caelus.api.event.RenderElytraCallback;
import top.theillusivec4.caelus.core.client.RenderElytraInfoImpl;
import top.theillusivec4.caelus.loader.common.MixinHooks;
import top.theillusivec4.caelus.loader.common.NetworkHandler;

public class ClientMixinHooks {

  public static void checkFlight() {
    ClientPlayerEntity playerEntity = MinecraftClient.getInstance().player;

    if (playerEntity != null && MixinHooks.startFlight(playerEntity)) {
      ClientSidePacketRegistry.INSTANCE
          .sendToServer(NetworkHandler.START_FLYING, new PacketByteBuf(Unpooled.buffer()));
    }
  }

  public static boolean canRenderCape(PlayerEntity playerEntity) {
    RenderElytraInfo renderElytraEvent = new RenderElytraInfoImpl(playerEntity);
    RenderElytraCallback.EVENT.invoker().process(playerEntity, renderElytraEvent);
    return !renderElytraEvent.canRender();
  }
}
