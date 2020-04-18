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

package top.theillusivec4.caelus.common.network.client;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.entity.item.FireworkRocketEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.network.NetworkEvent;
import top.theillusivec4.caelus.api.CaelusAPI;

public class CPacketSetFlight {

  private final boolean withFirework;

  public CPacketSetFlight() {
    this(false);
  }

  public CPacketSetFlight(boolean withFirework) {
    this.withFirework = withFirework;
  }

  public static void encode(CPacketSetFlight msg, PacketBuffer buf) {
    buf.writeBoolean(msg.withFirework);
  }

  public static CPacketSetFlight decode(PacketBuffer buf) {
    return new CPacketSetFlight(buf.readBoolean());
  }

  public static void handle(CPacketSetFlight msg, Supplier<NetworkEvent.Context> ctx) {

    ctx.get().enqueueWork(() -> {
      ServerPlayerEntity sender = ctx.get().getSender();

      if (sender == null) {
        return;
      }
      sender.func_226568_ek_();

      if (!sender.onGround && !sender.isElytraFlying() && !sender.isInWater() && CaelusAPI
          .canElytraFly(sender)) {
        sender.func_226567_ej_();
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
