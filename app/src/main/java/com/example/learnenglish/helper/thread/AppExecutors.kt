package com.example.learnenglish.helper.thread

import java.util.concurrent.Executor

class AppExecutors(private val diskIo: Executor, private val mainThread: Executor) {

    fun getDiskIo(): Executor {
        return diskIo
    }

    fun getMainThread(): Executor {
        return mainThread
    }
}