/*
 * Copyright (C) 2013-2014 Dabo Ross <http://www.daboross.net/>
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

import java.util.Random;
import net.daboross.bukkitdev.skywars.api.location.SkyBlockLocationRangeTest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// TODO: Test generation of clearing/boundaries
public class SkyBoundariesConfigTest {

    private Random r;

    @Before
    public void setupRandom() {
        r = new Random();
    }

    @Test
    public void testEquals() {
        SkyBoundariesConfig original = getRandom(r);
        SkyBoundariesConfig second = new SkyBoundariesConfig(original.getOrigin());
        Assert.assertEquals(original, second);
    }

    @Test
    public void testSerializeDeserialize() {
        SkyBoundariesConfig original = getRandom(r);
        YamlConfiguration serializationMedium = new YamlConfiguration();
        original.serialize(serializationMedium);
        SkyBoundariesConfig deserialized = SkyBoundariesConfig.deserialize(serializationMedium);
        Assert.assertEquals(original, deserialized);
    }

    public static SkyBoundariesConfig getRandom(Random r) {
        return new SkyBoundariesConfig(
                r.nextBoolean() ? null : SkyBlockLocationRangeTest.getRandom(r));
    }
}
