LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := \
	EventCallBack.cpp \
	InputEvent.cpp \
	KeyLayoutMap.cpp \
	ReadEventHub.cpp \
	ReadDeviceThread.cpp \
	InputEventMonitor.cpp \
	OnLoad.cpp \
#Main.cpp
   
LOCAL_SHARED_LIBRARIES :=libandroid_runtime \
	libcutils \
	libutils \
	libhardware \
	libhardware_legacy
	
LOCAL_MODULE := libmonitorevent

LOCAL_MODULE_TAGS := eng
	
LOCAL_PRELINK_MODULE := false

include $(BUILD_SHARED_LIBRARY)
#include $(BUILD_EXECUTABLE)

