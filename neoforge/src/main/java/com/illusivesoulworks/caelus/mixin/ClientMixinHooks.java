package com.illusivesoulworks.caelus.mixin;

import com.illusivesoulworks.caelus.api.RenderCapeEvent;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.neoforged.neoforge.common.NeoForge;

public class ClientMixinHooks {

  public static boolean canRenderCape(PlayerRenderState playerRenderState) {
    return !NeoForge.EVENT_BUS.post(new RenderCapeEvent(playerRenderState)).isCanceled();
  }
}
