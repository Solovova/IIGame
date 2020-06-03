package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.maps.MapLayers
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.solovova.iigame.visual.core.rescontainer.ResContainer
import com.solovova.iigame.visual.core.util.PlUtils


class ActorMap(private val rc: ResContainer): Actor() {
    companion object {
        const val MAP_BORDER = 16
        const val MAP_BITS_PES_SQUARE = 16
    }

    private val textureCharacter: Texture = Texture("char_face.png")

    private val cam: OrthographicCamera = OrthographicCamera()
    private val renderer: OrthogonalTiledMapRenderer

    private val firstLayerInd: IntArray
    private val secondLayerInd: IntArray

    private val worldBoxWidth = 34
    private val worldBoxHeight = 34

    init {

        cam.setToOrtho(false, (worldBoxWidth*MAP_BITS_PES_SQUARE).toFloat(),
                (worldBoxHeight*MAP_BITS_PES_SQUARE).toFloat())
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
    }

    fun draw() {
        camSetPosition()

        cam.update()
        renderer.setView(cam)

        Gdx.gl.glViewport(MAP_BORDER, MAP_BORDER,Gdx.graphics.width- MAP_BORDER*2,Gdx.graphics.height- MAP_BORDER*2-ActorHud.HUD_HEIGHT)

        val uiMatrix = cam.combined.cpy()
//        uiMatrix.setToOrtho2D(MAP_BORDER.toFloat(),
//                MAP_BORDER.toFloat(),
//                (Gdx.graphics.width- MAP_BORDER*2).toFloat(),
//                (Gdx.graphics.height- MAP_BORDER*2-ActorHud.HUD_HEIGHT).toFloat())
//        renderer.batch.projectionMatrix = uiMatrix

        renderer.render(firstLayerInd)
        renderer.batch.begin()
        renderer.batch.draw(textureCharacter, (rc.player.getX()* MAP_BITS_PES_SQUARE).toFloat(),
                (rc.player.getY()*MAP_BITS_PES_SQUARE).toFloat(),
                MAP_BITS_PES_SQUARE.toFloat(),
                MAP_BITS_PES_SQUARE.toFloat())
        renderer.batch.end()
        renderer.render(secondLayerInd)
    }

    private fun camSetPosition(){
        val posX = PlUtils.putInRange(rc.player.getX(),worldBoxWidth/2,rc.rcMap.mapWith-worldBoxWidth/2)
        val posY = PlUtils.putInRange(rc.player.getY(),worldBoxHeight/2,rc.rcMap.mapHeight-worldBoxHeight/2)

        cam.position.set((posX* MAP_BITS_PES_SQUARE).toFloat(), (posY* MAP_BITS_PES_SQUARE).toFloat(),0f)
    }
}