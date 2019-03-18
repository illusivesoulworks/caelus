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

package top.theillusivec4.caelus.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;

/**
 * This event is fired when the game checks if the player cape should be rendered.<br>
 * Failing this check will disable the cape rendering.<br>
 *
 * This event has a result. {@link HasResult}<br>
 *
 * setResult(ALLOW) informs game that the player cape should be rendered.<br>
 * setResult(DEFAULT) causes game to check if the player is wearing an elytra in the chest slot and will disable the
 * rendering if one is found.
 */
@Event.HasResult
public class RenderCapeCheckEvent extends PlayerEvent {

    public RenderCapeCheckEvent(EntityPlayer player) {
        super(player);
    }
}
