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

#define TEST "./test.txt"

#define LOGE(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

static bool isStartCountUp = false;
static int countNum = 0;

bool readFile(const std::string& path) {
    ifstream ifs(path);

    char str[BUFF_SIZE];
    if (ifs.fail()) {
        LOGE("failed read file");
        return false;
    }
    while (ifs.getline(str, BUFF_SIZE)) {
        LOGE("%s", str);
    }
    return true;
}

//bool printCurrentFileName() {
////    filesystem::directory_iterator iterator;
////    error_code err;
//
//    for (const auto & entry : filesystem::directory_iterator("./")) {
//
////        file_names.push_back( entry.path().string() );
////        printf("%s\n", file_names.back().c_str());
////        LOGE("printCurrentFileName: %s", entry.path().c_str());
//    }
//
//    /* エラー処理 */
////    if (err) {
////        std::cout << err.value() << std::endl;
////        std::cout << err.message() << std::endl;
////        return false;
////    }
//    return true;
//}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_native_1library_NativeLibrary_stringFromJNI(JNIEnv *env, jobject thiz) {
        bool isRead = readFile(TEST);
//    bool isPrintFileName = printCurrentFileName();

        stringstream stringStream;
        stringStream << "readFile: ";
        stringStream << (isRead ? "Success" : "Failed") << endl;
//    stringStream << "printCurrentFileName";
//    stringStream << (isPrintFileName ? "Success" : "Failed") << endl;

//        return env->NewStringUTF(stringStream.str().c_str());
    return env->NewStringUTF("hello");
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_native_1library_NativeLibrary_startCountUp(JNIEnv *env, jobject thiz) {
    isStartCountUp = true;
    while (isStartCountUp) {
        LOGE("count: %d", countNum);
        sleep(1);
        countNum++;
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_native_1library_NativeLibrary_stopCountUp(JNIEnv *env, jobject thiz) {
    isStartCountUp = false;
}
