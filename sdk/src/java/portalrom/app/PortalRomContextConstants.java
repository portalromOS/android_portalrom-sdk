/**
 * Copyright (C) 2015, The CyanogenMod Project
 *               2017-2022 The PortalRomOS Project
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

package portalrom.app;

import android.annotation.SdkConstant;

/**
 * @hide
 * TODO: We need to somehow make these managers accessible via getSystemService
 */
public final class PortalRomContextConstants {

    /**
     * @hide
     */
    private PortalRomContextConstants() {
        // Empty constructor
    }

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link portalrom.app.ProfileManager} for informing the user of
     * background events.
     *
     * @see android.content.Context#getSystemService
     * @see portalrom.app.ProfileManager
     *
     * @hide
     */
    public static final String PORTALROM_PROFILE_SERVICE = "profile";

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link portalrom.hardware.PortalRomHardwareManager} to manage the extended
     * hardware features of the device.
     *
     * @see android.content.Context#getSystemService
     * @see portalrom.hardware.PortalRomHardwareManager
     *
     * @hide
     */
    public static final String PORTALROM_HARDWARE_SERVICE = "portalromhardware";

    /**
     * Control device power profile and characteristics.
     *
     * @hide
     */
    public static final String PORTALROM_PERFORMANCE_SERVICE = "portalromperformance";

    /**
     * Manages display color adjustments
     *
     * @hide
     */
    public static final String PORTALROM_LIVEDISPLAY_SERVICE = "portalromlivedisplay";

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link portalrom.trust.TrustInterface} to access the Trust interface.
     *
     * @see android.content.Context#getSystemService
     * @see portalrom.trust.TrustInterface
     *
     * @hide
     */
    public static final String PORTALROM_TRUST_INTERFACE = "portalromtrust";

    /**
     * Update power menu (GlobalActions)
     *
     * @hide
     */
    public static final String PORTALROM_GLOBAL_ACTIONS_SERVICE = "portalromglobalactions";

    /**
     * Features supported by the PortalRom SDK.
     */
    public static class Features {
        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the hardware abstraction
         * framework service utilized by the portalrom sdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String HARDWARE_ABSTRACTION = "org.portalrom.hardware";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the portalrom profiles service
         * utilized by the portalrom sdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String PROFILES = "org.portalrom.profiles";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the portalrom performance service
         * utilized by the portalrom sdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String PERFORMANCE = "org.portalrom.performance";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the LiveDisplay service
         * utilized by the portalrom sdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String LIVEDISPLAY = "org.portalrom.livedisplay";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the PortalRom audio extensions
         * utilized by the portalrom sdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String AUDIO = "org.portalrom.audio";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the portalrom trust service
         * utilized by the portalrom sdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String TRUST = "org.portalrom.trust";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the portalrom settings service
         * utilized by the portalrom sdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String SETTINGS = "org.portalrom.settings";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the portalrom globalactions
         * service utilized by the portalrom sdk and PortalRomParts.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String GLOBAL_ACTIONS = "org.portalrom.globalactions";
    }
}
