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

package top.theillusivec4.caelus.common.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;
import top.theillusivec4.caelus.Caelus;

public class CaelusNetwork {

  private static final int PTC_VERSION = 1;

  private static SimpleChannel instance;

  public static void setup() {
    instance = ChannelBuilder.named(new ResourceLocation(Caelus.MOD_ID, "main"))
        .networkProtocolVersion(PTC_VERSION)
        .clientAcceptedVersions(Channel.VersionTest.exact(PTC_VERSION))
        .serverAcceptedVersions(Channel.VersionTest.exact(PTC_VERSION)).simpleChannel();

    instance.messageBuilder(CPacketFlight.class)
        .encoder(CPacketFlight::encode)
        .decoder(CPacketFlight::decode)
        .consumerNetworkThread(CPacketFlight::handle)
        .add();
  }

  public static void sendFlightC2S() {
    instance.send(new CPacketFlight(), PacketDistributor.SERVER.noArg());
  }
}
