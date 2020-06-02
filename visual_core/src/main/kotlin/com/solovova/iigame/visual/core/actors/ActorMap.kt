package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.MapLayers
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.solovova.iigame.visual.core.MyInputProcessor
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class ActorMap(private val rc: ResContainer): Actor() {
    private val character: ActorCharacter

    private val cam: OrthographicCamera
    private val renderer: OrthogonalTiledMapRenderer

    private val firstLayerInd: IntArray
    private val secondLayerInd: IntArray

    fun getTurn():Int = character.turn

    init {

        cam = OrthographicCamera()
        cam.setToOrtho(false, 16*20f, 16*34f)
        renderer = OrthogonalTiledMapRenderer(rc.rcMap.getTiledMap(), 1f)

        val mapLayers: MapLayers = rc.rcMap.getTiledMap().layers
        firstLayerInd = intArrayOf(
                mapLayers.getIndex("Ground/terrain"),
                mapLayers.getIndex("Ground overlay"),
                mapLayers.getIndex("Road"),
                mapLayers.getIndex("NonBlockObjects")
        )

        secondLayerInd = intArrayOf(
                mapLayers.getIndex("Objects"),
                mapLayers.getIndex("Door/Roof"),
                mapLayers.getIndex("Roof object")
        )

        character = ActorCharacter(rc)

        val inputProcessor  = MyInputProcessor(character)
        Gdx.input.inputProcessor = inputProcessor

    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        canSetPosition()

        cam.update()
        renderer.setView(cam)

        Gdx.gl.glViewport(16,16,Gdx.graphics.width-32,Gdx.graphics.height-32-100)
        renderer.render(firstLayerInd)
        renderer.batch.begin()
        character.draw(renderer.batch,1f)
        renderer.batch.end()
        renderer.render(secondLayerInd)

    }

    private fun canSetPosition(){
        var posX = character.tx
        if (posX<10) posX=10
        if (posX>(38-10)) posX=38-10

        var posY = character.ty
        if (posY<15) posY=15
        if (posY>(34-17)) posY=34-17

        cam.position.set(posX*16f, posY*16f,0f)
    }
}