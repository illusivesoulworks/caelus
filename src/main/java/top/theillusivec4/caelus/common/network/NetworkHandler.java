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

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import top.theillusivec4.caelus.Caelus;
import top.theillusivec4.caelus.common.network.client.CPacketSetFlight;
import top.theillusivec4.caelus.common.network.client.CPacketToggleFlight;
import top.theillusivec4.caelus.common.network.client.CPacketUseFirework;

public class NetworkHandler {

  private static final String PTC_VERSION = "1";

  public static SimpleChannel INSTANCE;

  private static int id = 0;

  public static void register() {
    INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Caelus.MODID, "main"))
        .networkProtocolVersion(() -> PTC_VERSION).clientAcceptedVersions(PTC_VERSION::equals)
        .serverAcceptedVersions(PTC_VERSION::equals).simpleChannel();

    registerMessage(CPacketToggleFlight.class, CPacketToggleFlight::encode,
        CPacketToggleFlight::decode, CPacketToggleFlight::handle);
    registerMessage(CPacketSetFlight.class, CPacketSetFlight::encode, CPacketSetFlight::decode,
        CPacketSetFlight::handle);
    registerMessage(CPacketUseFirework.class, CPacketUseFirework::encode,
        CPacketUseFirework::decode, CPacketUseFirework::handle);
  }

  private static <M> void registerMessage(Class<M> messageType, BiConsumer<M, PacketBuffer> encoder,
      Function<PacketBuffer, M> decoder,
      BiConsumer<M, Supplier<NetworkEvent.Context>> messageConsumer) {
    INSTANCE.registerMessage(id++, messageType, encoder, decoder, messageConsumer);
  }
}
