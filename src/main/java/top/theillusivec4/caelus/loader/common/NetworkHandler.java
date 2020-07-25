package top.theillusivec4.caelus.loader.common;

import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import top.theillusivec4.caelus.api.CaelusApi;

public class NetworkHandler {

  public static final Identifier START_FLYING = new Identifier(CaelusApi.MODID, "start_flying");

  public static void setup() {
    ServerSidePacketRegistry.INSTANCE.register(START_FLYING,
        ((packetContext, packetByteBuf) -> packetContext.getTaskQueue().execute(() -> {
          PlayerEntity playerEntity = packetContext.getPlayer();

          if (playerEntity != null) {
            playerEntity.stopFallFlying();

            if (MixinHooks.canFly(playerEntity)) {
              playerEntity.startFallFlying();
            }
          }
        })));
  }
}
