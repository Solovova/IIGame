package com.solovova.iigame.visual.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.solovova.iigame.visual.core.actors.ActorHud
import com.solovova.iigame.visual.core.actors.ActorMap
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class MainGameScreen(rc: ResContainer) : Screen {
    private val actorMap:ActorMap = ActorMap(rc)
    private val actorHud:ActorHud = ActorHud(rc)


    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun show() {}
    override fun hide() {}

    init {
        val inputProcessor  = MyInputProcessor(rc)
        Gdx.input.inputProcessor = inputProcessor
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(145f/256f, 196f/256f, 53f/256f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        actorMap.draw()
        actorHud.draw()
    }

    override fun dispose() {
    }
}