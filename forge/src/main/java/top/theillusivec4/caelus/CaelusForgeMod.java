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

package top.theillusivec4.caelus;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.common.CaelusApiImpl;
import top.theillusivec4.caelus.common.CaelusEvents;
import top.theillusivec4.caelus.common.network.CaelusNetwork;

@Mod(CaelusConstants.MOD_ID)
public class CaelusForgeMod {

  public CaelusForgeMod() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    CaelusApiImpl.setup();
    eventBus.addListener(this::setup);
    eventBus.addListener(this::attributeSetup);
    MinecraftForge.EVENT_BUS.addListener(this::livingTick);
  }

  private void attributeSetup(final EntityAttributeModificationEvent evt) {

    for (EntityType<? extends LivingEntity> type : evt.getTypes()) {
      evt.add(type, CaelusApi.getInstance().getFlightAttribute());
    }
  }

  private void setup(final FMLCommonSetupEvent evt) {
    CaelusNetwork.setup();
  }

  private void livingTick(final LivingEvent.LivingTickEvent evt) {
    CaelusEvents.livingTick(evt.getEntity());
  }
}
