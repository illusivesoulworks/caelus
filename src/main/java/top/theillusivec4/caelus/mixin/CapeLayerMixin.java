package top.theillusivec4.caelus.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.caelus.client.ClientMixinHooks;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {

  @Inject(at = @At(value = "HEAD"), method = "render", cancellable = true)
  public void _caelus_capeCheck(MatrixStack matrixStack, IRenderTypeBuffer bufferIn,
      int packedLightIn, AbstractClientPlayerEntity entitylivingbaseIn, float limbSwing,
      float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
      float headPitch, CallbackInfo cb) {

    if (!ClientMixinHooks.canRenderCape(entitylivingbaseIn)) {
      cb.cancel();
    }
  }
}
