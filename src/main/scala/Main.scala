package wlexample

import java.awt.FlowLayout
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.{JFrame, JButton, JLabel}

import scala.actors.Actor.actor

object Main {
  def main(args: Array[String]) {
    startUserInterface()
  }
  
  def startUserInterface() {
    val a = actor { new MainWindow }
  }
}

object MainWindow {
  val WINDOW_NAME = "MainWindow"
  val WINDOW_TITLE = "Spank Me"
  val STATUS_LABEL_NAME = "SpankStatus"
  val BUTTON_NAME = "SpankButton"  
  val BUTTON_TEXT = "Spank"
  val DEFAULT_STATUS_TEXT = "Unspanked"
  val CHANGED_STATUS_TEXT = "Spanked!"
}

class MainWindow extends JFrame(MainWindow.WINDOW_TITLE) {
  import MainWindow._
  
  val button = SpankButton(BUTTON_TEXT)
  val statusLabel = SpankStatusLabel(DEFAULT_STATUS_TEXT)
  setUpWindow()
  
  private def setUpWindow() {  
    setName(WINDOW_NAME)
    setLayout(new FlowLayout)
    add(button)
    add(statusLabel)
    pack()
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setVisible(true)
  }
  
  private def SpankStatusLabel(initialText: String) = {
    val status = new JLabel(initialText)
    status.setName(STATUS_LABEL_NAME)
    status
  }
  
  private def SpankButton(text: String) = {
    val button = new JButton(text)
    button.setName(BUTTON_NAME)
    button.addActionListener(new ActionListener {
      def actionPerformed(event: ActionEvent) {
        statusLabel.setText(CHANGED_STATUS_TEXT)
      }
    })
    button
  }
  
}

