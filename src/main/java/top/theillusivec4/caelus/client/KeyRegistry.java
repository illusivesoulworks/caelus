/*
 * Copyright (C) 2019  C4
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

package top.theillusivec4.caelus.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.caelus.Caelus;

public class KeyRegistry {

  private static final String TOGGLE_DESC = "key." + Caelus.MODID + ".toggle.desc";
  private static final String TRIGGER_DESC = "key." + Caelus.MODID + ".trigger.desc";
  private static final String CONFIG_CATEGORY = "key." + Caelus.MODID + ".category";

  public static KeyBinding toggleFlight;
  public static KeyBinding triggerFlight;

  public static void register() {
    triggerFlight = new KeyBinding(TRIGGER_DESC, InputMappings.INPUT_INVALID.getKeyCode(), CONFIG_CATEGORY);
    toggleFlight = new KeyBinding(TOGGLE_DESC, GLFW.GLFW_KEY_V, CONFIG_CATEGORY);
    ClientRegistry.registerKeyBinding(toggleFlight);
    ClientRegistry.registerKeyBinding(triggerFlight);
  }
}
