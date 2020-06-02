package com.solovova.iigame.visual.core

import com.badlogic.gdx.Game
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class MainGame : Game() {
    override fun create() {
        //LogicTiledMap().saveToCache()

        val rc = ResContainer()
        val screen = MainGameScreen(rc)
        setScreen( screen)
    }
}