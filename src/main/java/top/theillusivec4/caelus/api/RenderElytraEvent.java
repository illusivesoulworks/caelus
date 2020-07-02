package top.theillusivec4.caelus.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
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

  public RenderElytraEvent(PlayerEntity playerEntity) {
    super(playerEntity);
  }

  public boolean canRender() {
    return this.render;
  }

  public void setRender(boolean render) {
    this.render = render;
  }

  public boolean isEnchanted() {
    return this.enchanted;
  }

  public void setEnchanted(boolean enchanted) {
    this.enchanted = enchanted;
  }

  public ResourceLocation getResourceLocation() {
    return this.resourceLocation;
  }

  public void setResourceLocation(ResourceLocation resourceLocation) {
    this.resourceLocation = resourceLocation;
  }
}
