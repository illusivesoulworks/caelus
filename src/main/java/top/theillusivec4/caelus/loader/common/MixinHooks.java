package top.theillusivec4.caelus.loader.common;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import top.theillusivec4.caelus.api.CaelusApi;

public class MixinHooks {

  public static boolean canFly(LivingEntity livingEntity) {
    return !livingEntity.isOnGround() && !livingEntity.hasVehicle() && !livingEntity
        .hasStatusEffect(StatusEffects.LEVITATION) && CaelusApi.getInstance().canFly(livingEntity);
  }

  public static void checkEquippedElytra(PlayerEntity playerEntity) {
    EntityAttributeInstance attributeInstance = playerEntity
        .getAttributeInstance(CaelusApi.ELYTRA_FLIGHT);

    if (attributeInstance != null) {
      attributeInstance.removeModifier(CaelusApi.VANILLA_ELYTRA_MODIFIER);
      ItemStack stack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);

      if (stack.getItem() instanceof ElytraItem && !attributeInstance
          .hasModifier(CaelusApi.VANILLA_ELYTRA_MODIFIER) && ElytraItem.isUsable(stack)) {
        attributeInstance.removeModifier(CaelusApi.VANILLA_ELYTRA_MODIFIER);
      }
    }
  }
}
