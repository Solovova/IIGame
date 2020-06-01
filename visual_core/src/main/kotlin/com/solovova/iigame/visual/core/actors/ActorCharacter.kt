package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.scenes.scene2d.Actor
import com.solovova.iigame.visual.core.logic.LogicTiledMap

class ActorCharacter(tiledMap: TiledMap): Actor() {

    private val textureCharacter: Texture = Texture("char_face.png")
    var turn: Int = 0
    private var collision:Array<Array<Int>> = LogicTiledMap().loadFromCacheOrTMap(tiledMap)


    private var tx: Int = 0
        set(value) {
            if (isTilePosBlocked(value,ty)) return
            field = value
            turn++
        }
    private var ty: Int = 0
        set(value) {
            if (isTilePosBlocked(tx,value)) return
            field = value
            turn++
        }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(textureCharacter, tx*16f, ty*16f,16f,16f)
    }

    fun move(keycode: Int):Boolean {
        if (keycode == Input.Keys.UP ) this.ty++
        if (keycode == Input.Keys.DOWN) this.ty--
        if (keycode == Input.Keys.LEFT) this.tx--
        if (keycode == Input.Keys.RIGHT) this.tx++
        return true
    }

    init {
        this.tx = 1
        this.ty = 1
        this.turn = 0
    }

    private fun isTilePosBlocked(tx: Int, ty: Int): Boolean {
        if (tx >= collision.size || tx < 0 || (collision.isNotEmpty() && ty >= collision[0].size) || ty < 0) {
            return true
        }
        return collision[tx][ty] == 1
    }
}

