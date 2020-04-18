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

package top.theillusivec4.caelus.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.caelus.Caelus;
import top.theillusivec4.caelus.api.CaelusAPI;
import top.theillusivec4.caelus.common.CaelusConfig;
import top.theillusivec4.caelus.common.network.NetworkHandler;
import top.theillusivec4.caelus.common.network.client.CPacketSetFlight;
import top.theillusivec4.caelus.common.network.client.CPacketToggleFlight;

public class ClientEventHandler {

  private static boolean triggerJump = false;
  private static int cooldown = 0;
  private static boolean triggerFlight = false;
  private static int triggerFlightUse = 0;

  private static void triggerElytra() {
    NetworkHandler.INSTANCE
        .send(PacketDistributor.SERVER.noArg(), new CPacketSetFlight(triggerFlightUse > 0));
    triggerJump = false;
    triggerFlight = false;
    triggerFlightUse = 0;
  }

  @SubscribeEvent
  public void onTrigger(InputUpdateEvent evt) {
    ClientPlayerEntity player = (ClientPlayerEntity) evt.getPlayer();

    if (player.onGround && triggerJump) {
      evt.getMovementInput().jump = true;
      triggerJump = false;
      triggerFlight = true;
    }
  }

  @SubscribeEvent
  public void onKeyPress(TickEvent.ClientTickEvent evt) {

    if (evt.phase != TickEvent.Phase.END) {
      return;
    }

    final boolean isFocused = Minecraft.getInstance().isGameFocused();
    final boolean isToggleKeyDown = KeyRegistry.toggleFlight.isKeyDown() && isFocused;
    final boolean isTriggerKeyDown = KeyRegistry.triggerFlight.isKeyDown() && isFocused;

    if (isToggleKeyDown && cooldown <= 0) {
      NetworkHandler.INSTANCE.sendToServer(new CPacketToggleFlight());
      cooldown = 10;
    }

    ClientPlayerEntity player = Minecraft.getInstance().player;

    if (player != null) {

      final boolean canFly =
          CaelusAPI.canElytraFly(player) && !player.isElytraFlying() && !player.abilities.isFlying;

      if (canFly) {

        if (isTriggerKeyDown && !triggerJump) {

          if (!player.onGround) {

            if (player.getMotion().y < 0.0D) {
              triggerElytra();
            }
          } else {
            triggerJump = true;
          }
        }

        if (triggerFlight && !player.onGround && player.getMotion().y < 0.0D) {
          triggerElytra();
        }
      }

      if (cooldown > 0) {
        cooldown--;
      }

      if (CaelusConfig.CLIENT.simpleTakeoff.get() && isTriggerKeyDown && (triggerFlight
          || triggerJump)) {
        triggerFlightUse++;
      } else {
        triggerFlightUse = 0;
      }
    }
  }

  @SubscribeEvent
  public void onRenderGameOverlay(RenderGameOverlayEvent.Post evt) {

    if (!CaelusConfig.CLIENT.toggleIcon.get()
        || evt.getType() != RenderGameOverlayEvent.ElementType.POTION_ICONS) {
      return;
    }

    ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;

    if (clientPlayer == null) {
      return;
    }

    IAttributeInstance attributeInstance = clientPlayer.getAttribute(CaelusAPI.ELYTRA_FLIGHT);

    if (attributeInstance.hasModifier(CaelusAPI.DISABLE_FLIGHT)) {
      Minecraft.getInstance().getTextureManager().bindTexture(Caelus.DISABLED_ICON);
      AbstractGui.blit(1, 1, 0, 0, 24, 24, 24, 24);
    }
  }
}
