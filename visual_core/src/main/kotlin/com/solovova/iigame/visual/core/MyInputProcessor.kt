package com.solovova.iigame.visual.core

import com.badlogic.gdx.InputAdapter
import com.solovova.iigame.visual.core.actors.ActorCharacter

class MyInputProcessor(private val character: ActorCharacter?) : InputAdapter() {
    override fun keyDown(keycode: Int): Boolean {
        return character?.move(keycode) ?: false
    }
}