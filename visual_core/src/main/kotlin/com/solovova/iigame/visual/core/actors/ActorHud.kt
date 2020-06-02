package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.solovova.iigame.visual.core.rescontainer.ResContainer


class ActorHud(private val rc: ResContainer) : Actor() {
    private val labelTurn: Label
    private val labelHealth: Label

    private val shapeRenderer:ShapeRenderer = ShapeRenderer()
    private val spriteBatch:SpriteBatch = SpriteBatch()


    var font: BitmapFont
    private var table: Table

    private fun update() {
        labelTurn.setText(String.format("%03d", rc.player.getTurn()))
    }

    fun draw() {
        Gdx.gl.glViewport(0,Gdx.graphics.height-100,Gdx.graphics.width,Gdx.graphics.height)
        var offsetYRect = 10f+70f
        var offsetYText = 23f+70f
        val steep = 20f

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = if (rc.player.getHealth()>70f) Color.GREEN else
            if (rc.player.getHealth()>30f) Color.YELLOW else Color.RED
        shapeRenderer.rect(70f,offsetYRect,150f*rc.player.getHealth()/100f,16f)
        offsetYRect -= steep
        shapeRenderer.color = if (rc.player.getFatigue()<=40f) Color.GREEN else
            if (rc.player.getFatigue()<=80f) Color.YELLOW else Color.RED
        shapeRenderer.rect(70f,offsetYRect,150f*rc.player.getFatigue()/100f,16f)
        offsetYRect -= steep
        shapeRenderer.color = if (rc.player.getFood()>=60f) Color.GREEN else
            if (rc.player.getFood()>=20f) Color.YELLOW else Color.RED
        shapeRenderer.rect(70f,offsetYRect,150f*rc.player.getFood()/100f,16f)

        shapeRenderer.end()

        spriteBatch.begin()


        font.draw(spriteBatch,"Turn: ${rc.player.getTurn()}",300f,offsetYText)
        font.draw(spriteBatch,"Health:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getHealth()),110f,offsetYText)
        offsetYText -= steep
        val time = rc.player.getTime()
        font.draw(spriteBatch,"Day: ${time[0]} Hour: ${time[1]} Min: ${time[2]}",300f,offsetYText)
        font.draw(spriteBatch,"Fatigue:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getFatigue()),110f,offsetYText)
        offsetYText -= steep
        font.draw(spriteBatch,"Food have: ${rc.player.getFoodQuantity()}",300f,offsetYText)
        font.draw(spriteBatch,"Food:",10f,offsetYText)
        font.draw(spriteBatch,String.format("%3.0f", rc.player.getFood()),110f,offsetYText)
        offsetYText -= steep
        font.draw(spriteBatch,"Money have: ${rc.player.getMoney()}",300f,offsetYText)

        spriteBatch.end()

    }

    override fun drawDebug(shapeRenderer: ShapeRenderer) {
        table.drawDebug(shapeRenderer)

    }

    init {
        val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts/proximanova.ttf"))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 16
        font = generator.generateFont(parameter)
        font.setColor(0.0f, 0.0f, 0.0f, 1.0f)

        generator.dispose()

        labelTurn = Label(String.format("%03d", 0), Label.LabelStyle(font, Color.BLACK))
        labelHealth = Label("100", Label.LabelStyle(font, Color.BLACK))

        //define a table used to organize hud's labels
        table = Table()


        table.add<Actor>(Label("Turn:", Label.LabelStyle(font, Color.BLACK))).left()
        table.add<Actor>(labelTurn).expandX().right()

        table.row()
        table.add<Actor>(Label("Rest:", Label.LabelStyle(font, Color.BLACK))).left()
        table.add<Actor>(labelHealth).expandX().right()
        table.debugCell()
        
        table.setFillParent(true)


    }


}