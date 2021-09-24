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

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import top.theillusivec4.caelus.api.RenderCapeEvent;
import top.theillusivec4.caelus.common.network.CaelusNetwork;

public class ClientMixinHooks {

  public static void checkFlight() {
    LocalPlayer playerEntity = Minecraft.getInstance().player;

    if (playerEntity != null && MixinHooks.startFlight(playerEntity)) {
      CaelusNetwork.sendFlightC2S();
    }
  }

  public static boolean canRenderCape(Player playerEntity) {
    return !MinecraftForge.EVENT_BUS.post(new RenderCapeEvent(playerEntity));
  }
}
