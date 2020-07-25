package top.theillusivec4.caelus.core.client;

import java.awt.Color;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import top.theillusivec4.caelus.api.RenderElytraInfo;

public final class RenderElytraInfoImpl implements RenderElytraInfo {

  private final PlayerEntity playerEntity;

  private boolean render = false;
  private boolean glowing = false;
  private Identifier texture = null;
  private Color color = new Color(255, 255, 255);

  public RenderElytraInfoImpl(PlayerEntity playerEntity) {
    this.playerEntity = playerEntity;
  }

  @Override
  public PlayerEntity getPlayer() {
    return this.playerEntity;
  }

  @Override
  public boolean canRender() {
    return this.render;
  }

  @Override
  public void activateRender() {
    this.render = true;
  }

  @Override
  public boolean isGlowing() {
    return this.glowing;
  }

  @Override
  public void activateGlow() {
    this.glowing = true;
  }

  @Override
  public Identifier getTextureOverride() {
    return this.texture;
  }

  @Override
  public void setTextureOverride(Identifier identifier) {
    this.texture = identifier;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }
}
