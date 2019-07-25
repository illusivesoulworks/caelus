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
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.caelus.api.CaelusAPI;
import top.theillusivec4.caelus.client.EventHandlerClient;
import top.theillusivec4.caelus.client.KeyRegistry;
import top.theillusivec4.caelus.client.renderer.CaelusElytraLayer;
import top.theillusivec4.caelus.common.CaelusConfig;
import top.theillusivec4.caelus.common.network.NetworkHandler;

@Mod(Caelus.MODID)
public class Caelus {

  public static final String MODID = "caelus";

  public static final ResourceLocation DISABLED_ICON =
      new ResourceLocation(Caelus.MODID, "textures/gui/flight_disabled.png");

  public Caelus() {

    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CaelusConfig.clientSpec);
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(FMLCommonSetupEvent evt) {

    NetworkHandler.register();
  }

  @SubscribeEvent
  public void attachAttribute(EntityEvent.EntityConstructing evt) {

    if (evt.getEntity() instanceof PlayerEntity) {
      ((PlayerEntity) evt.getEntity()).getAttributes().registerAttribute(CaelusAPI.ELYTRA_FLIGHT);
    }
  }

  @SubscribeEvent
  public void onLivingEquipmentChange(LivingEquipmentChangeEvent evt) {

    if (!(evt.getEntityLiving() instanceof PlayerEntity) ||
        evt.getSlot() != EquipmentSlotType.CHEST) {
      return;
    }

    ItemStack from = evt.getFrom();
    ItemStack to = evt.getTo();
    IAttributeInstance attributeInstance =
        evt.getEntityLiving().getAttribute(CaelusAPI.ELYTRA_FLIGHT);

    if (from.getItem() instanceof ElytraItem) {
      attributeInstance.removeModifier(CaelusAPI.ELYTRA_MODIFIER);
    }

    if (to.getItem() instanceof ElytraItem &&
        !attributeInstance.hasModifier(CaelusAPI.ELYTRA_MODIFIER) && ElytraItem.isUsable(to)) {
      attributeInstance.applyModifier(CaelusAPI.ELYTRA_MODIFIER);
    }
  }

  @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class ClientSetup {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent evt) {

      KeyRegistry.register();
      MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
    }

    @SubscribeEvent
    public static void postSetup(FMLLoadCompleteEvent evt) {

      EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
      rendererManager.getSkinMap()
                     .values()
                     .forEach(renderer -> renderer.addLayer(new CaelusElytraLayer<>(renderer)));
      rendererManager.renderers.values().forEach(renderer -> {
        if (renderer instanceof LivingRenderer) {
          LivingRenderer<? extends LivingEntity, ? extends EntityModel> livingRenderer =
              (LivingRenderer<? extends LivingEntity, ? extends EntityModel>) renderer;
          livingRenderer.addLayer(new CaelusElytraLayer(livingRenderer));
        }
      });
    }
  }
}
