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

package com.illusivesoulworks.caelus.common.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;
import com.illusivesoulworks.caelus.CaelusConstants;

public class CaelusNetwork {

  private static final int PTC_VERSION = 1;

  public static SimpleChannel instance;

  public static void setup() {
    instance =
        ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(CaelusConstants.MOD_ID, "main"))
            .networkProtocolVersion(PTC_VERSION)
            .clientAcceptedVersions(Channel.VersionTest.exact(PTC_VERSION))
            .serverAcceptedVersions(Channel.VersionTest.exact(PTC_VERSION)).simpleChannel();

    instance.messageBuilder(CPacketFlight.class)
        .encoder(CPacketFlight::encode)
        .decoder(CPacketFlight::decode)
        .consumerNetworkThread((cPacketFlight, context) -> {
          context.enqueueWork(() -> CPacketFlight.handle(context.getSender()));
          context.setPacketHandled(true);
        })
        .add();
  }
}
