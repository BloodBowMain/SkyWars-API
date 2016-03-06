/*
 * Copyright (C) 2016 Dabo Ross <http://www.daboross.net/>
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
package net.daboross.bukkitdev.skywars.api.kits;

import org.bukkit.inventory.ItemStack;

public abstract class SkyItemMeta implements Comparable<SkyItemMeta> {

    public abstract void applyToItem(ItemStack item);

    public abstract SkyItemMetaType getType();

    @Override
    public int compareTo(SkyItemMeta other) {
        return getType().compareTo(other.getType());
    }
}
