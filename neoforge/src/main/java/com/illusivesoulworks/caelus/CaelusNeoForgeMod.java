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

package com.illusivesoulworks.caelus;

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import com.illusivesoulworks.caelus.api.CaelusApi;
import com.illusivesoulworks.caelus.common.CaelusApiImpl;
import com.illusivesoulworks.caelus.common.CaelusEvents;
import com.illusivesoulworks.caelus.common.network.CPacketFlight;
import com.illusivesoulworks.caelus.common.network.CaelusServerPayloadHandler;

@Mod(CaelusConstants.MOD_ID)
public class CaelusNeoForgeMod {

  public CaelusNeoForgeMod(IEventBus eventBus) {
    CaelusApiImpl.setup();
    eventBus.addListener(this::registerPayloadHandler);
    eventBus.addListener(this::attributeSetup);
    NeoForge.EVENT_BUS.addListener(this::entityTick);
  }

  private void attributeSetup(final EntityAttributeModificationEvent evt) {

    for (EntityType<? extends LivingEntity> type : evt.getTypes()) {
      evt.add(type, CaelusApi.getInstance().getFallFlyingAttribute());
    }
  }

  private void registerPayloadHandler(final RegisterPayloadHandlersEvent evt) {
    evt.registrar(CaelusConstants.MOD_ID)
        .playToServer(CPacketFlight.TYPE, StreamCodec.unit(CPacketFlight.INSTANCE),
            CaelusServerPayloadHandler.getInstance()::handleFlight);
  }

  private void entityTick(final EntityTickEvent.Post evt) {

    if (evt.getEntity() instanceof LivingEntity livingEntity) {
      CaelusEvents.livingTick(livingEntity);
    }
  }
}
