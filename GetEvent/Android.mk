LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := \
	EventCallBack.cpp \
	InputEvent.cpp \
	ReadDevice.cpp \
	InputEventMonitor.cpp \
	OnLoad.cpp
   
LOCAL_SHARED_LIBRARIES :=libandroid_runtime \
	libcutils \
	libutils  
	
LOCAL_MODULE := libmonitorevent

LOCAL_MODULE_TAGS := eng
	
LOCAL_PRELINK_MODULE := false

include $(BUILD_SHARED_LIBRARY)
#include $(BUILD_EXECUTABLE)

