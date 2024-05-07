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

package top.theillusivec4.caelus.platform.services;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.PacketDistributor;
import top.theillusivec4.caelus.api.RenderCapeEvent;
import top.theillusivec4.caelus.common.network.CPacketFlight;
import top.theillusivec4.caelus.common.network.CaelusNetwork;

public class CaelusForge implements ICaelusPlatform {

  @Override
  public boolean canFly(ItemStack stack, LivingEntity livingEntity) {
    return stack.canElytraFly(livingEntity);
  }

  @Override
  public void sendFlightPacket() {
    CaelusNetwork.instance.send(CPacketFlight.INSTANCE, PacketDistributor.SERVER.noArg());
  }

  @Override
  public boolean canRenderCape(Player playerEntity) {
    return !MinecraftForge.EVENT_BUS.post(new RenderCapeEvent(playerEntity));
  }
}
