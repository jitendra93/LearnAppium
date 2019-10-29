package com.jitendraalekar.test

import com.google.common.collect.ImmutableMap
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.android.Activity
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.net.MalformedURLException
import java.net.URL
import java.nio.file.Paths
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals


class ActivityInteractions {

    private val SEARCH_ACTIVITY = ".app.SearchInvoke"
    private val ALERT_DIALOG_ACTIVITY = ".app.AlertDialogSamples"
    private val PACKAGE = "io.appium.android.apis"

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

        //testSendKeys()

        testOpensAlert()

    }

    private fun appLocation(): File {
        val currentRelativePath = Paths.get("")
        val app = File(currentRelativePath.toAbsolutePath().toString(), "ApiDemos.apk")
        return app
    }


    fun testSendKeys() {
        driver.startActivity(Activity(PACKAGE, SEARCH_ACTIVITY))
        val searchBoxEl = driver.findElementById("txt_query_prefill") as AndroidElement
        searchBoxEl.sendKeys("Hello world!")
        val onSearchRequestedBtn = driver.findElementById("btn_start_search") as AndroidElement
        onSearchRequestedBtn.click()
        val searchText = WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/search_src_text"))) as AndroidElement
        val searchTextValue = searchText.text
       assertEquals(searchTextValue, "Hello world!")
    }

    @Test
    fun testOpensAlert() {
        // Open the "Alert Dialog" activity of the android app
        driver.startActivity(Activity(PACKAGE, ALERT_DIALOG_ACTIVITY))

        // Click button that opens a dialog
        val openDialogButton = driver.findElementById("io.appium.android.apis:id/select_button") as AndroidElement
        openDialogButton.click()

        val view = WebDriverWait(driver,20)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[1]")))
        as AndroidElement

        view.click()

        val view2 = WebDriverWait(driver,20)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("android:id/message")))
        as AndroidElement

        assertEquals(view2.text,"You selected: 0 , Command one")



    }
}