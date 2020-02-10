package top.theillusivec4.caelus.common.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.caelus.api.capability.IRenderElytra;
import top.theillusivec4.caelus.api.capability.IRenderElytra.State;
import top.theillusivec4.caelus.api.capability.RenderElytraCapability;

public class CaelusCapability {

  public static void register() {
    CapabilityManager.INSTANCE.register(IRenderElytra.class,
        new Capability.IStorage<IRenderElytra>() {
          @Override
          public INBT writeNBT(Capability<IRenderElytra> capability, IRenderElytra instance,
              Direction side) {
            int state = 0;

            if (instance.getRenderState() == State.ELYTRA) {
              state = 1;
            } else if (instance.getRenderState() == State.ENCHANTED) {
              state = 2;
            }
            return IntNBT.valueOf(state);
          }

          @Override
          public void readNBT(
              Capability<IRenderElytra> capability, IRenderElytra instance, Direction side,
              INBT nbt) {
            int state = ((IntNBT) nbt).getInt();
            State renderState = State.NONE;

            if (state == 1) {
              renderState = State.ELYTRA;
            } else if (state == 2) {
              renderState= State.ENCHANTED;
            }
            instance.setRenderState(renderState);
          }
        }, RenderElytraWrapper::new);
  }

  public static ICapabilityProvider createProvider() {
    return new Provider();
  }

  public static class RenderElytraWrapper implements IRenderElytra {

    State renderState = State.NONE;

    @Override
    public State getRenderState() {
      return renderState;
    }

    @Override
    public void setRenderState(State renderState) {
      this.renderState = renderState;
    }
  }

  public static class Provider implements ICapabilitySerializable<INBT> {

    final LazyOptional<IRenderElytra> optional;
    final IRenderElytra handler;

    Provider() {
      this.handler = new RenderElytraWrapper();
      this.optional = LazyOptional.of(() -> handler);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nullable Capability<T> capability, Direction facing) {
      return RenderElytraCapability.RENDER_ELYTRA.orEmpty(capability, optional);
    }

    @Override
    public INBT serializeNBT() {
      return RenderElytraCapability.RENDER_ELYTRA.writeNBT(handler, null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
      RenderElytraCapability.RENDER_ELYTRA.readNBT(handler, null, nbt);
    }
  }
}
