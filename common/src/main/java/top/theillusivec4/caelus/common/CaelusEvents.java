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

package top.theillusivec4.caelus.common;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.platform.Services;

public class CaelusEvents {

  public static void livingTick(LivingEntity livingEntity) {
    AttributeInstance attributeInstance =
        livingEntity.getAttribute(CaelusApi.getInstance().getFlightAttribute());

    if (attributeInstance != null) {
      AttributeModifier elytraModifier = CaelusApi.getInstance().getElytraModifier();
      attributeInstance.removeModifier(elytraModifier.id());
      ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);

      if (Services.CAELUS.canFly(stack, livingEntity) &&
          !attributeInstance.hasModifier(elytraModifier)) {
        attributeInstance.addTransientModifier(elytraModifier);
      }
    }
  }
}
