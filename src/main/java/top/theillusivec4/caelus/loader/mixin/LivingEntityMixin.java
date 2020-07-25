package top.theillusivec4.caelus.loader.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import top.theillusivec4.caelus.loader.common.MixinHooks;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

  @SuppressWarnings("ConstantConditions")
  @ModifyVariable(at = @At(value = "INVOKE", target = "net/minecraft/entity/LivingEntity.setFlag (IZ)V"), method = "initAi")
  public boolean changeFlag(boolean flag) {
    return flag || MixinHooks.canFly((LivingEntity) (Object) this);
  }
}
