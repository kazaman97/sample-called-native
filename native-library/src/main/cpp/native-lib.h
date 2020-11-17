//
// Created by KazamaRyusei on 2020/11/12.
//

#ifndef SAMPLE_CALLED_NATIVE_NATIVE_LIB_H
#define SAMPLE_CALLED_NATIVE_NATIVE_LIB_H

#define TAG "NATIVE_LIB"
#define LOGE(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

void setJavaCount(JNIEnv *env, int value);

#endif //SAMPLE_CALLED_NATIVE_NATIVE_LIB_H
