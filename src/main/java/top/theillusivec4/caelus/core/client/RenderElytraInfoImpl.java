/*
 * Copyright (c) 2019-2020 C4
 *
 * This file is part of Caelus, a mod made for Minecraft.
 *
 * Caelus is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Caelus is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Caelus.  If not, see <https://www.gnu.org/licenses/>.
 */

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
