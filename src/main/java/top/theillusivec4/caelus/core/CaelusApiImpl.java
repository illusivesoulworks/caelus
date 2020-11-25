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

package top.theillusivec4.caelus.core;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import top.theillusivec4.caelus.api.CaelusApi;

public class CaelusApiImpl implements CaelusApi {

  public static final CaelusApi INSTANCE = new CaelusApiImpl();
  public static final Tag<Item> ELYTRA =
      TagRegistry.item(new Identifier("c", "elytra"));

  @Override
  public boolean canFly(LivingEntity livingEntity) {

    if (!(livingEntity instanceof PlayerEntity)) {
      ItemStack stack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
      return ELYTRA.contains(stack.getItem()) && ElytraItem.isUsable(stack);
    }
    EntityAttributeInstance attribute = livingEntity.getAttributeInstance(CaelusApi.ELYTRA_FLIGHT);
    return attribute != null && attribute.getValue() >= 1.0d;
  }

  @Override
  public boolean isElytra(Item item) {
    return ELYTRA.contains(item);
  }
}
