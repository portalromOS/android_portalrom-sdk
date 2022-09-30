/*
 * Copyright (C) 2015 The CyanogenMod Project
 * Copyright (C) 2018 The PortalRomOS Project
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

package portalrom.preference;

import android.content.Context;
import android.util.AttributeSet;

import portalrom.providers.PortalRomSettings;

public class PortalRomSystemSettingSwitchPreference extends SelfRemovingSwitchPreference {

    public PortalRomSystemSettingSwitchPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PortalRomSystemSettingSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PortalRomSystemSettingSwitchPreference(Context context) {
        super(context);
    }

    @Override
    protected boolean isPersisted() {
        return PortalRomSettings.System.getString(getContext().getContentResolver(), getKey()) != null;
    }

    @Override
    protected void putBoolean(String key, boolean value) {
        PortalRomSettings.System.putInt(getContext().getContentResolver(), key, value ? 1 : 0);
    }

    @Override
    protected boolean getBoolean(String key, boolean defaultValue) {
        return PortalRomSettings.System.getInt(getContext().getContentResolver(),
                key, defaultValue ? 1 : 0) != 0;
    }
}
