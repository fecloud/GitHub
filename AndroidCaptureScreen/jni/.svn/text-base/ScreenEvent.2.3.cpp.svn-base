#define LOG_TAG "ScreenEvent2.3"
#include <fcntl.h>
#include <stdio.h>

#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>

#include <binder/IMemory.h>
#include <surfaceflinger/ISurfaceComposer.h>

#include <SkImageEncoder.h>
#include <SkBitmap.h>
#include <SkStream.h>

#include "log.h"

using namespace android;

int ScreenCapture (char* out, int out_buf_len)
{
	//LOGD("GrabScreen");
    const String16 name("SurfaceFlinger");
    sp<ISurfaceComposer> composer;
    getService(name, &composer);
	//LOGD("getService");
    sp<IMemoryHeap> heap;
    uint32_t w, h;
    PixelFormat f;
    uint32_t imgLen = 0;
	//LOGD("captureScreen start");
    status_t err = composer->captureScreen(0, &heap, &w, &h, &f, 0, 0);
	//LOGD("captureScreen end");
    if (err != NO_ERROR) {
        LOGE("screen capture failed: %s\n", strerror(-err));
        return -1;
    }

    //LOGI("screen capture success: w=%u, h=%u, pixels=%p\n", w, h, heap->getBase());

    SkBitmap b;
    b.setConfig(SkBitmap::kARGB_8888_Config, w, h);
    b.setPixels(heap->getBase());

    //LOGD("now convert from bmp to jpg\n");
    SkDynamicMemoryWStream outStream;
    SkImageEncoder::EncodeStream(&outStream, b,
            SkImageEncoder::kJPEG_Type, SkImageEncoder::kDefaultQuality);

    imgLen = outStream.getOffset();
    if ( imgLen > out_buf_len ) {
	imgLen = out_buf_len;
    }

    outStream.copyTo(out);
	//("captureScreen finish");
    return imgLen;
}
