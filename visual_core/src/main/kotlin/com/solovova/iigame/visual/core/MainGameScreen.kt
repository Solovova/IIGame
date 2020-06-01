package com.solovova.iigame.visual.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.maps.MapLayers
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.solovova.iigame.visual.core.actors.ActorCharacter
import com.solovova.iigame.visual.core.actors.ActorHud


class MainGameScreen(private val game: MainGame) : Screen {
    private var character: ActorCharacter? = null
    private var hud: ActorHud? = null

    private val tiledMap: TiledMap
    private val camera: OrthographicCamera
    private val renderer: OrthogonalTiledMapRenderer

    companion object {
        const val UNIT_SCALE: Float = 1f/16f
    }

    private val firstLayerInd: IntArray
    private val secondLayerInd: IntArray
    private val batch = SpriteBatch()
    private val shapeRenderer = ShapeRenderer()

    private val prevX = Gdx.graphics.width

    init {
        tiledMap = TmxMapLoader().load("map/map.tmx")
        camera = OrthographicCamera()
        camera.setToOrtho(false,Gdx.graphics.width* UNIT_SCALE,Gdx.graphics.height* UNIT_SCALE)
        renderer = OrthogonalTiledMapRenderer(tiledMap, UNIT_SCALE)

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
        hud = ActorHud()

        val inputProcessor  = MyInputProcessor(character)
        Gdx.input.inputProcessor = inputProcessor

        camera.update()
        renderer.setView(camera)
    }


    override fun resize(width: Int, height: Int) {
    }
    override fun pause() {}
    override fun resume() {}



    override fun show() {

    }

    override fun hide() {

    }


    override fun render(delta: Float) {
        Gdx.gl.glClearColor(145f/256f, 196f/256f, 53f/256f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        hud?.update(character?.turn ?: 0)

        Gdx.gl.glViewport(0, 0, Gdx.graphics.width-((hud?.getTableWidth() ?: 0).toFloat()/prevX*Gdx.graphics.width).toInt(), Gdx.graphics.height)


        renderer.render(firstLayerInd)
        renderer.batch.begin()

        character?.draw(renderer.batch,1f)


        renderer.batch.end()
        renderer.render(secondLayerInd)

        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        batch.begin()

        hud?.draw(batch,1f)
        batch.end()

        //Day night
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        shapeRenderer.begin(ShapeType.Filled)
        shapeRenderer.color = Color(0f, 0f, 0f, (character?.turn ?: 0).toFloat()%10f/10f)
        shapeRenderer.rect(0f,0f,Gdx.graphics.width.toFloat(),Gdx.graphics.height.toFloat())
        shapeRenderer.end()
        Gdx.gl.glDisable(GL20.GL_BLEND)

//        shapeRenderer.setAutoShapeType(true)
//        shapeRenderer.begin()
//        hud?.drawDebug(shapeRenderer)
//        shapeRenderer.end()
    }

    override fun dispose() {
        batch.dispose()
        shapeRenderer.dispose()
    }

}