/*
 * Copyright (C) 2019-2023 C4
 *
 * Caelus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Caelus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * and the GNU Lesser General Public License along with Caelus.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package top.theillusivec4.caelus.mixin.core;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.caelus.mixin.util.ClientMixinHooks;

@Mixin(CapeLayer.class)
public abstract class MixinCapeLayer {

  @Inject(
      at = @At(
          value = "INVOKE",
          target = "net/minecraft/client/player/AbstractClientPlayer.getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"),
      method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V",
      cancellable = true
  )
  private void caelus$canRenderCape(PoseStack pMatrixStack, MultiBufferSource pBuffer,
                                    int pPackedLight, AbstractClientPlayer pLivingEntity,
                                    float pLimbSwing, float pLimbSwingAmount, float pPartialTicks,
                                    float pAgeInTicks, float pNetHeadYaw, float pHeadPitch,
                                    CallbackInfo cb) {

    if (!ClientMixinHooks.canRenderCape(pLivingEntity)) {
      cb.cancel();
    }
  }
}
