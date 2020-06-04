package com.solovova.iigame.gui

import com.solovova.iigame.engine.player.Player
import javafx.application.Application
import javafx.fxml.FXMLLoader.load
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Created by naik on 06.02.16.
 */
class MainGUI() : Application() {
    private val layout = "/main.fxml"

    override fun start(primaryStage: Stage?) {
        val fxml = javaClass.getResource(layout)
        println(fxml)
        val root = load<Parent>(fxml)

        primaryStage?.scene = Scene(root)
        primaryStage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainGUI::class.java)
        }

        @JvmStatic
        fun runThread() {
            Thread(Runnable {
                launch(MainGUI::class.java)
            }).start()
        }
    }
}