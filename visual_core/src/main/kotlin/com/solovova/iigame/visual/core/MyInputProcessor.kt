package com.solovova.iigame.visual.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import com.solovova.iigame.engine.player.PlayerCommands
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class MyInputProcessor(private val rc: ResContainer) : InputAdapter() {
    private val commandTranslator: Map<Int, PlayerCommands> = mapOf(
            Input.Keys.UP to PlayerCommands.Up,
            Input.Keys.DOWN to PlayerCommands.Down,
            Input.Keys.LEFT to PlayerCommands.Left,
            Input.Keys.RIGHT to PlayerCommands.Right,
            Input.Keys.SPACE to PlayerCommands.Do
    )

    override fun keyDown(keycode: Int): Boolean {
        val playerCommand: PlayerCommands? = commandTranslator[keycode]
        if (playerCommand!=null) {
            rc.player.doCommand(playerCommand)
            return true
        }
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val offset = 150
        if (screenX<offset) {
            rc.player.doCommand(PlayerCommands.Left)
            return true
        }
        if (screenX>Gdx.graphics.width-offset) {
            rc.player.doCommand(PlayerCommands.Right)
            return true
        }
        if (screenY<offset) {
            rc.player.doCommand(PlayerCommands.Up)
            return true
        }
        if (screenY>Gdx.graphics.height-offset) {
            rc.player.doCommand(PlayerCommands.Down)
            return true
        }
        rc.player.doCommand(PlayerCommands.Do)
        //println("${screenX},${screenY}")
        return true
    }

}