/*
 * Copyright (C) 2019-2023 C4
 *
 * Caelus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Caelus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * and the GNU Lesser General Public License along with Caelus.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package com.illusivesoulworks.caelus.platform.services;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import com.illusivesoulworks.caelus.common.registry.RegistryProvider;

public interface IRegistryFactory {

  <T> RegistryProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId);

  default <T> RegistryProvider<T> create(Registry<T> registry, String modId) {
    return create(registry.key(), modId);
  }
}
