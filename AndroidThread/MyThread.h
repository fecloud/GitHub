#ifndef _MYTHREAD_H
#define _MYTHREAD_H

#include <utils/threads.h>

namespace android {

class MyThread: public Thread {
public:
	MyThread();
	//virtual ~MyThread();
	//如果返回true,循环调用此函数,返回false下一次不会再调用此函数
	virtual bool threadLoop();
};

}
#endif
