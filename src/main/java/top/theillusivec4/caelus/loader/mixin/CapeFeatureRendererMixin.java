/*
 * Copyright (c) 2019-2020 C4
 *
 * This file is part of Caelus, a mod made for Minecraft.
 *
 * Caelus is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Caelus is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Caelus.  If not, see <https://www.gnu.org/licenses/>.
 */

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
