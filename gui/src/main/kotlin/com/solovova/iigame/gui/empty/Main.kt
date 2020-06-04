package com.solovova.iigame.gui.empty

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.fxml.FXMLLoader.load
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Created by naik on 06.02.16.
 */
class Main : Application() {


    //val layout = "/resources/main.fxml"
    val layout = "/empty.fxml"

    override fun start(primaryStage: Stage?) {
        //File("main1.txt").writeText("teeestqe")
//        val fileContent = Main::class.java.getResource("main.fxml").readText()
//        println(fileContent)
        //FXMLLoader.load<>(layout)
        val fxml = javaClass.getResource("/empty.fxml")
        println(fxml)
        //val root = FXMLLoader.load<Parent>(fxml)

        val root = load<Parent>(javaClass.getResource("/empty.fxml"))

        println(root)
        System.setProperty("prism.lcdtext", "false") // for beautiful fonts on linux
        primaryStage?.scene = Scene(root)
        //primaryStage?.scene = Scene(load<Parent?>(Main.javaClass.getResource(layout)))
        primaryStage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}