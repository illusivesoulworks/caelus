/*
 * Copyright (C) 2019-2023 C4
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
 */

package com.illusivesoulworks.caelus.api;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

/**
 * The public-facing Caelus API instance.
 *
 * <p>To obtain a working instance, call {@link #getInstance()}.</p>
 */
public abstract class CaelusApi {

  private static CaelusApi instance;

  /**
   * Returns the public-facing Caelus API instance.
   */
  public static CaelusApi getInstance() {

    if (instance == null) {
      throw new RuntimeException("Missing Caelus API implementation!");
    }
    return instance;
  }

  public abstract String getModId();

  /**
   * The fall flight attribute.
   * <br>
   * Greater than or equal to 1.0 - Always grants fall flying.
   * Between 0.0 (exclusive) and 1.0 (exclusive) - Falls back to default behavior.
   * Less than or equal to 0.0 - Denies any fall flying.
   */
  public abstract Holder<Attribute> getFallFlyingAttribute();

  /**
   * The attribute modifier used for the vanilla elytra item. In most cases, modded items should
   * use a separate attribute modifier.
   */
  public abstract AttributeModifier getElytraModifier();

  /**
   * Checks whether an entity is able to fall fly.
   * <br>
   * {@link TriState#ALLOW} - Entity can always fall fly.
   * {@link TriState#DEFAULT} - Falls back to default behavior.
   * {@link TriState#DENY} - Entity cannot fall fly.
   *
   * @param livingEntity The entity to check for fall flight capabilities
   * @return {@link TriState} based on the entity fall flight attribute
   */
  public abstract TriState canFallFly(LivingEntity livingEntity);

  /**
   * Checks whether an entity is able to fall fly.
   * <br>
   *
   * @param livingEntity  The entity to check for fall flight capabilities
   * @param checkDefaults True to include default behavior, such as fall flying granted by other mods
   * @return True if the entity can fall fly, false otherwise
   */
  public abstract boolean canFallFly(LivingEntity livingEntity, boolean checkDefaults);

  public enum TriState {
    ALLOW,
    DEFAULT,
    DENY
  }
}
