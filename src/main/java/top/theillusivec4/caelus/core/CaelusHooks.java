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

package top.theillusivec4.caelus.core;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import top.theillusivec4.caelus.api.CaelusAPI;
import top.theillusivec4.caelus.api.event.RenderCapeCheckEvent;

public class CaelusHooks {

    public static void setElytraState(EntityPlayerMP player) {

        if (CaelusAPI.canElytraFly(player)) {
            player.setElytraFlying();
        }
    }

    public static void sendElytraPacket(EntityPlayerSP playerSP) {

        if (CaelusAPI.canElytraFly(playerSP)) {
            playerSP.connection.sendPacket(new CPacketEntityAction(playerSP, CPacketEntityAction.Action.START_FALL_FLYING));
        }
    }

    public static boolean fireRenderCapeCheckEvent(EntityPlayer player) {
        RenderCapeCheckEvent evt = new RenderCapeCheckEvent(player);
        MinecraftForge.EVENT_BUS.post(evt);
        Event.Result doRender = evt.getResult();

        if(doRender == Event.Result.DEFAULT) {
            return player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA;
        } else {
            return doRender == Event.Result.ALLOW;
        }
    }
}
