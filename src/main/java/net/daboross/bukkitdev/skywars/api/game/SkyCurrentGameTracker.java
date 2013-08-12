/*
 * Copyright (C) 2013 daboross
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
package net.daboross.bukkitdev.skywars.api.game;

/**
 *
 * @author daboross
 */
public interface SkyCurrentGameTracker {

    /**
     * Gets if a player is currently in a game.
     *
     * @param playerName the name of the player to check.
     * @return whether or not the given player is in a game.
     */
    public boolean isInGame(String playerName);

    /**
     * Gets the ID of the game a given player is in.
     *
     * @param playerName the name of the player to check.
     * @return the ID of the game the given player is in, or -1 if none.
     */
    public int getGameID(String playerName);
}
