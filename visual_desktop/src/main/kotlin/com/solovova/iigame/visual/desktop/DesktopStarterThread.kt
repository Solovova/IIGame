package com.solovova.iigame.visual.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.solovova.iigame.visual.core.MainGame

class DesktopStarterThread {
    fun run(): MainGame {
        val cfg = LwjglApplicationConfiguration()
        cfg.title = "IIGame"
        cfg.useGL30 = true
        cfg.width = 1920
        cfg.height = 1080
        val mainGame = MainGame()
        Thread(Runnable {
            LwjglApplication(mainGame, cfg)
        }).start()
        return mainGame
    }
}