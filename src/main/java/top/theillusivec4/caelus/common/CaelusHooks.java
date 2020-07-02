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

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.api.RenderElytraEvent;
import top.theillusivec4.caelus.common.network.CPacketSetFlight;
import top.theillusivec4.caelus.common.network.NetworkHandler;

public class CaelusHooks {

  public static void sendElytraPacket() {
    ClientPlayerEntity clientPlayerEntity = Minecraft.getInstance().player;

    if (clientPlayerEntity != null && canElytraFly(clientPlayerEntity)) {
      NetworkHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new CPacketSetFlight());
    }
  }

  public static boolean canRenderCape(PlayerEntity playerEntity) {
    RenderElytraEvent renderElytraEvent = new RenderElytraEvent(playerEntity);
    MinecraftForge.EVENT_BUS.post(renderElytraEvent);
    return !renderElytraEvent.canRender();
  }

  private static boolean canElytraFly(ClientPlayerEntity clientPlayerEntity) {

    if (!clientPlayerEntity.func_233570_aj_() && !clientPlayerEntity.isElytraFlying()
        && !clientPlayerEntity.isInWater() && !clientPlayerEntity.isPotionActive(Effects.LEVITATION)
        && CaelusApi.canElytraFly(clientPlayerEntity)) {
      clientPlayerEntity.startFallFlying();
      return true;
    }
    return false;
  }
}
