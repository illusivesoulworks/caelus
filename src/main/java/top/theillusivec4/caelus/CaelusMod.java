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

package top.theillusivec4.caelus;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;
import top.theillusivec4.caelus.api.CaelusApi;
import top.theillusivec4.caelus.common.CaelusNetwork;

@SuppressWarnings("unused")
public class CaelusMod implements ModInitializer {

  @Override
  public void onInitialize() {
    Registry.register(Registry.ATTRIBUTE, "caelus.elytra_flight", CaelusApi.getInstance()
        .getFlightAttribute());
    CaelusNetwork.setup();
  }
}
