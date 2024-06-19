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

package com.illusivesoulworks.caelus.platform;

import java.util.ServiceLoader;
import com.illusivesoulworks.caelus.CaelusConstants;
import com.illusivesoulworks.caelus.platform.services.ICaelusPlatform;
import com.illusivesoulworks.caelus.platform.services.IRegistryFactory;

public class Services {

  public static final IRegistryFactory REGISTRY_FACTORY = load(IRegistryFactory.class);
  public static final ICaelusPlatform CAELUS = load(ICaelusPlatform.class);

  public static <T> T load(Class<T> clazz) {

    final T loadedService = ServiceLoader.load(clazz)
        .findFirst()
        .orElseThrow(
            () -> new NullPointerException("Failed to load service for " + clazz.getName()));
    CaelusConstants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
    return loadedService;
  }
}
