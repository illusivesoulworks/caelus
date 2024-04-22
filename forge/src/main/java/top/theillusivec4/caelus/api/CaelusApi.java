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
import org.jetbrains.annotations.ApiStatus;

public abstract class CaelusApi {

  public static CaelusApi getInstance() {
    throw new IllegalStateException("Missing API implementation from Caelus!");
  }

  public abstract String getModId();

  /**
   * The fall flight attribute.
   * <br>
   * Greater than or equal to 1.0 - Always grants fall flying.
   * Between 0.0 (exclusive) and 1.0 (exclusive) - Falls back to default behavior.
   * Less than or equal to 0.0 - Denies any fall flying.
   */
  public abstract Attribute getFlightAttribute();

  /**
   * The attribute modifier used for the vanilla elytra item.
   */
  public abstract AttributeModifier getElytraModifier();

  /**
   * Checks whether an entity is able to fall fly.
   *
   * @param livingEntity The entity to check for fall flight capabilities
   * @return {@link TriState} based on the entity fall flight attribute
   */
  public abstract TriState canFallFly(LivingEntity livingEntity);

  /**
   * @deprecated See {@link CaelusApi#canFallFly(LivingEntity)}
   */
  @Deprecated
  @ApiStatus.ScheduledForRemoval(inVersion = "1.21")
  public abstract boolean canFly(LivingEntity livingEntity);

  public enum TriState {
    ALLOW,
    DEFAULT,
    DENY
  }
}
