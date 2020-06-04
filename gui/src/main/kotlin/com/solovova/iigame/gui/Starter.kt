package com.solovova.iigame.gui

import com.solovova.iigame.engine.player.Player
import com.solovova.iigame.gui.empty.MainEmpty
import com.solovova.iigame.visual.core.MainGame
import com.solovova.iigame.visual.desktop.DesktopStarterThread
import javafx.application.Application


class Starter {
    companion object {
        var player: Player? = null

        @JvmStatic
        fun main(args: Array<String>) {
            val mainGame: MainGame = DesktopStarterThread().run()

            while (mainGame.getPlayer() == null) {Thread.sleep(100)}

            player = mainGame.getPlayer()

            MainGUI.runThread()
        }
    }
}