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

import java.util.UUID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CaelusApi {

  public static final String MODID = "caelus";
  public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister
      .create(Attribute.class, MODID);

  /**
   * The elytra flight attribute, will provide elytra flight if the value is 1.0 or above. No flight
   * otherwise.
   */
  public static final RegistryObject<Attribute> ELYTRA_FLIGHT = ATTRIBUTES.register("elytra_flight",
      () -> new RangedAttribute("caelus.elytraFlight", 0.0d, 0.0d, 1.0d).setShouldWatch(true));

  /**
   * The attribute modifier used for the vanilla elytra item.
   */
  public static final AttributeModifier ELYTRA_MODIFIER = new AttributeModifier(
      UUID.fromString("5b6c3728-9c24-42ae-83ac-70d61d8b8199"), "Elytra modifier", 1.0f,
      AttributeModifier.Operation.ADDITION);

  /**
   * Tag for elytra items
   */
  public static final ITag.INamedTag<Item> ELYTRA = ForgeTagHandler.createOptionalTag(
      ForgeRegistries.ITEMS, new ResourceLocation("forge", "elytra"));

  /**
   * Tag for elytra-like items
   */
  public static final ITag.INamedTag<Item> ELYTRA_LIKE = ForgeTagHandler.createOptionalTag(
      ForgeRegistries.ITEMS, new ResourceLocation("forge", "elytra_like"));

  /**
   * Checks whether or not an entity is able to elytra fly. Checks against the elytra flight
   * attribute if the entity is a {@link PlayerEntity}. Otherwise checks against the ItemStack in
   * the chest slot to see if it's an elytra item.
   *
   * @param livingEntity The entity to check for elytra flight capabilities
   * @return True if the entity can elytra fly, false otherwise.
   */
  public static boolean canElytraFly(LivingEntity livingEntity) {

    if (!(livingEntity instanceof PlayerEntity)) {
      ItemStack stack = livingEntity.getItemStackFromSlot(EquipmentSlotType.CHEST);
      return canElytraFly(livingEntity, stack);
    }
    ModifiableAttributeInstance attribute = livingEntity.getAttribute(ELYTRA_FLIGHT.get());
    return attribute != null && attribute.getValue() >= 1.0d;
  }

  /**
   * Checks whether or not an ItemStack is able to grant elytra flight.
   *
   * @param livingEntity The entity to check for elytra flight capabilities
   * @return True if the ItemStack can grant elytra flight, false otherwise.
   */
  public static boolean canElytraFly(LivingEntity livingEntity, ItemStack stack) {
    return stack.canElytraFly(livingEntity) || isElytraLike(stack);
  }

  /**
   * Checks whether the stack is an elytra.
   *
   * @param stack The ItemStack being checked
   * @return True if the ItemStack is an elytra
   */
  public static boolean isElytra(ItemStack stack) {
    return stack.getItem() instanceof ElytraItem || ELYTRA.contains(stack.getItem());
  }

  /**
   * Checks whether the stack is an elytra-like item. These items are NOT considered elytra but
   * enable elytra flight like one.
   *
   * @param stack The ItemStack being checked
   * @return True if the ItemStack is an elytra-like item
   */
  public static boolean isElytraLike(ItemStack stack) {
    return ELYTRA_LIKE.contains(stack.getItem());
  }
}
