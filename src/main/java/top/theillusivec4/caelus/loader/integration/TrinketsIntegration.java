package top.theillusivec4.caelus.loader.integration;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class TrinketsIntegration {

  public static ItemStack getCapeStack(PlayerEntity playerEntity) {
    return TrinketsApi.getTrinketComponent(playerEntity).getStack(SlotGroups.CHEST, Slots.CAPE);
  }
}
