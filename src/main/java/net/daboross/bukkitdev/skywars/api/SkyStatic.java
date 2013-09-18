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

import java.util.Locale;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

/**
 *
 * @author Dabo Ross <http://www.daboross.net/>
 */
public class SkyStatic {

    private static boolean debug = false;
    private static Logger logger = Bukkit.getLogger();
    private static String pluginName = "SkyWars";
    private static String version = "Unset";
    private static Locale locale = Locale.ENGLISH;

    public static void setDebug( boolean debug ) {
        SkyStatic.debug = debug;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setLogger( Logger logger ) {
        SkyStatic.logger = logger;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setPluginName( String pluginName ) {
        SkyStatic.pluginName = pluginName;
    }

    public static String getPluginName() {
        return pluginName;
    }

    public static void setVersion( String version ) {
        SkyStatic.version = version;
    }

    public static String getVersion() {
        return version;
    }

    public static Locale getLocale() {
        return locale;
    }
}
