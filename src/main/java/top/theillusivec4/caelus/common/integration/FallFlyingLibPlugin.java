package top.theillusivec4.caelus.common.integration;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.AbilityTracker;
import io.github.ladysnake.pal.Pal;
import net.adriantodt.fallflyinglib.FallFlyingLib;
import net.adriantodt.fallflyinglib.event.PreFallFlyingCallback;
import net.adriantodt.fallflyinglib.event.StopFallFlyingCallback;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import top.theillusivec4.caelus.api.CaelusApi;

public class FallFlyingLibPlugin {

  private static final CaelusApi API = CaelusApi.getInstance();
  private static final AbilitySource ELYTRA_FLIGHT =
      Pal.getAbilitySource(API.getModId(), "elytra_flight");

  public static void init() {
    PreFallFlyingCallback.EVENT.register((player) -> {

      if (!player.world.isClient) {
        AbilityTracker tracker = FallFlyingLib.ABILITY.getTracker(player);

        if (API.canFly(player)) {
          tracker.addSource(ELYTRA_FLIGHT);
        } else {
          tracker.removeSource(ELYTRA_FLIGHT);
        }
      }
    });
    StopFallFlyingCallback.EVENT.register((player, reason, cancel) -> {
      EntityAttributeInstance attribute = player.getAttributeInstance(API.getFlightAttribute());

      if (attribute != null) {

        for (EntityAttributeModifier modifier : attribute.getModifiers()) {

          if (modifier.getOperation() != EntityAttributeModifier.Operation.ADDITION &&
              modifier.getValue() == 0.0d) {
            cancel.run();
            return;
          }
        }
      }
    });
  }
}
