package com.solovova.iigame.visual.core

import com.badlogic.gdx.Game
import com.solovova.iigame.visual.core.logic.LogicTiledMap


class MainGame : Game() {
    override fun create() {
        LogicTiledMap().saveToCache()

        val screen = MainGameScreen(this)
        setScreen( screen)
    }

    override fun resize(width: Int, height: Int) {
        //super.resize(width, height)

    }
}