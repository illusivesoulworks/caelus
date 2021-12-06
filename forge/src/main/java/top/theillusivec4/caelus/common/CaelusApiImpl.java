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

package top.theillusivec4.caelus.common;

import java.util.UUID;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.caelus.api.CaelusApi;

public class CaelusApiImpl extends CaelusApi {

  public static final CaelusApi INSTANCE = new CaelusApiImpl();

  public static final String MOD_ID = "caelus";
  public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister
      .create(Attribute.class, MOD_ID);

  private static final RegistryObject<Attribute> FALL_FLYING = ATTRIBUTES.register("fall_flying",
      () -> new RangedAttribute("caelus.fallFlying", 0.0d, 0.0d, 1.0d).setSyncable(true));
  private static final AttributeModifier ELYTRA_MODIFIER =
      new AttributeModifier(UUID.fromString("5b6c3728-9c24-42ae-83ac-70d61d8b8199"),
          "Elytra modifier", 1.0f, AttributeModifier.Operation.ADDITION);

  @Override
  public String getModId() {
    return MOD_ID;
  }

  @Override
  public Attribute getFlightAttribute() {
    return FALL_FLYING.get();
  }

  @Override
  public AttributeModifier getElytraModifier() {
    return ELYTRA_MODIFIER;
  }

  @Override
  public boolean canFly(LivingEntity livingEntity) {
    AttributeInstance attribute = livingEntity.getAttribute(FALL_FLYING.get());

    if (attribute != null) {
      return attribute.getValue() >= 1.0d;
    }
    ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
    return stack.getItem() instanceof ElytraItem && ElytraItem.isFlyEnabled(stack);
  }
}
