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

package top.theillusivec4.caelus.common.network;

import java.util.function.Supplier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import top.theillusivec4.caelus.Caelus;
import top.theillusivec4.caelus.api.CaelusAPI;

public class CPacketToggleFlight {

  private static final String ENABLE_FLIGHT = Caelus.MODID + ".enableFlight";
  private static final String DISABLE_FLIGHT = Caelus.MODID + ".disableFlight";

  public static void encode(CPacketToggleFlight msg, PacketBuffer buf) {

  }

  public static CPacketToggleFlight decode(PacketBuffer buf) {

    return new CPacketToggleFlight();
  }

  public static void handle(CPacketToggleFlight msg, Supplier<NetworkEvent.Context> ctx) {

    ctx.get().enqueueWork(() -> {
      ServerPlayerEntity sender = ctx.get().getSender();

      if (sender == null) {
        return;
      }

      IAttributeInstance attributeInstance = sender.getAttribute(CaelusAPI.ELYTRA_FLIGHT);

      if (attributeInstance.hasModifier(CaelusAPI.DISABLE_FLIGHT)) {
        attributeInstance.removeModifier(CaelusAPI.DISABLE_FLIGHT);
        sender.sendStatusMessage(new TranslationTextComponent(ENABLE_FLIGHT), true);
      } else {
        attributeInstance.applyModifier(CaelusAPI.DISABLE_FLIGHT);
        sender.sendStatusMessage(new TranslationTextComponent(DISABLE_FLIGHT), true);
      }
    });
    ctx.get().setPacketHandled(true);
  }
}
