package top.theillusivec4.caelus.core;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import top.theillusivec4.caelus.api.CaelusApi;

public class CaelusApiImpl implements CaelusApi {

  public static final CaelusApi INSTANCE = new CaelusApiImpl();

  @Override
  public boolean canFly(LivingEntity livingEntity) {

    if (!(livingEntity instanceof PlayerEntity)) {
      ItemStack stack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
      return stack.getItem() == Items.ELYTRA && ElytraItem.isUsable(stack);
    }
    EntityAttributeInstance attribute = livingEntity.getAttributeInstance(CaelusApi.ELYTRA_FLIGHT);
    return attribute != null && attribute.getValue() >= 1.0d;
  }
}
