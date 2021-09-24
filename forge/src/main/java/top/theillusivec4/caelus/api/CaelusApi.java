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

package top.theillusivec4.caelus.api;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public abstract class CaelusApi {

  public static CaelusApi getInstance() {
    throw new IllegalStateException("Missing API implementation from Caelus!");
  }

  public abstract String getModId();

  /**
   * The elytra flight attribute, will provide elytra flight if the value is 1.0 or above. No flight
   * otherwise.
   */
  public abstract Attribute getFlightAttribute();

  /**
   * The attribute modifier used for the vanilla elytra item.
   */
  public abstract AttributeModifier getElytraModifier();

  /**
   * Checks whether or not an entity is able to elytra fly.
   *
   * @param livingEntity The entity to check for elytra flight capabilities
   * @return True if the entity can elytra fly, false otherwise
   */
  public abstract boolean canFly(LivingEntity livingEntity);
}
