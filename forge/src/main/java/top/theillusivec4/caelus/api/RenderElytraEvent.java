package top.theillusivec4.caelus.api;

import java.awt.Color;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * This event is fired when the game attempts to render the elytra model while the player is not
 * wearing a vanilla elytra. This can be used to enable rendering, enable enchantment glow, and/or
 * use a custom texture.
 * <br>
 * This event is fired client-side only.
 * <br>
 * This event is not {@link net.minecraftforge.eventbus.api.Cancelable}. <br>
 * <br>
 * This event does not have a result. {@link HasResult} <br>
 * <br>
 * This event is fired on the {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS}.
 **/
public class RenderElytraEvent extends PlayerEvent {

  private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation(
      "textures/entity/elytra.png");

  private boolean render = false;
  private boolean enchanted = false;
  private ResourceLocation resourceLocation = TEXTURE_ELYTRA;
  private Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);

  public RenderElytraEvent(Player playerEntity) {
    super(playerEntity);
  }

  /**
   * If true, will render the elytra model given the entity is not already wearing an elytra in the
   * chest slot.
   * <p>
   * Note that this result will also affect cape rendering as rendering elytras will disable
   * rendering capes; however, elytras will still render with cape textures when appropriate.
   */
  public boolean canRender() {
    return this.render;
  }

  public void setRender(boolean render) {
    this.render = render;
  }

  /**
   * If true and {@link RenderElytraEvent#canRender()} is true, the elytra model will render with
   * the enchantment glow.
   */
  public boolean isEnchanted() {
    return this.enchanted;
  }

  public void setEnchanted(boolean enchanted) {
    this.enchanted = enchanted;
  }

  /**
   * The texture to render on the elytra model if {@link RenderElytraEvent#canRender()} is true.
   */
  public ResourceLocation getResourceLocation() {
    return this.resourceLocation;
  }

  public void setResourceLocation(ResourceLocation resourceLocation) {
    this.resourceLocation = resourceLocation;
  }

  /**
   * The color to render for the elytra model texture
   */
  public Color getColor() {
    return this.color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
