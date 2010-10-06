package wlexample.tests

import org.junit.Test
import org.scalatest.junit.JUnitSuite

import com.objogate.wl.swing.AWTEventQueueProber
import com.objogate.wl.swing.driver.{ComponentDriver, JButtonDriver, JFrameDriver, JLabelDriver}
import ComponentDriver.{named, showingOnScreen}
import com.objogate.wl.swing.gesture.GesturePerformer

import org.hamcrest.Matchers._

import javax.swing.JButton

import scala.actors.Actor.actor

import wlexample.Main
import wlexample.MainWindow._

class WLExampleToEndTest extends JUnitSuite {
  private val appRunner = new AppRunner
  
  @Test def statusChangesWhenButtonClicked() {
    appRunner.startApp
    appRunner.showsDefaultStatus
    appRunner.clickStatusChangeButton
    appRunner.showsStatusIsChanged
  }
  
}

class AppRunner {
  private val driver = new UIDriver
  
  def startApp() {
    val a = actor { Main.main(Array("")) }
  }
  
  def clickStatusChangeButton() { driver.clickSpankButton }
  
  def showsDefaultStatus = driver.showsSpankStatus(DEFAULT_STATUS_TEXT)
  
  def showsStatusIsChanged= driver.showsSpankStatus(CHANGED_STATUS_TEXT)
}

class UIDriver extends JFrameDriver (
  new GesturePerformer,
  new AWTEventQueueProber,
  named(WINDOW_NAME),
  showingOnScreen
) {
  
  def clickSpankButton() {
    SpankButton.click()
  }
  
  def showsSpankStatus(statusText: String) = SpankStatusLabel.hasText(equalTo(statusText))
  
  private def SpankStatusLabel = new JLabelDriver(this, named(STATUS_LABEL_NAME))
  
  private def SpankButton = new JButtonDriver(this, classOf[JButton], named(BUTTON_NAME))
  
}
