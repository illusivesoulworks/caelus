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

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * This event is fired when the game checks if a non-vanilla elytra should be rendered.<br>
 * This event is fired client-side only.<br>
 *
 * <br>
 * This event is not {@link Cancelable}. <br>
 * <br>
 * This event does not have a result. {@link HasResult} <br>
 * <br>
 * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
 **/
public class RenderElytraEvent extends LivingEvent {

    private boolean renderElytra = false;
    private boolean renderEnchantmentGlow = false;

    public RenderElytraEvent(LivingEntity livingEntity) {
        super(livingEntity);
    }

    public boolean getRenderElytra() {
        return renderElytra;
    }

    /**
     * Determines whether or not to render the elytra model given the entity is not already wearing an elytra in the chest slot
     *
     * Note that this result will also affect cape rendering as rendering elytras will disable rendering capes;
     * however, elytras will still render with cape textures when appropriate
     */
    public void setRenderElytra(boolean renderElytra) {
        this.renderElytra = renderElytra;
    }

    public boolean getRenderEnchantmentGlow() {
        return renderEnchantmentGlow;
    }

    /**
     * Determines whether or not the elytra texture will have an enchantment glow
     */
    public void setRenderEnchantmentGlow(boolean renderEnchantmentGlow) {
        this.renderEnchantmentGlow = renderEnchantmentGlow;
    }
}
