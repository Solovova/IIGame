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

    init {

        cam = OrthographicCamera()
        cam.setToOrtho(false, 16*16f, 16*34f)
        renderer = OrthogonalTiledMapRenderer(rc.rcMap.getTiledMap(), 1f)

        val mapLayers: MapLayers = rc.rcMap.getTiledMap().layers
        firstLayerInd = intArrayOf(
                mapLayers.getIndex("Ground/terrain"),
                mapLayers.getIndex("Ground overlay"),
                mapLayers.getIndex("Road"),
                mapLayers.getIndex("NonBlockObjects"),
                mapLayers.getIndex("Points")
        )

        secondLayerInd = intArrayOf(
                mapLayers.getIndex("Objects"),
                mapLayers.getIndex("Door/Roof"),
                mapLayers.getIndex("Roof object")
        )

        character = ActorCharacter(rc)
    }

    fun draw() {
        canSetPosition()

        cam.update()
        renderer.setView(cam)

        Gdx.gl.glViewport(16,16,Gdx.graphics.width-32,Gdx.graphics.height-32-200)
        renderer.render(firstLayerInd)
        renderer.batch.begin()
        character.draw(renderer.batch,1f)
        renderer.batch.end()
        renderer.render(secondLayerInd)

    }

    private fun canSetPosition(){
        var posX = rc.player.getX()
        if (posX<12) posX=8
        if (posX>(rc.rcMap.mapWith-8)) posX=rc.rcMap.mapWith-8

        var posY = rc.player.getY()
        if (posY<15) posY=15
        if (posY>(rc.rcMap.mapHeight-17)) posY=rc.rcMap.mapHeight-17

        cam.position.set(posX*16f, posY*16f,0f)
    }
}