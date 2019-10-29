package com.jitendraalekar.test

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.jupiter.api.Test
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.MalformedURLException
import java.net.URL
import java.nio.file.Paths
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class ActivityCheck {

    lateinit var driver : AndroidDriver<MobileElement>
    private fun desiredCapabilities(app: File): DesiredCapabilities {
        return DesiredCapabilities(
                mapOf<String, Any>(
                        MobileCapabilityType.AUTOMATION_NAME to "UiAutomator2",
                        MobileCapabilityType.PLATFORM_NAME to "Android",
                        MobileCapabilityType.PLATFORM_VERSION to "10",
                        MobileCapabilityType.DEVICE_NAME to "Android Emulator",
                        MobileCapabilityType.APP to app.absolutePath
                )
        )
    }

    @Test
    @Throws(MalformedURLException::class)
    fun setUp() {

        val capabilities = desiredCapabilities(appLocation())

        driver = AndroidDriver(URL("http://0.0.0.0:4723/wd/hub"), capabilities)
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)

        assertEquals(driver.currentActivity(),".ApiDemos")
        assertEquals(driver.currentPackage,"io.appium.android.apis")

    }

    private fun appLocation(): File {
        val currentRelativePath = Paths.get("")
        val app = File(currentRelativePath.toAbsolutePath().toString(), "ApiDemos.apk")
        return app
    }
}