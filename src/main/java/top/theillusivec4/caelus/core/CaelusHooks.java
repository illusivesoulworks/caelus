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

package top.theillusivec4.caelus.core;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.caelus.api.event.RenderElytraEvent;
import top.theillusivec4.caelus.common.network.NetworkHandler;
import top.theillusivec4.caelus.common.network.client.CPacketSetFlight;

public class CaelusHooks {

  public static void sendElytraPacket(ClientPlayerEntity playerSP) {

    NetworkHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new CPacketSetFlight(true));
  }

  public static boolean fireAndCheckRenderElytraEvent(LivingEntity livingEntity) {

    RenderElytraEvent evt = new RenderElytraEvent(livingEntity);
    MinecraftForge.EVENT_BUS.post(evt);
    return evt.getRenderElytra();
  }

  public static Tuple<Boolean, Boolean> fireRenderElytraEvent(LivingEntity livingEntity) {

    RenderElytraEvent evt = new RenderElytraEvent(livingEntity);
    MinecraftForge.EVENT_BUS.post(evt);
    return new Tuple<>(evt.getRenderElytra(), evt.getRenderEnchantmentGlow());
  }
}
