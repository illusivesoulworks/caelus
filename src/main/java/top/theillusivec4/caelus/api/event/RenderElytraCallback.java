package top.theillusivec4.caelus.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import top.theillusivec4.caelus.api.RenderElytraInfo;

public interface RenderElytraCallback {

  Event<RenderElytraCallback> EVENT = EventFactory
      .createArrayBacked(RenderElytraCallback.class, (listeners) -> (playerEntity, info) -> {

        for (RenderElytraCallback listener : listeners) {
          listener.process(playerEntity, info);
        }
      });

  void process(PlayerEntity playerEntity, RenderElytraInfo info);
}
