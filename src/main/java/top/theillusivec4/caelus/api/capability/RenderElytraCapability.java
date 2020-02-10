package top.theillusivec4.caelus.api.capability;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import top.theillusivec4.caelus.Caelus;

public class RenderElytraCapability {

  @CapabilityInject(IRenderElytra.class)
  public static final Capability<IRenderElytra> RENDER_ELYTRA;

  public static final ResourceLocation ID_RENDER = new ResourceLocation(Caelus.MODID, "render");

  static {
    RENDER_ELYTRA = null;
  }
}
