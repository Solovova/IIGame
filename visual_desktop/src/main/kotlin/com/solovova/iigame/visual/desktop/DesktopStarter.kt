package com.solovova.iigame.visual.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.solovova.iigame.visual.core.MainGame


object DesktopStarter {
    @JvmStatic
    fun main(args: Array<String>) {
        val cfg = LwjglApplicationConfiguration()
        cfg.title = "IIGame"
        cfg.useGL30 = true
//        cfg.width = 720
//        cfg.height = 1280

        cfg.width = 1920
        cfg.height = 1080
        LwjglApplication(MainGame(), cfg)
    }
}