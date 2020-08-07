package top.theillusivec4.caelus.mixin;

import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.caelus.api.CaelusApi;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

  @Inject(at = @At("RETURN"), method = "func_234570_el_()Lnet/minecraft/entity/ai/attributes/AttributeModifierMap$MutableAttribute;")
  private static void createAttributes(CallbackInfoReturnable<MutableAttribute> cb) {
    cb.getReturnValue().createMutableAttribute(CaelusApi.ELYTRA_FLIGHT.get());
  }
}
