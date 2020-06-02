package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class ActorCharacter(private val rc: ResContainer): Actor() {

    private val textureCharacter: Texture = Texture("char_face.png")

    override fun draw(batch: Batch?, parentAlpha: Float) {
        batch?.draw(textureCharacter, rc.player.getX()*16f, rc.player.getY()*16f,16f,16f)
    }
}

