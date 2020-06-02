package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.solovova.iigame.engine.player.Player
import com.solovova.iigame.visual.core.rescontainer.ResContainer


class ActorHud(private val rc: ResContainer) : Actor() {
    private val shapeRenderer:ShapeRenderer = ShapeRenderer()
    private val spriteBatch:SpriteBatch = SpriteBatch()

    private var font: BitmapFont

    fun draw() {
        Gdx.gl.glViewport(0,Gdx.graphics.height-100,Gdx.graphics.width,Gdx.graphics.height)
        var offsetYRect = 10f+70f
        var offsetYText = 23f+70f
        val steep = 20f

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = when {
            rc.player.getHealth()>=Player.HEALTH_LOW -> Color.GREEN
            rc.player.getHealth()>Player.HEALTH_VERY_LOW -> Color.YELLOW
            else -> Color.RED
        }
        shapeRenderer.rect(70f,offsetYRect,150f*rc.player.getHealth()/100f,16f)
        offsetYRect -= steep
        shapeRenderer.color = when {
            rc.player.getPep()>=Player.PEP_UP -> Color.GREEN
            rc.player.getPep()>=Player.PEP_LOW -> Color.YELLOW
            else -> Color.RED
        }
        shapeRenderer.rect(70f,offsetYRect,150f*rc.player.getPep()/100f,16f)
        offsetYRect -= steep
        shapeRenderer.color = when {
            rc.player.getFood()>=Player.FOOD_UP -> Color.GREEN
            rc.player.getFood()>=Player.FOOD_LOW -> Color.YELLOW
            else -> Color.RED
        }
        shapeRenderer.rect(70f,offsetYRect,150f*rc.player.getFood()/100f,16f)

        shapeRenderer.end()

        spriteBatch.begin()


        font.draw(spriteBatch,"Turn: ${rc.player.getTurn()}",300f,offsetYText)
        font.draw(spriteBatch,"Health:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getHealth()),110f,offsetYText)
        offsetYText -= steep
        val time = rc.player.getTime()
        font.draw(spriteBatch,"Day: ${time[0]} Hour: ${time[1]} Min: ${time[2]}",300f,offsetYText)
        font.draw(spriteBatch,"Pep:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getPep()),110f,offsetYText)
        offsetYText -= steep
        font.draw(spriteBatch,"Food have: ${rc.player.getFoodQuantity()}",300f,offsetYText)
        font.draw(spriteBatch,"Food:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getFood()),110f,offsetYText)
        offsetYText -= steep
        font.draw(spriteBatch,"Money have: ${rc.player.getMoney()}",300f,offsetYText)


        offsetYText = 23f+70f
        val work = rc.player.works.getByCoordinate(rc.player.getX(),rc.player.getY())
        if (work!=null) {
            font.draw(spriteBatch,"${work.describe} - Press SPACE",500f,offsetYText)
            offsetYText -= steep
            for (key in work.muteStats.keys) {
                font.draw(spriteBatch,"${key}: ${work.muteStats[key]}",500f,offsetYText)
                offsetYText -= steep
            }
        }



        spriteBatch.end()

    }

    init {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/proximanova.ttf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 16
        font = generator.generateFont(parameter)
        font.setColor(0.0f, 0.0f, 0.0f, 1.0f)
        generator.dispose()
    }


}