package top.theillusivec4.caelus.loader.mixin;

import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.loader.common.MixinHooks;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

  @Inject(at = @At("RETURN"), method = "createPlayerAttributes() Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;")
  private static void createAttributes(CallbackInfoReturnable<Builder> cb) {
    cb.getReturnValue().add(CaelusApi.ELYTRA_FLIGHT);
  }

  @Inject(at = @At("TAIL"), method = "tick")
  public void onTick(CallbackInfo cb) {
    @SuppressWarnings("ConstantConditions") PlayerEntity playerEntity = (PlayerEntity) (Object) this;
    MixinHooks.checkEquippedElytra(playerEntity);
  }
}
