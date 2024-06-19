package com.illusivesoulworks.caelus.mixin.core;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.illusivesoulworks.caelus.api.CaelusApi;
import com.illusivesoulworks.caelus.common.CaelusApiImpl;

@Mixin(value = CaelusApi.class, remap = false)
public class MixinCaelusApi {

  @Inject(at = @At("HEAD"), method = "getInstance", cancellable = true)
  private static void caelus$getInstance(final CallbackInfoReturnable<CaelusApi> cir) {
    cir.setReturnValue(CaelusApiImpl.INSTANCE);
  }
}
