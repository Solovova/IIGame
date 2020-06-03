package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.solovova.iigame.engine.player.Player
import com.solovova.iigame.visual.core.rescontainer.ResContainer

class ActorHud(private val rc: ResContainer) {
    companion object {
        const val HUD_HEIGHT = 150
    }

    private val cam: OrthographicCamera = OrthographicCamera()
    private val shapeRenderer:ShapeRenderer = ShapeRenderer()
    private val spriteBatch:SpriteBatch = SpriteBatch()

    private var font: BitmapFont

    init {
        cam.setToOrtho(false, (34*16).toFloat(),
                (34*16).toFloat())
        cam.position.set(Vector2((34*16).toFloat()/2,(34*16).toFloat()/2),0f)
    }

    fun draw() {
        //cam.update()
        //renderer.setView(cam)

        Gdx.gl.glViewport(0,Gdx.graphics.height-HUD_HEIGHT,Gdx.graphics.width,HUD_HEIGHT)
        var offsetYRect = 10f+HUD_HEIGHT-50f
        var offsetYText = 31f+HUD_HEIGHT-50f
        val steep = 32f

        val offsetX1 = 100f
        val offsetX2 = 180f
        val offsetX3 = 330f
        val offsetX4 = 520f


        val uiMatrix = Matrix4()
        uiMatrix.setToOrtho2D(0f, 0f, Gdx.graphics.width.toFloat(), HUD_HEIGHT.toFloat())
        shapeRenderer.projectionMatrix = uiMatrix
        spriteBatch.projectionMatrix = uiMatrix

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = when {
            rc.player.getHealth()>=Player.HEALTH_LOW -> Color.GREEN
            rc.player.getHealth()>Player.HEALTH_VERY_LOW -> Color.YELLOW
            else -> Color.RED
        }
        shapeRenderer.rect(offsetX1,offsetYRect,200f*rc.player.getHealth()/100f,26f)
        offsetYRect -= steep
        shapeRenderer.color = when {
            rc.player.getPep()>=Player.PEP_UP -> Color.GREEN
            rc.player.getPep()>=Player.PEP_LOW -> Color.YELLOW
            else -> Color.RED
        }
        shapeRenderer.rect(offsetX1,offsetYRect,200f*rc.player.getPep()/100f,26f)
        offsetYRect -= steep
        shapeRenderer.color = when {
            rc.player.getFood()>=Player.FOOD_UP -> Color.GREEN
            rc.player.getFood()>=Player.FOOD_LOW -> Color.YELLOW
            else -> Color.RED
        }
        shapeRenderer.rect(offsetX1,offsetYRect,200f*rc.player.getFood()/100f,26f)

        shapeRenderer.end()

        spriteBatch.begin()


        font.draw(spriteBatch,"Turn: ${rc.player.getTurn()}",offsetX3,offsetYText)
        font.draw(spriteBatch,"Health:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getHealth()),offsetX2,offsetYText)
        offsetYText -= steep
        val time = rc.player.getTime()
        font.draw(spriteBatch,"Time: ${time[0]} : ${time[1]} : ${time[2]}",offsetX3,offsetYText)
        font.draw(spriteBatch,"Pep:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getPep()),offsetX2,offsetYText)
        offsetYText -= steep
        font.draw(spriteBatch,"Food: ${rc.player.getFoodQuantity().toInt()}",offsetX3,offsetYText)
        font.draw(spriteBatch,"Food:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getFood()),offsetX2,offsetYText)
        offsetYText -= steep
        font.draw(spriteBatch,"Money: ${rc.player.getMoney().toInt()}",offsetX3,offsetYText)


        offsetYText = 31f+HUD_HEIGHT-50f
        val work = rc.player.works.getByCoordinate(rc.player.getX(),rc.player.getY())
        if (work!=null) {
            font.draw(spriteBatch,work.describe,offsetX4,offsetYText)
            offsetYText -= steep
            for (key in work.muteStats.keys) {
                font.draw(spriteBatch,"${key}: ${work.muteStats[key]}",offsetX4,offsetYText)
                offsetYText -= steep
            }
        }

        spriteBatch.end()

    }

    init {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/proximanova.ttf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 26
        font = generator.generateFont(parameter)
        font.setColor(0.0f, 0.0f, 0.0f, 1.0f)
        generator.dispose()
    }
}