package com.solovova.iigame.visual.core

import com.badlogic.gdx.Game
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class MainGame : Game() {
    private var rc:ResContainer? = null

    override fun create() {
        rc = ResContainer()
        val locRc = rc
        if (locRc!=null) {
            val screen = MainGameScreen(locRc)
            setScreen( screen)
        }

    }

    fun getPlayer() = rc?.player
}