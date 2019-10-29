package com.jitendraalekar.test

import com.google.common.collect.ImmutableMap
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.MalformedURLException
import java.net.URL
import java.nio.file.Paths
import java.util.concurrent.TimeUnit

class ConnectAndFirstRun {

    private fun desiredCapabilities(app: File): DesiredCapabilities {
        return DesiredCapabilities(
                mapOf<String, String>(
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

        val wd = AppiumDriver<MobileElement>(URL("http://0.0.0.0:4723/wd/hub"), capabilities)
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)

    }

    private fun appLocation(): File {
        val currentRelativePath = Paths.get("")
        val app = File(currentRelativePath.toAbsolutePath().toString(), "ApiDemos.apk")
        return app
    }
}