package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.MapLayers
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import com.solovova.iigame.visual.core.MyInputProcessor

class ActorMap: Actor() {
    private val character: ActorCharacter
    private val tiledMap: TiledMap
    private val cam: OrthographicCamera
    private val renderer: OrthogonalTiledMapRenderer

    private val firstLayerInd: IntArray
    private val secondLayerInd: IntArray

    fun getTurn():Int = character.turn

    init {
        tiledMap = TmxMapLoader().load("map/map.tmx")
        cam = OrthographicCamera()
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        renderer = OrthogonalTiledMapRenderer(tiledMap, 1f)


        val mapLayers: MapLayers = tiledMap.layers
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

        character = ActorCharacter(tiledMap)

        val inputProcessor  = MyInputProcessor(character)
        Gdx.input.inputProcessor = inputProcessor

        cam.update()
        renderer.setView(cam)


    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        //super.draw(batch, parentAlpha)

        //Gdx.gl.glViewport(0,0,Gdx.graphics.width-150,Gdx.graphics.height)
        renderer.render(firstLayerInd)
        renderer.batch.begin()
        character.draw(renderer.batch,1f)
        renderer.batch.end()
        renderer.render(secondLayerInd)

    }


}