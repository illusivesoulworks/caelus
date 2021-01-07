package top.theillusivec4.caelus.mixin;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.caelus.client.ClientMixinHooks;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

  @Inject(at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/client/entity/player/ClientPlayerEntity.getItemStackFromSlot (Lnet/minecraft/inventory/EquipmentSlotType;)Lnet/minecraft/item/ItemStack;"), method = "livingTick")
  public void _caelus_checkFlight(CallbackInfo cb) {
    ClientMixinHooks.checkFlight();
  }

  @ModifyVariable(at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/client/entity/player/ClientPlayerEntity.getItemStackFromSlot (Lnet/minecraft/inventory/EquipmentSlotType;)Lnet/minecraft/item/ItemStack;"), method = "livingTick")
  public ItemStack _caelus_changeEquippedStack(ItemStack stack) {
    return ItemStack.EMPTY;
  }
}
