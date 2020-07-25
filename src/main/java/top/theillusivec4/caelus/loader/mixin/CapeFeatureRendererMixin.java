package top.theillusivec4.caelus.loader.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.caelus.loader.client.ClientMixinHooks;

@Mixin(CapeFeatureRenderer.class)
public class CapeFeatureRendererMixin {

  @Inject(at = @At(value = "INVOKE", target = "net/minecraft/client/network/AbstractClientPlayerEntity.getEquippedStack (Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"), method = "render", cancellable = true)
  public void onElytraCheck(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
      int i, AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, float h,
      float j, float k, float l, CallbackInfo cb) {

    if (!ClientMixinHooks.canRenderCape(abstractClientPlayerEntity)) {
      cb.cancel();
    }
  }
}
