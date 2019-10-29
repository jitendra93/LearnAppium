package com.jitendraalekar.test

import com.google.common.collect.ImmutableMap
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.junit.jupiter.api.Test

class CommandTest {
    lateinit var driver : AppiumDriver<MobileElement>
    @Test
    fun test(){
        val connectAndFirstRun = ConnectAndFirstRun().also {
            it.setUp()
            driver = it.webDriver
        }

        print("""This method is used to get build version status of running Appium server
            webDriver.status => $driver.status
            """)
        driver.executeScript("mobile: swipe", ImmutableMap.of("direction", "down"));


    }
}