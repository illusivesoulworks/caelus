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

package top.theillusivec4.caelus.common;

import java.util.UUID;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import top.theillusivec4.caelus.api.CaelusApi;

public class CaelusApiImpl extends CaelusApi {

  public static final CaelusApi INSTANCE = new CaelusApiImpl();

  private static final EntityAttribute ELYTRA_FLIGHT =
      new ClampedEntityAttribute("attribute.name.generic.caelus.flight", 0.0D, 0.0D, 1.0D)
          .setTracked(true);

  private static final EntityAttributeModifier ELYTRA_MODIFIER =
      new EntityAttributeModifier(UUID.fromString("5b6c3728-9c24-42ae-83ac-70d61d8b8199"),
          "Elytra modifier", 1.0f, EntityAttributeModifier.Operation.ADDITION);

  @Override
  public String getModId() {
    return "caelus";
  }

  @Override
  public EntityAttribute getFlightAttribute() {
    return ELYTRA_FLIGHT;
  }

  @Override
  public EntityAttributeModifier getElytraModifier() {
    return ELYTRA_MODIFIER;
  }

  @Override
  public boolean canFly(LivingEntity livingEntity) {
    EntityAttributeInstance attribute = livingEntity.getAttributeInstance(ELYTRA_FLIGHT);

    if (attribute != null) {
      return attribute.getValue() >= 1.0d;
    }
    ItemStack stack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
    return stack.getItem() instanceof ElytraItem && ElytraItem.isUsable(stack);
  }
}
