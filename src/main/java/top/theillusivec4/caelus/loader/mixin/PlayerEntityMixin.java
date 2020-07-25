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
