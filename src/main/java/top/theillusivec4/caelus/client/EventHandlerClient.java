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
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import top.theillusivec4.caelus.Caelus;
import top.theillusivec4.caelus.api.CaelusAPI;
import top.theillusivec4.caelus.common.CaelusConfig;
import top.theillusivec4.caelus.common.network.CPacketToggleFlight;
import top.theillusivec4.caelus.common.network.NetworkHandler;

public class EventHandlerClient {

    private static int cooldown = 0;

    @SubscribeEvent
    public void onKeyPress(TickEvent.ClientTickEvent evt) {

        if (evt.phase == TickEvent.Phase.END) {

            if (KeyRegistry.toggleFlight.isKeyDown() && Minecraft.getInstance().isGameFocused() && cooldown <= 0) {
                NetworkHandler.INSTANCE.sendToServer(new CPacketToggleFlight());
                cooldown = 10;
            }

            if (cooldown > 0) {
                cooldown--;
            }
        }
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post evt) {

        if (CaelusConfig.CLIENT.toggleIcon.get() && evt.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
            ClientPlayerEntity playerSP = Minecraft.getInstance().player;

            if (playerSP != null) {
                IAttributeInstance attributeInstance = playerSP.getAttribute(CaelusAPI.ELYTRA_FLIGHT);

                if (attributeInstance.hasModifier(CaelusAPI.DISABLE_FLIGHT)) {
                    Minecraft.getInstance().getTextureManager().bindTexture(Caelus.DISABLED_ICON);
                    AbstractGui.blit(0, 0, 0, 0, 24, 24, 24, 24);
                }
            }
        }
    }
}
