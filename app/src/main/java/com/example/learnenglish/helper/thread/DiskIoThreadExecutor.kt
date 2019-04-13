package com.example.learnenglish.helper.thread

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskIoThreadExecutor : Executor {

    private val diskIo: Executor

    init {
        diskIo = Executors.newSingleThreadExecutor()
    }

    override fun execute(command: Runnable?) {
        diskIo.execute(command)
    }
}