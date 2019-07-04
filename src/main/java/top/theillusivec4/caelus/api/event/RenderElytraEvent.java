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
 * This event is fired when the game checks if the elytra should be rendered.<br>
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

    private Result renderElytra = Result.DEFAULT;
    private Result renderEnchantmentGlow = Result.DEFAULT;

    public RenderElytraEvent(LivingEntity livingEntity) {
        super(livingEntity);
    }

    public Result getRenderElytra() {
        return renderElytra;
    }

    /**
     * Result.ALLOW enables elytra rendering
     * Result.DEFAULT checks if the entity is wearing an elytra item in the chest slot and renders the elytra if found
     * Result.DENY prevents elytra rendering
     *
     * Note that this result will also affect cape rendering as rendering elytras will disable rendering capes;
     * however, elytras will still render with cape textures when appropriate
     */
    public void setRenderElytra(Result renderElytra) {
        this.renderElytra = renderElytra;
    }

    public Result getRenderEnchantmentGlow() {
        return renderEnchantmentGlow;
    }

    /**
     * Result.ALLOW enables enchantment rendering on the elytra
     * Result.DEFAULT checks if the ItemStack found in the entity's chest slot is enchanted and renders the enchantment
     * on the elytra if true
     * Result.DENY prevents enchantment rendering on the elytra
     */
    public void setRenderEnchantmentGlow(Result renderEnchantmentGlow) {
        this.renderEnchantmentGlow = renderEnchantmentGlow;
    }
}
