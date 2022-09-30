#
# Copyright (C) 2022 The Portal Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

# We have a special case here where we build the library's resources
# independently from its code, so we need to find where the resource
# class source got placed in the course of building the resources.
# Thus, the magic here.
# Also, this module cannot depend directly on the R.java file; if it
# did, the PRIVATE_* vars for R.java wouldn't be guaranteed to be correct.
# Instead, it depends on the R.stamp file, which lists the corresponding
# R.java file as a prerequisite.
portalrom_platform_res := APPS/org.portalrom.platform-res_intermediates/aapt

# List of packages used in portalrom-api-stubs
portalrom_stub_packages := portalrom.app:portalrom.content:portalrom.hardware:portalrom.media:portalrom.os:portalrom.preference:portalrom.profiles:portalrom.providers:portalrom.platform:portalrom.util:portalrom.trust

portalrom_framework_module := $(LOCAL_INSTALLED_MODULE)

# Make sure that R.java and Manifest.java are built before we build
# the source for this library.
portalrom_framework_res_R_stamp := \
    $(call intermediates-dir-for,APPS,org.portalrom.platform-res,,COMMON)/src/R.stamp
LOCAL_ADDITIONAL_DEPENDENCIES := $(portalrom_framework_res_R_stamp)

$(portalrom_framework_module): | $(dir $(portalrom_framework_module))org.portalrom.platform-res.apk

portalrom_framework_built := $(call java-lib-deps, org.portalrom.platform)

# the sdk as an aar for publish, not built as part of full target
# DO NOT LINK AGAINST THIS IN BUILD
# ============================================================
include $(CLEAR_VARS)

LOCAL_MODULE := org.portalrom.platform.sdk.aar

LOCAL_JACK_ENABLED := disabled

LOCAL_CONSUMER_PROGUARD_FILE := $(LOCAL_PATH)/sdk/proguard.txt

LOCAL_RESOURCE_DIR := $(addprefix $(LOCAL_PATH)/, sdk/res/res)
LOCAL_MANIFEST_FILE := sdk/AndroidManifest.xml

portalrom_sdk_exclude_files := 'portalrom/library'
LOCAL_JAR_EXCLUDE_PACKAGES := $(portalrom_sdk_exclude_files)
LOCAL_JAR_EXCLUDE_FILES := none

LOCAL_STATIC_JAVA_LIBRARIES := org.portalrom.platform.sdk

include $(BUILD_STATIC_JAVA_LIBRARY)
$(LOCAL_MODULE) : $(built_aar)

# ===========================================================
# Common Droiddoc vars
portalrom_platform_docs_src_files := \
    $(call all-java-files-under, $(portalrom_sdk_src)) \
    $(call all-html-files-under, $(portalrom_sdk_src))

portalrom_platform_docs_java_libraries := \
    org.portalrom.platform.sdk

# SDK version as defined
portalrom_platform_docs_SDK_VERSION := 15.1

# release version
portalrom_platform_docs_SDK_REL_ID := 9

portalrom_platform_docs_LOCAL_MODULE_CLASS := JAVA_LIBRARIES

portalrom_platform_docs_LOCAL_DROIDDOC_SOURCE_PATH := \
    $(portalrom_platform_docs_src_files)

portalrom_platform_docs_LOCAL_ADDITIONAL_JAVA_DIR := \
    $(call intermediates-dir-for,JAVA_LIBRARIES,org.portalrom.platform.sdk,,COMMON)

# ====  the api stubs and current.xml ===========================
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
    $(portalrom_platform_docs_src_files)
LOCAL_INTERMEDIATE_SOURCES:= $(portalrom_platform_LOCAL_INTERMEDIATE_SOURCES)
LOCAL_JAVA_LIBRARIES:= $(portalrom_platform_docs_java_libraries)
LOCAL_MODULE_CLASS:= $(portalrom_platform_docs_LOCAL_MODULE_CLASS)
LOCAL_DROIDDOC_SOURCE_PATH:= $(portalrom_platform_docs_LOCAL_DROIDDOC_SOURCE_PATH)
LOCAL_ADDITIONAL_JAVA_DIR:= $(portalrom_platform_docs_LOCAL_ADDITIONAL_JAVA_DIR)
LOCAL_ADDITIONAL_DEPENDENCIES:= $(portalrom_platform_docs_LOCAL_ADDITIONAL_DEPENDENCIES)

