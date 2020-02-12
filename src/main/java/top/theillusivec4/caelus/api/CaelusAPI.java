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

package top.theillusivec4.caelus.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CaelusAPI {

  /**
   * The elytra flight attribute, will provide elytra flight if the value is 1.0 or above. No flight
   * otherwise.
   */
  public static final IAttribute ELYTRA_FLIGHT = new RangedAttribute(null, "caelus.elytraFlight",
      0.0d, 0.0d, 1.0d).setShouldWatch(true);

  /**
   * The attribute modifier used for the vanilla elytra item. Note: Modders, do not use this for
   * most other implementations! For internal use only. This is only here to provide access to the
   * vanilla elytra's specific attribute modifier if absolutely needed.
   */
  public static final AttributeModifier ELYTRA_MODIFIER = new AttributeModifier(
      UUID.fromString("5b6c3728-9c24-42ae-83ac-70d61d8b8199"), "Elytra modifier", 1.0f,
      AttributeModifier.Operation.ADDITION);

  /**
   * The attribute modifier used for disabling elytra flight when the toggle keybinding is pressed.
   * Note: Modders, do not use this for most other implementations! For internal use only. This is
   * only here to provide access to the specific disabling modifier if absolutely needed.
   */
  public static final AttributeModifier DISABLE_FLIGHT = new AttributeModifier(
      UUID.fromString("faadb8f3-c95c-44ce-ba68-0f31bd1b47d5"), "Toggled modifier", -1.0d,
      AttributeModifier.Operation.MULTIPLY_TOTAL);

  /**
   * Checks whether or not an entity is able to elytra fly. Checks against the elytra flight
   * attribute if the entity is a {@link PlayerEntity} Otherwise checks against the ItemStack in the
   * chest slot to see if it's a vanilla elytra item.
   *
   * @param livingEntity The entity to check for elytra flight capabilities
   * @return True if the entity can elytra fly, false otherwise.
   */
  public static boolean canElytraFly(LivingEntity livingEntity) {

    if (!(livingEntity instanceof PlayerEntity)) {
      ItemStack stack = livingEntity.getItemStackFromSlot(EquipmentSlotType.CHEST);
      return stack.getItem() == Items.ELYTRA && ElytraItem.isUsable(stack);
    }

    return livingEntity.getAttribute(CaelusAPI.ELYTRA_FLIGHT).getValue() >= 1.0d;
  }

  public static List<Function<LivingEntity, ElytraRender>> renderFunctions = new ArrayList<>();

  /**
   * Retrieves the render state of the elytra on the specified entity.
   *
   * @param livingEntity The entity to check for the elytra render.
   * @return The render state of the elytra on the entity as an {@link ElytraRender}
   */
  public static ElytraRender getElytraRender(LivingEntity livingEntity) {
    ElytraRender render = ElytraRender.NONE;

    for (Function<LivingEntity, ElytraRender> func : renderFunctions) {
      if (func.apply(livingEntity) == ElytraRender.NORMAL) {
        render = ElytraRender.NORMAL;
      } else if (func.apply(livingEntity) == ElytraRender.ENCHANTED) {
        return ElytraRender.ENCHANTED;
      }
    }
    return render;
  }

  public static final class IMC {

    public static final String ELYTRA_RENDER = "elytraRender";
  }

  public enum ElytraRender {
    NONE, NORMAL, ENCHANTED
  }
}
