package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class ActorButtons(private val rc: ResContainer) {
    private val spriteBatch: SpriteBatch = SpriteBatch()

    fun draw() {
        Gdx.gl.glViewport(Gdx.graphics.width-100,16,100,Gdx.graphics.height-32-100)


        spriteBatch.begin()
        spriteBatch.end()

    }

}