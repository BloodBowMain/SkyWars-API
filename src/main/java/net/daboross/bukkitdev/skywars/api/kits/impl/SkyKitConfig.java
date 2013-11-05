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
package net.daboross.bukkitdev.skywars.api.kits.impl;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import net.daboross.bukkitdev.skywars.api.kits.SkyKit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SkyKitConfig implements SkyKit {

    private final List<SkyKitItemConfig> inventoryContents;
    private final List<SkyKitItemConfig> armorContents;
    @Getter
    private final int cost;
    @Getter
    private final String permission;

    private SkyKitConfig(List<SkyKitItemConfig> inventoryContents, List<SkyKitItemConfig> armorContents, int cost, String permissionNode) {
        this.inventoryContents = inventoryContents;
        if (armorContents.size() != 4) {
            throw new IllegalArgumentException("Armor contents size not 4");
        }
        this.armorContents = armorContents;
        this.cost = cost;
        this.permission = permissionNode;
    }

    @Override
    public void applyTo(Player p) {
        PlayerInventory i = p.getInventory();
        i.setArmorContents(armorContents.toArray(new ItemStack[4]));
    }

    @Override
    public List<SkyKitItemConfig> getArmorContents() {
        return Collections.unmodifiableList(armorContents);
    }

    @Override
    public List<SkyKitItemConfig> getInventoryContents() {
        return Collections.unmodifiableList(inventoryContents);
    }
}
