package com.solovova.iigame.gui.controller


import com.solovova.iigame.engine.player.PlayerCommands
import com.solovova.iigame.gui.MainGUI
import com.solovova.iigame.gui.Starter
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label


class MainController {

    @FXML
    lateinit var buttonUp: Button

    @FXML
    lateinit var testLabel: Label

    @FXML
    private fun buttonClicked() {
        //println("Button clicked!" + Starter.player?.getX())
        Starter.player?.doCommand(PlayerCommands.Up)
    }


}
