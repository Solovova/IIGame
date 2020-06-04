package com.solovova.iigame.gui.empty

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane

class SAKApplication : Application() {

    override fun start(primaryStage: Stage) {
        val btn = Button()
        btn.text = "Say 'Hello World'"
        btn.onAction = EventHandler<ActionEvent> { println("Hello World!") }

        val root = StackPane()
        root.children.add(btn)

        val scene = Scene(root, 300.0, 250.0)

        if (primaryStage != null) {
            primaryStage.title = "Hello World!"
            primaryStage.scene = scene
            primaryStage.show()
        }
    }

}

fun main(args: Array<String>) {
    Application.launch(SAKApplication::class.java, *args)
}