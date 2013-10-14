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
package net.daboross.bukkitdev.skywars.api.arenaconfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import net.daboross.bukkitdev.skywars.api.parent.Parentable;
import net.daboross.bukkitdev.skywars.api.config.ConfigColorCode;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

/**
 *
 * @author Dabo Ross <http://www.daboross.net/>
 */
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true, callSuper = false)
@SerializableAs("SkyMessagesConfig")
public class SkyMessagesConfig extends Parentable<SkyMessagesConfig> implements ConfigurationSerializable, SkyMessages {

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
        this.messages.putAll(config.messages);
    }

    @Override
    public boolean definesAnything() {
        return !rawMessages.isEmpty();
    }

    public void setPrefix(String prefix) {
        this.prefix = translate(prefix);
    }

    @Override
    public String getMessage(String key) {
        if (prefix == null) {
            throw new IllegalStateException("Prefix not set");
        }
        key = key.toLowerCase(Locale.ENGLISH);
        String message = messages.get(key);
        if (message == null) {
            if (parent == null) {
                throw new IllegalArgumentException("Originally asked does not define message '" + key + "' and has no parent. Originally asked: " + this.toIndentedString(2));
            } else {
                return parent.getMessage(key, this);
            }
        } else {
            return prefix + message;
        }
    }

    private String getMessage(String key, SkyMessagesConfig original) {
        String message = messages.get(key);
        if (message == null) {
            if (parent == null) {
                throw new IllegalArgumentException("Ultimate parent does not define message '" + key + "'; original=" + original.toIndentedString(2));
            } else {
                return parent.getMessage(key, original);
            }
        } else {
            return prefix + message;
        }
    }

    @Override
    public String getRawMessage(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Null key");
        }
        String message = rawMessages.get(key.toLowerCase(Locale.ENGLISH));
        if (message == null) {
            if (parent == null) {
                throw new IllegalArgumentException("Original does not define raw message '" + key + "'; original=" + this.toIndentedString(2));
            } else {
                return parent.getRawMessage(key, this);
            }
        } else {
            return message;
        }
    }

    private String getRawMessage(String key, SkyMessagesConfig original) {
        String message = rawMessages.get(key);
        if (message == null) {
            if (parent == null) {
                throw new IllegalArgumentException("Ultimate parent does not define raw message " + key + "; original=" + original.toIndentedString(2));
            } else {
                return parent.getMessage(key, original);
            }
        } else {
            return message;
        }
    }

    @Override
    public void setRawMessage(String key, String message) {
        if (key == null) {
            throw new IllegalArgumentException("Null key");
        }
        key = key.toLowerCase(Locale.ENGLISH);
        if (message == null) {
            rawMessages.remove(key);
            messages.remove(key);
        } else {
            rawMessages.put(key, message);
            messages.put(key, translate(message));
        }
    }

    public Map<String, String> getInternalRawMessages() {
        return Collections.unmodifiableMap(rawMessages);
    }

    private String translate(String original) {
        return ChatColor.translateAlternateColorCodes('&', ConfigColorCode.translateCodes(original));
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
            if (o instanceof String) {
                returnValue.setRawMessage(entry.getKey(), (String) o);
            }
        }
        return returnValue;
    }

    public static SkyMessagesConfig deserialize(ConfigurationSection configurationSection) {
        SkyMessagesConfig returnValue = new SkyMessagesConfig();
        for (String key : configurationSection.getKeys(true)) {
            if (configurationSection.isString(key)) {
                returnValue.setRawMessage(key, configurationSection.getString(key));
            }
        }
        return returnValue;
    }

    public String toIndentedString(int indentAmount) {
        return "SkyMessagesConfig{\n"
                + (parent == null ? "" : getIndent(indentAmount + 1) + "parent=" + parent.toIndentedString(indentAmount + 1) + ",\n")
                + (prefix == null ? "" : getIndent(indentAmount + 1) + "prefix=" + prefix + ",\n")
                + getIndent(indentAmount + 1) + "rawMessages=" + rawMessages + "\n"
                + getIndent(indentAmount + 1) + "messages=" + messages + "\n"
                + getIndent(indentAmount) + "}";
    }

    private String getIndent(int indentAmount) {
        return StringUtils.repeat("\t", indentAmount);
    }
}
