/*
 * Copyright (C) 2017 The PortalRomOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.portalrom.internal.preference.deviceinfo;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;

import androidx.preference.Preference;

import portalrom.preference.SelfRemovingPreference;

import org.portalrom.platform.internal.R;

public class PortalRomUpdatesPreference extends SelfRemovingPreference
        implements Preference.OnPreferenceClickListener {

    private static final String TAG = "PortalRomUpdatesPreference";

    private static final String UPDATER_PACKAGE_NAME = "org.portalrom.updater";
    private static final String UPDATER_ACTIVITY_CLASS =
            UPDATER_PACKAGE_NAME + ".UpdatesActivity";

    public PortalRomUpdatesPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PortalRomUpdatesPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PortalRomUpdatesPreference(Context context) {
        super(context);
    }

    @Override
    public void onAttached() {
        super.onAttached();

        setOnPreferenceClickListener(this);
        setTitle(R.string.portalrom_updates);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        final Intent intent = new Intent(Intent.ACTION_MAIN)
                .setClassName(UPDATER_PACKAGE_NAME, UPDATER_ACTIVITY_CLASS);
        try {
            getContext().startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Unable to start activity " + intent.toString());
        }
        return true; // handled
    }
}
