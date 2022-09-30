/**
 * Copyright (c) 2022, The Portal Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.portalrom.portalromsettings.tests;

import android.content.ContentResolver;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import portalrom.providers.PortalRomSettings;

public class PortalRomSettingsSecureTests extends AndroidTestCase {
    private ContentResolver mContentResolver;

    private static final String UNREALISTIC_SETTING = "_______UNREAL_______";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContentResolver = mContext.getContentResolver();
    }

    @SmallTest
    public void testFloat() {
        final float expectedFloatValue = 1.0f;
        PortalRomSettings.Secure.putFloat(mContentResolver,
                PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER, expectedFloatValue);

        try {
            float actualValue = PortalRomSettings.Secure.getFloat(mContentResolver,
                    PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER);
            assertEquals(expectedFloatValue, actualValue);
        } catch (PortalRomSettings.PortalRomSettingNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    @SmallTest
    public void testFloatWithDefault() {
        final float expectedDefaultFloatValue = 1.5f;
        float actualValue = PortalRomSettings.Secure.getFloat(mContentResolver,
                    UNREALISTIC_SETTING, expectedDefaultFloatValue);
        assertEquals(expectedDefaultFloatValue, actualValue);
    }

    @SmallTest
    public void testInt() {
        final int expectedIntValue = 2;
        PortalRomSettings.Secure.putInt(mContentResolver,
                PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER, expectedIntValue);

        try {
            int actualValue = PortalRomSettings.Secure.getInt(mContentResolver,
                    PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER);
            assertEquals(expectedIntValue, actualValue);
        } catch (PortalRomSettings.PortalRomSettingNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    @SmallTest
    public void testIntWithDefault() {
        final int expectedDefaultIntValue = 11;
        int actualValue = PortalRomSettings.Secure.getInt(mContentResolver,
                UNREALISTIC_SETTING, expectedDefaultIntValue);
        assertEquals(expectedDefaultIntValue, actualValue);
    }

    @SmallTest
    public void testLong() {
        final long expectedLongValue = 3l;
        PortalRomSettings.Secure.putLong(mContentResolver,
                PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER, expectedLongValue);

        try {
            long actualValue = PortalRomSettings.Secure.getLong(mContentResolver,
                    PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER);
            assertEquals(expectedLongValue, actualValue);
        } catch (PortalRomSettings.PortalRomSettingNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    @SmallTest
    public void testLongWithDefault() {
        final long expectedDefaultLongValue = 17l;
        long actualValue = PortalRomSettings.Secure.getLong(mContentResolver,
                UNREALISTIC_SETTING, expectedDefaultLongValue);
        assertEquals(expectedDefaultLongValue, actualValue);
    }

    @SmallTest
    public void testString() {
        final String expectedStringValue = "4";
        PortalRomSettings.Secure.putString(mContentResolver,
                PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER, expectedStringValue);

        String actualValue = PortalRomSettings.Secure.getString(mContentResolver,
                PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER);
        assertEquals(expectedStringValue, actualValue);
    }

    @SmallTest
    public void testGetUri() {
        final Uri expectedUri = Uri.withAppendedPath(PortalRomSettings.Secure.CONTENT_URI,
                PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER);

        final Uri actualUri = PortalRomSettings.Secure.getUriFor(
                PortalRomSettings.Secure.__MAGICAL_TEST_PASSING_ENABLER);

        assertEquals(expectedUri, actualUri);
    }
}
