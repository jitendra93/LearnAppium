package com.jitendraalekar.test

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.MalformedURLException
import java.net.URL
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

class ConnectandFirstRun {

    @Test
    @Throws(MalformedURLException::class)
    fun setUp() {

        val currentRelativePath = Paths.get("")
        val app = File(currentRelativePath.toAbsolutePath().toString(), "ApiDemos.apk")

        println(app.absolutePath)
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("automationName", "UiAutomator2")
        capabilities.setCapability("platformName", "Android")
        capabilities.setCapability("platformVersion", "10")
        capabilities.setCapability("deviceName", "Android Emulator")
        capabilities.setCapability("app", app.absolutePath)
        val wd = AppiumDriver<MobileElement>(URL("http://0.0.0.0:4723/wd/hub"), capabilities)
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)


    }
}