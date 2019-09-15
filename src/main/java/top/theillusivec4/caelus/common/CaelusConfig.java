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

package top.theillusivec4.caelus.common;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import top.theillusivec4.caelus.Caelus;

public class CaelusConfig {

  public static final ForgeConfigSpec clientSpec;
  public static final Client CLIENT;
  private static final String CONFIG_PREFIX = "gui." + Caelus.MODID + ".config.";

  static {
    final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
        .configure(Client::new);
    clientSpec = specPair.getRight();
    CLIENT = specPair.getLeft();
  }

  public static class Client {

    public final ForgeConfigSpec.BooleanValue toggleIcon;

    Client(ForgeConfigSpec.Builder builder) {

      builder.comment("Client only settings, mostly things related to rendering").push("client");

      toggleIcon = builder.comment("Set to true to enable an icon that appears on the HUD when " +
          "elytra flight is disabled")
          .translation(CONFIG_PREFIX + "toggleIcon")
          .define("toggleIcon", true);

      builder.pop();
    }
  }
}
