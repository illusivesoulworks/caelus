package top.theillusivec4.caelus.mixin;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.caelus.api.CaelusApi;

@Mixin(Player.class)
public class PlayerEntityMixin {

  @Inject(at = @At("RETURN"), method = "createAttributes()Lnet/minecraft/entity/ai/attributes/AttributeModifierMap$MutableAttribute;")
  private static void createAttributes(CallbackInfoReturnable<Builder> cb) {
    cb.getReturnValue().add(CaelusApi.ELYTRA_FLIGHT.get());
  }
}
