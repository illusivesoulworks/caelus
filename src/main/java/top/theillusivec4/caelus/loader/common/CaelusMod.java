package top.theillusivec4.caelus.loader.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;
import top.theillusivec4.caelus.api.CaelusApi;

public class CaelusMod implements ModInitializer {

  @Override
  public void onInitialize() {
    Registry.register(Registry.ATTRIBUTE, "caelus.elytra_flight", CaelusApi.ELYTRA_FLIGHT);
    NetworkHandler.setup();
  }
}
