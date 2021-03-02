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

package top.theillusivec4.caelus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.client.CaelusElytraLayer;
import top.theillusivec4.caelus.common.network.NetworkHandler;

@Mod(Caelus.MODID)
public class Caelus {

  public static final String MODID = CaelusApi.MODID;

  public Caelus() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    CaelusApi.ATTRIBUTES.register(eventBus);
    eventBus.addListener(this::setup);
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(FMLCommonSetupEvent evt) {
    NetworkHandler.register();
  }

  @SubscribeEvent
  public void playerTick(PlayerTickEvent evt) {
    PlayerEntity player = evt.player;
    ModifiableAttributeInstance attributeInstance =
        player.getAttribute(CaelusApi.ELYTRA_FLIGHT.get());

    if (attributeInstance != null) {
      attributeInstance.removeModifier(CaelusApi.ELYTRA_MODIFIER);
      ItemStack stack = player.getItemStackFromSlot(EquipmentSlotType.CHEST);

      if (CaelusApi.canElytraFly(player, stack) &&
          !attributeInstance.hasModifier(CaelusApi.ELYTRA_MODIFIER)) {
        attributeInstance.applyNonPersistentModifier(CaelusApi.ELYTRA_MODIFIER);
      }
    }
  }

  @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class ClientSetup {

    @SubscribeEvent
    public static void postSetup(FMLLoadCompleteEvent evt) {
      EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
      rendererManager.getSkinMap().values()
          .forEach(renderer -> renderer.addLayer(new CaelusElytraLayer<>(renderer)));
    }
  }
}
