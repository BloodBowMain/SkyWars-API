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
package net.daboross.bukkitdev.skywars.api.arenaconfig;

import java.util.HashMap;
import java.util.Map;
import net.daboross.bukkitdev.skywars.api.Parentable;
import net.daboross.bukkitdev.skywars.api.config.ConfigColorCode;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

/**
 *
 * @author daboross
 */
@SerializableAs("SkyMessagesConfig")
public class SkyMessagesConfig extends Parentable<SkyMessagesConfig> implements ConfigurationSerializable {

    private final Map<String, String> rawMessages = new HashMap<String, String>();
    private final Map<String, String> messages = new HashMap<String, String>();
    private String prefix;

    public SkyMessagesConfig() {
    }

    public SkyMessagesConfig(SkyMessagesConfig parent) {
        super(parent);
    }

    public void copyDataFrom(SkyMessagesConfig config) {
        this.rawMessages.putAll(config.rawMessages);
    }

    public boolean definesAnything() {
        return !rawMessages.isEmpty();
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMessage(String messageKey) {
        if (prefix == null) {
            throw new IllegalStateException("Prefix not set");
        }
        String message = messages.get(messageKey.toLowerCase());
        if (message == null) {
            if (parent == null) {
                throw new IllegalArgumentException("Ultimate parent does not define message " + messageKey);
            } else {
                return parent.getMessage(messageKey);
            }
        } else {
            return prefix + message;
        }
    }

    public String getRawMessage(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Null key");
        }
        String message = rawMessages.get(key.toLowerCase());
        if (message == null) {
            if (parent == null) {
                throw new IllegalArgumentException("Ultimate parent does not define message " + key);
            } else {
                return parent.getRawMessage(key);
            }
        } else {
            return message;
        }
    }

    public void setRawMessage(String key, String message) {
        if (key == null) {
            throw new IllegalArgumentException("Null key");
        }
        key = key.toLowerCase();
        if (message == null) {
            rawMessages.remove(key);
            messages.remove(key);
        } else {
            rawMessages.put(key, message);
            messages.put(key, ChatColor.translateAlternateColorCodes('&', ConfigColorCode.translateCodes(message)));
        }
    }

    @Override
    public Map<String, Object> serialize() {
        return new HashMap<String, Object>(rawMessages);
    }

    public void serialize(ConfigurationSection section) {
        for (Map.Entry<String, String> entry : rawMessages.entrySet()) {
            section.set(entry.getKey(), entry.getValue());
        }
    }

    public static SkyMessagesConfig deserialize(Map<String, Object> map) {
        SkyMessagesConfig returnValue = new SkyMessagesConfig();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object o = entry.getValue();
            if (o != null) {
                returnValue.setRawMessage(entry.getKey(), o instanceof String ? (String) o : o.toString());
            }
        }
        return returnValue;
    }

    public static SkyMessagesConfig deserialize(ConfigurationSection configurationSection) {
        SkyMessagesConfig returnValue = new SkyMessagesConfig();
        for (String key : configurationSection.getKeys(true)) {
            String value = configurationSection.getString(key);
            if (value != null) {
                returnValue.setRawMessage(key, value);
            }
        }
        return returnValue;
    }

    @Override
    public String toString() {
        return "ArenaMessages{parent=" + parent + ",messages=" + rawMessages + "}";
    }
}
