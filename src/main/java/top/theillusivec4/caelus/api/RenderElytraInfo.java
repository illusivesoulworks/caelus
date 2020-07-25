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
