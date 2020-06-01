package com.solovova.iigame.visual.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table


class ActorHud() : Actor() {
    private val labelTurn: Label
    private val labelHealth: Label

    var font: BitmapFont
    private val table: Table

    fun update(turn:Int) {
        labelTurn.setText(String.format("%03d", turn))
    }

    override fun draw(batch: Batch?, alf: Float) {
        table.draw(batch,1f)
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
        table.setFillParent(true)

        table.add<Actor>(Label("Turn:", Label.LabelStyle(font, Color.BLACK))).left()
        table.add<Actor>(labelTurn).expandX().right()

        table.row()
        table.add<Actor>(Label("Rest:", Label.LabelStyle(font, Color.BLACK))).left()
        table.add<Actor>(labelHealth).expandX().right()
        table.debugCell()


//        table.left()
//        table.bottom()

        table.setSize(80f, 100f)
        table.setPosition(Gdx.graphics.width.toFloat()-80f, Gdx.graphics.height.toFloat()-100f)
    }

    fun getTableWidth():Int {
        return table.width.toInt()
    }
}