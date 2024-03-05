/*
 * Copyright (C) 2019-2024 C4
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

package top.theillusivec4.caelus.common.network;

import javax.annotation.Nonnull;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import top.theillusivec4.caelus.CaelusConstants;

public class CPacketFlightPayload extends CPacketFlight implements CustomPacketPayload {

  public static final ResourceLocation ID =
      new ResourceLocation(CaelusConstants.MOD_ID, "flight");

  public CPacketFlightPayload() {
    super();
  }

  public CPacketFlightPayload(FriendlyByteBuf buf) {
    this();
  }

  @Override
  public void write(@Nonnull FriendlyByteBuf buf) {
    CPacketFlight.encode(this, buf);
  }

  @Nonnull
  @Override
  public ResourceLocation id() {
    return ID;
  }
}