LOCAL_MODULE := portalrom-api-stubs

LOCAL_DROIDDOC_CUSTOM_TEMPLATE_DIR:= external/doclava/res/assets/templates-sdk

LOCAL_DROIDDOC_STUB_OUT_DIR := $(TARGET_OUT_COMMON_INTERMEDIATES)/JAVA_LIBRARIES/portalrom-sdk_stubs_current_intermediates/src

LOCAL_DROIDDOC_OPTIONS:= \
        -referenceonly \
        -stubpackages $(portalrom_stub_packages) \
        -exclude org.portalrom.platform.internal \
        -api $(INTERNAL_PORTALROM_PLATFORM_API_FILE) \
        -removedApi $(INTERNAL_PORTALROM_PLATFORM_REMOVED_API_FILE) \
        -nodocs

LOCAL_UNINSTALLABLE_MODULE := true

#include $(BUILD_DROIDDOC)

# $(gen), i.e. framework.aidl, is also needed while building against the current stub.
$(full_target): $(portalrom_framework_built) $(gen)
$(INTERNAL_portalrom_PLATFORM_API_FILE): $(full_target)
$(call dist-for-goals,sdk,$(INTERNAL_portalrom_PLATFORM_API_FILE))


# Documentation
# ===========================================================
include $(CLEAR_VARS)

LOCAL_MODULE := org.portalrom.platform.sdk
LOCAL_INTERMEDIATE_SOURCES:= $(portalrom_platform_LOCAL_INTERMEDIATE_SOURCES)
LOCAL_MODULE_CLASS := JAVA_LIBRARIES
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(portalrom_platform_docs_src_files)
LOCAL_ADDITONAL_JAVA_DIR := $(portalrom_platform_docs_LOCAL_ADDITIONAL_JAVA_DIR)

LOCAL_IS_HOST_MODULE := false
LOCAL_DROIDDOC_CUSTOM_TEMPLATE_DIR := vendor/portalrom/build/tools/droiddoc/templates-portalrom-sdk
LOCAL_ADDITIONAL_DEPENDENCIES := \
    services

LOCAL_JAVA_LIBRARIES := $(portalrom_platform_docs_java_libraries)

LOCAL_DROIDDOC_OPTIONS := \
        -android \
        -offlinemode \
        -exclude org.portalrom.platform.internal \
        -hidePackage org.portalrom.platform.internal \
        -hdf android.whichdoc offline \
        -hdf sdk.version $(portalrom_platform_docs_docs_SDK_VERSION) \
        -hdf sdk.rel.id $(portalrom_platform_docs_docs_SDK_REL_ID) \
        -hdf sdk.preview 0 \
        -since $(PORTALROM_SRC_API_DIR)/1.txt 1 \
        -since $(PORTALROM_SRC_API_DIR)/2.txt 2 \
        -since $(PORTALROM_SRC_API_DIR)/3.txt 3 \
        -since $(PORTALROM_SRC_API_DIR)/4.txt 4 \
        -since $(PORTALROM_SRC_API_DIR)/5.txt 5 \
        -since $(PORTALROM_SRC_API_DIR)/6.txt 6 \
        -since $(PORTALROM_SRC_API_DIR)/7.txt 7 \
        -since $(PORTALROM_SRC_API_DIR)/8.txt 8 \
        -since $(PORTALROM_SRC_API_DIR)/9.txt 9

$(full_target): $(portalrom_framework_built) $(gen)
#include $(BUILD_DROIDDOC)

include $(call first-makefiles-under,$(LOCAL_PATH))

# Cleanup temp vars
# ===========================================================
portalrom_platform_docs_src_files :=
portalrom_platform_docs_java_libraries :=
portalrom_platform_docs_LOCAL_ADDITIONAL_JAVA_DIR :=
