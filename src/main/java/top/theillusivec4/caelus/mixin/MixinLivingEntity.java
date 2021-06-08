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

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import top.theillusivec4.caelus.common.util.CommonCaelusHooks;

@SuppressWarnings("unused")
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

  public MixinLivingEntity(EntityType<?> type, World world) {
    super(type, world);
  }

  @SuppressWarnings("ConstantConditions")
  @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"), method = "tickFallFlying")
  public boolean caelus$setFlag(boolean value) {
    return CommonCaelusHooks
        .canFly((LivingEntity) (Object) this, this.getFlag(Entity.FALL_FLYING_FLAG_INDEX));
  }
}
