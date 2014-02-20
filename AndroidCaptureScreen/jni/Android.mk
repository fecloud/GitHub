LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := eng

LOCAL_SRC_FILES:= \
	AgentDaemon.cpp

LOCAL_SRC_FILES += ScreenEvent.2.3.cpp

LOCAL_SHARED_LIBRARIES := \
        libutils \
        libskia \
        libbinder \
        libui \
        libsurfaceflinger_client

LOCAL_MODULE := libscreencapture23
#LOCAL_CERTIFICATE := platform

LOCAL_C_INCLUDES := $(KERNEL_HEADERS) \
	$(JNI_H_INCLUDE) \
	external/skia/include/core \
	external/skia/include/effects \
	external/skia/include/images \
	external/skia/include/ports \
	external/skia/include/utils 

LOCAL_PRELINK_MODULE := false

include $(BUILD_SHARED_LIBRARY)
