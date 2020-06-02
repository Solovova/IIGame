package com.solovova.iigame.visual.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.solovova.iigame.visual.core.actors.ActorHud
import com.solovova.iigame.visual.core.actors.ActorMap
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class MainGameScreen(private val rc: ResContainer) : Screen {
    private val batch:SpriteBatch = SpriteBatch()
    private val actorMap:ActorMap
    private val actorHud:ActorHud


    override fun resize(width: Int, height: Int) {
    }
    override fun pause() {}
    override fun resume() {}
    override fun show() {}
    override fun hide() {}

    init {
        actorMap = ActorMap(rc)
        actorHud = ActorHud(actorMap)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(145f/256f, 196f/256f, 53f/256f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        actorMap.draw(batch,0f)

        batch.begin()

        actorHud.draw(batch,0f)
        batch.end()
    }



    override fun dispose() {
        batch.dispose()
    }
}