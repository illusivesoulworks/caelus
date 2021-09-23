package top.theillusivec4.caelus.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.PacketDistributor;
import top.theillusivec4.caelus.api.RenderElytraEvent;
import top.theillusivec4.caelus.common.MixinHooks;
import top.theillusivec4.caelus.common.network.CPacketSetFlight;
import top.theillusivec4.caelus.common.network.NetworkHandler;

public class ClientMixinHooks {

  public static void checkFlight() {
    LocalPlayer playerEntity = Minecraft.getInstance().player;

    if (playerEntity != null && MixinHooks.startFlight(playerEntity)) {
      NetworkHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new CPacketSetFlight());
    }
  }

  public static boolean canRenderCape(Player playerEntity) {
    RenderElytraEvent renderElytraEvent = new RenderElytraEvent(playerEntity);
    MinecraftForge.EVENT_BUS.post(renderElytraEvent);
    return !renderElytraEvent.canRender();
  }
}
