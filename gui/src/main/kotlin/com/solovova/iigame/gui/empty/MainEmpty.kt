package com.solovova.iigame.gui.empty

import javafx.application.Application
import javafx.fxml.FXMLLoader.load
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Created by naik on 06.02.16.
 */
class MainEmpty : Application() {
    val layout = "/empty.fxml"

    override fun start(primaryStage: Stage?) {
        val fxml = javaClass.getResource("/empty.fxml")
        val root = load<Parent>(fxml)
        primaryStage?.scene = Scene(root)
        primaryStage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainEmpty::class.java)
        }

    }
}