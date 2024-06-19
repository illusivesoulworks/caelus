package top.theillusivec4.caelus.mixin.core;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.common.CaelusApiImpl;

@Mixin(value = CaelusApi.class, remap = false)
public class MixinCaelusApi {

  @Inject(at = @At("HEAD"), method = "getInstance", cancellable = true)
  private static void caelus$getInstance(final CallbackInfoReturnable<CaelusApi> cir) {
    cir.setReturnValue(CaelusApiImpl.INSTANCE);
  }
}
