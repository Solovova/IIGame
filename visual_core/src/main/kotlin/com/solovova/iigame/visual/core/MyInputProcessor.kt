package com.solovova.iigame.visual.core

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.solovova.iigame.engine.player.PlayerCommands
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class MyInputProcessor(private val rc: ResContainer) : InputAdapter() {
    private val commandTranslator: Map<Int, PlayerCommands> = mapOf(
            Input.Keys.UP to PlayerCommands.Up,
            Input.Keys.DOWN to PlayerCommands.Down,
            Input.Keys.LEFT to PlayerCommands.Left,
            Input.Keys.RIGHT to PlayerCommands.Right
    )

    override fun keyDown(keycode: Int): Boolean {
        val playerCommand: PlayerCommands? = commandTranslator[keycode]
        if (playerCommand!=null) {
            rc.player.doCommand(playerCommand)
            return true
        }
        return false
    }
}