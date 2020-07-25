package top.theillusivec4.caelus.api;

import java.awt.Color;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public interface RenderElytraInfo {

  PlayerEntity getPlayer();

  /**
   * Returns true if the elytra can render
   */
  boolean canRender();

  void activateRender();

  /**
   * Returns true if the elytra has an enchantment glow
   */
  boolean isGlowing();

  void activateGlow();

  /**
   * Returns the location of the elytra model's texture
   */
  Identifier getTextureOverride();

  void setTextureOverride(Identifier identifier);

  /**
   * Returns the color to use for the elytra render
   */
  Color getColor();

  void setColor(Color color);
}
