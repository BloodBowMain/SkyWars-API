/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bukkitdev.skywars.api;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

public class SkyStatic {

    @Setter
    @Getter
    private static boolean debug = false;
    @Setter
    @Getter
    @SuppressWarnings("NonConstantLogger")
    private static Logger logger = Bukkit.getLogger();
    @Setter
    @Getter
    private static String pluginName = "SkyWars";
    @Setter
    @Getter
    private static String version = "Unknown";
    @Getter
    private static final String implementationVersion = SkyStatic.class.getPackage().getImplementationVersion();

    public static void debug(String message) {
        if (debug) {
            if (logger.equals(Bukkit.getLogger())) {
                logger.log(Level.INFO, "[SkyWars] {0}", message);
            } else {
                logger.log(Level.INFO, message);
            }
        }
    }
}
