package top.theillusivec4.caelus.loader.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.caelus.loader.client.ClientMixinHooks;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

  @Inject(at = @At(value = "INVOKE", target = "net/minecraft/client/network/ClientPlayerEntity.getEquippedStack (Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"), method = "tickMovement")
  public void onElytraCheck(CallbackInfo cb) {
    ClientMixinHooks.checkFlight();
  }
}
