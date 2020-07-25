package top.theillusivec4.caelus.loader.client;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import top.theillusivec4.caelus.api.RenderElytraInfo;
import top.theillusivec4.caelus.api.event.RenderElytraCallback;
import top.theillusivec4.caelus.core.client.RenderElytraInfoImpl;
import top.theillusivec4.caelus.loader.common.MixinHooks;
import top.theillusivec4.caelus.loader.common.NetworkHandler;

public class ClientMixinHooks {

  public static void checkFlight() {
    ClientPlayerEntity playerEntity = MinecraftClient.getInstance().player;

    if (playerEntity != null && MixinHooks.canFly(playerEntity)) {
      ClientSidePacketRegistry.INSTANCE
          .sendToServer(NetworkHandler.START_FLYING, new PacketByteBuf(Unpooled.buffer()));
    }
  }

  public static boolean canRenderCape(PlayerEntity playerEntity) {
    RenderElytraInfo renderElytraEvent = new RenderElytraInfoImpl(playerEntity);
    RenderElytraCallback.EVENT.invoker().process(playerEntity, renderElytraEvent);
    return !renderElytraEvent.canRender();
  }
}
