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

package top.theillusivec4.caelus.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.caelus.common.util.ClientCaelusHooks;

@SuppressWarnings("unused")
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

  @Inject(at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/client/network/ClientPlayerEntity.getEquippedStack (Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"), method = "tickMovement")
  public void caelus$onElytraCheck(CallbackInfo cb) {
    ClientCaelusHooks.checkFlight();
  }

  @ModifyVariable(at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/client/network/ClientPlayerEntity.getEquippedStack (Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"), method = "tickMovement")
  public ItemStack caelus$swapFillerStack(ItemStack stack) {
    return ItemStack.EMPTY;
  }
}
