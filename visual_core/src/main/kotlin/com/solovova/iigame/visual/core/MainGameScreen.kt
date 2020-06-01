package com.solovova.iigame.visual.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.solovova.iigame.visual.core.actors.ActorHud
import com.solovova.iigame.visual.core.actors.ActorMap

class MainGameScreen(private val game: MainGame) : Screen {
    private val actorMap = ActorMap()
    private val actorHud = ActorHud(actorMap)
    private val batch = SpriteBatch()

    override fun resize(width: Int, height: Int) {
    }
    override fun pause() {}
    override fun resume() {}
    override fun show() {}
    override fun hide() {}

    init {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(145f/256f, 196f/256f, 53f/256f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.begin()
        actorMap.draw(batch,0f)
        batch.end()

        batch.begin()

        actorHud.draw(batch,0f)
        batch.end()
    }



    override fun dispose() {
        batch.dispose()
    }
}