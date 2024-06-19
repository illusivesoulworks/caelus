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

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.caelus.CaelusConstants;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.common.registry.RegistryObject;
import top.theillusivec4.caelus.common.registry.RegistryProvider;
import top.theillusivec4.caelus.platform.Services;

public class CaelusApiImpl extends CaelusApi {

  public static final CaelusApi INSTANCE = new CaelusApiImpl();
  public static final RegistryProvider<Attribute> ATTRIBUTES =
      RegistryProvider.get(Registries.ATTRIBUTE, CaelusConstants.MOD_ID);

  private static final RegistryObject<Attribute> FALL_FLYING = ATTRIBUTES.register("fall_flying",
      () -> new RangedAttribute("caelus.fallFlying", 0.1d, 0.0d, 1.0d).setSyncable(true));
  private static final AttributeModifier ELYTRA_MODIFIER =
      new AttributeModifier(ResourceLocation.fromNamespaceAndPath(CaelusConstants.MOD_ID, "elytra"),
          1.0f, AttributeModifier.Operation.ADD_VALUE);

  public static void setup() {
    // NO-OP
  }

  @Override
  public String getModId() {
    return CaelusConstants.MOD_ID;
  }

  @Override
  public Holder<Attribute> getFallFlyingAttribute() {
    return FALL_FLYING.asHolder();
  }

  @Override
  public AttributeModifier getElytraModifier() {
    return ELYTRA_MODIFIER;
  }

  @Override
  public TriState canFallFly(LivingEntity livingEntity) {
    Holder<Attribute> att = this.getFallFlyingAttribute();
    AttributeInstance attribute = livingEntity.getAttribute(att);

    if (attribute != null) {
      double val = attribute.getValue();

      if (val >= 1.0d) {
        return TriState.ALLOW;
      } else if (val > 0.0d) {
        return TriState.DEFAULT;
      }
      return TriState.DENY;
    }
    ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.CHEST);

    if (Services.CAELUS.canFly(stack, livingEntity)) {
      return TriState.ALLOW;
    }
    return TriState.DEFAULT;
  }

  @Override
  public boolean canFallFly(LivingEntity livingEntity, boolean checkDefaults) {
    return checkDefaults ? canFallFly(livingEntity) != TriState.DENY :
        canFallFly(livingEntity) == TriState.ALLOW;
  }
}
