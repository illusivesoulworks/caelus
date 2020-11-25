/*
 * Copyright (c) 2019-2020 C4
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

package top.theillusivec4.caelus.api;

import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import top.theillusivec4.caelus.core.CaelusApiImpl;

public interface CaelusApi {

  static CaelusApi getInstance() {
    return CaelusApiImpl.INSTANCE;
  }

  String MODID = "caelus";

  /**
   * The elytra flight attribute, will provide elytra flight if the value is 1.0 or above. No flight
   * otherwise.
   */
  EntityAttribute ELYTRA_FLIGHT = new ClampedEntityAttribute("caelus.elytra_flight", 0.0D, 0.0D,
      1.0D).setTracked(true);

  /**
   * The attribute modifier used for the vanilla elytra item.
   */
  EntityAttributeModifier VANILLA_ELYTRA_MODIFIER = new EntityAttributeModifier(
      UUID.fromString("5b6c3728-9c24-42ae-83ac-70d61d8b8199"), "Elytra modifier", 1.0f,
      EntityAttributeModifier.Operation.ADDITION);

  /**
   * Checks whether or not an entity is able to elytra fly.
   *
   * @param livingEntity The entity to check for elytra flight capabilities
   * @return True if the entity can elytra fly, false otherwise.
   */
  boolean canFly(LivingEntity livingEntity);

  boolean isElytra(Item item);
}
