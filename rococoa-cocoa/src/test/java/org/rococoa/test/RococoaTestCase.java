/*
 * Copyright 2007, 2008 Duncan McGregor
 *
 * This file is part of Rococoa, a library to allow Java to talk to Cocoa.
 *
 * Rococoa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Rococoa is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Rococoa.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.rococoa.test;

import com.sun.jna.Native;
import org.junit.After;
import org.junit.Before;
import org.rococoa.ID;
import org.rococoa.cocoa.foundation.NSAutoreleasePool;
import org.rococoa.cocoa.foundation.NSObject;
import org.rococoa.internal.RococoaLibrary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;

/**
 * A TestCase which runs tests with an autorelease pool in place.
 *
 * @author duncan
 */
public abstract class RococoaTestCase {
    static {
        String projectPath = System.getProperty("user.dir");
        String dylibPath = projectPath + "/../librococoa/build/Release";
        System.setProperty("jna.library.path", dylibPath);
        Native.load("rococoa-test", RococoaLibrary.class);
    }

    // stress our memory management
    private static final int NUMBER_OF_POOL_DRAININGS = Integer.getInteger("org.rococoa.test.pool.drainings", 1);
    private static final Logger LOGGER = Logger.getLogger(RococoaTestCase.class.getName());

    private NSAutoreleasePool pool;

    static {
        try {
            InputStream inputStream = RococoaTestCase.class.getResourceAsStream("/logging.properties");
            if (inputStream == null) {
                throw new FileNotFoundException("logging.properties");
            }
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (IOException e) {
            System.err.println("Failed to load logging.properties");
        }
    }

    @Before
    public final void setUpPool() {
        pool = NSAutoreleasePool.new_();
        assertNotNull(pool);
    }

    @After
    public final void tearDownPool() {
        for (int i = 0; i < NUMBER_OF_POOL_DRAININGS; i++) {
            pool.drain();
        }
    }

    protected void cyclePool() {
        tearDownPool();
        setUpPool();
    }

    protected void waitForKeystroke() {
        try {
            System.out.println("Press Enter to continue...");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected ID id(Object o) {
        return ((NSObject) o).id();
    }
}