/*
 * Copyright (C) 2014 Dabo Ross <http://www.daboross.net/>
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
package net.daboross.bukkitdev.skywars.api.ingame;

import net.daboross.bukkitdev.skywars.api.kits.SkyKit;
import org.bukkit.entity.Player;

public interface SkyPlayer {

    public String getName();

    public Player getPlayer();

    public int getGameId();

    public SkyPlayerState getState();

    public SkyKit getSelectedKit();

    public void setSelectedKit(SkyKit kit);
}