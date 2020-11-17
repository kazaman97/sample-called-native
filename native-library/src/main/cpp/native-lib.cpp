#include <jni.h>
#include <fstream>
#include <iostream>
#include <unistd.h>
#include <string>
#include <sstream>
#include <filesystem>

#include "native-lib.h"
#include "android/log.h"

using namespace std;

static bool isStartCountUp = false;
static int countNum = 0;

void setJavaCount(JNIEnv *env, int value) {
    auto clazz = env->FindClass("com/example/native_library/NativeLibrary");
    auto methodID = env->GetStaticMethodID(clazz, "setCount", "(I)V");
    if (methodID != nullptr) {
        env->CallStaticVoidMethod(clazz, methodID, value);
    }
    env->DeleteLocalRef(clazz);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_native_1library_NativeLibrary_startCountUp(JNIEnv *env, jobject thiz) {
    isStartCountUp = true;
    while (isStartCountUp) {
        setJavaCount(env, countNum);
        sleep(1);
        countNum++;
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_native_1library_NativeLibrary_stopCountUp(JNIEnv *env, jobject thiz) {
    isStartCountUp = false;
}
