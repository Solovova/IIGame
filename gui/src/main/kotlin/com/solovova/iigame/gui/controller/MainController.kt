package com.solovova.iigame.gui.controller


import com.solovova.iigame.engine.player.Player
import com.solovova.iigame.engine.player.PlayerCommands
import com.solovova.iigame.gui.MainGUI
import com.solovova.iigame.gui.Starter
import com.solovova.iigame.gui.auto.LMAuto
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label


class MainController {
    val lmAuto:LMAuto = LMAuto()

    @FXML
    lateinit var buttonUp: Button

    @FXML
    lateinit var testLabel: Label

    @FXML
    private fun buttonClicked() {
        //println("Button clicked!" + Starter.player?.getX())
        Starter.player?.doCommand(PlayerCommands.Up)
    }

    @FXML
    private fun buttonGoToWork() {
        val player: Player = Starter.player ?: return
        lmAuto.lmAutoGoTo.playerGoTo(player,38,24)
    }

    @FXML
    private fun buttonGoToFood() {
        val player: Player = Starter.player ?: return
        lmAuto.lmAutoGoTo.playerGoTo(player,29,21)
    }

    @FXML
    private fun buttonGoToEat() {
        val player: Player = Starter.player ?: return
        lmAuto.lmAutoGoTo.playerGoTo(player,23,18)
    }

    @FXML
    private fun buttonGoToSleep() {
        val player: Player = Starter.player ?: return
        lmAuto.lmAutoGoTo.playerGoTo(player,19,16)
    }

    @FXML
    private fun buttonGoToDo() {
        val player: Player = Starter.player ?: return
        player.doCommand(PlayerCommands.Do)
    }
}
