package top.theillusivec4.caelus.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.common.CaelusApiImpl;

@SuppressWarnings("unused")
@Mixin(CaelusApi.class)
public class MixinCaelusApi {

  @Inject(at = @At("HEAD"), method = "getInstance", remap = false, cancellable = true)
  private static void caelus$getInstance(CallbackInfoReturnable<CaelusApi> cir) {
    cir.setReturnValue(CaelusApiImpl.INSTANCE);
  }
}
