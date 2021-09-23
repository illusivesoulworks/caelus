package top.theillusivec4.caelus.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import top.theillusivec4.caelus.common.MixinHooks;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

  public LivingEntityMixin(EntityType<?> type, Level world) {
    super(type, world);
  }

  @SuppressWarnings("ConstantConditions")
  @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"), method = "updateElytra")
  public boolean _caelus_setFlag(boolean value) {
    return MixinHooks.canFly((LivingEntity) (Object) this, this.getSharedFlag(7));
  }
}
