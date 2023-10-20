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

package top.theillusivec4.caelus;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.common.CaelusApiImpl;
import top.theillusivec4.caelus.common.network.CaelusNetwork;

@Mod(Caelus.MOD_ID)
public class Caelus {

  public static final String MOD_ID = "caelus";

  public Caelus() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    CaelusApiImpl.ATTRIBUTES.register(eventBus);
    eventBus.addListener(this::setup);
    eventBus.addListener(this::attributeSetup);
    MinecraftForge.EVENT_BUS.addListener(this::playerTick);
  }

  private void attributeSetup(final EntityAttributeModificationEvent evt) {

    for (EntityType<? extends LivingEntity> type : evt.getTypes()) {
      evt.add(type, CaelusApi.getInstance().getFlightAttribute());
    }
  }

  private void setup(final FMLCommonSetupEvent evt) {
    CaelusNetwork.setup();
  }

  private void playerTick(final PlayerTickEvent evt) {
    Player player = evt.player;
    AttributeInstance attributeInstance =
        player.getAttribute(CaelusApi.getInstance().getFlightAttribute());

    if (attributeInstance != null) {
      AttributeModifier elytraModifier = CaelusApi.getInstance().getElytraModifier();
      attributeInstance.removeModifier(elytraModifier.getId());
      ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);

      if (stack.canElytraFly(player) && !attributeInstance.hasModifier(elytraModifier)) {
        attributeInstance.addTransientModifier(elytraModifier);
      }
    }
  }
}
