package com.solovova.iigame.engine.player

import com.solovova.iigame.engine.utils.PlUtils

class Player(private val collision:Array<Array<Int>>) {
    private var turn: Int = 0
    private var x: Int = 12
    private var y: Int = 5
    private var plFatigue: Float = 0f
    private var plHealth: Float = 100f
    private var plFood: Float = 100f
    private var plMoney: Float = 0f
    private var plFoodQuantity: Int = 0
    private var worldTime: Int = 0 // Time in minutes

    companion object {
        const val MINUTES_PER_TURN = 3
        const val FATIGUE_LOW = 80f
        const val FATIGUE_UP = 40f
        const val FATIGUE_PER_TURN = 0.3f
        const val FATIGUE_TO_HEALTH = 0.3f

        const val FOOD_LOW = 20f
        const val FOOD_UP = 60f
        const val FOOD_PER_TURN = 0.3f
        const val FOOD_TO_HEALTH = 0.3f
    }

    fun getX() = this.x
    fun getY() = this.y
    fun getTurn():Int = this.turn
    fun getHealth():Float = this.plHealth
    fun getFatigue():Float = this.plFatigue
    fun getFood():Float = this.plFood
    fun getMoney():Float = this.plMoney
    fun getFoodQuantity():Int = this.plFoodQuantity
    fun getTime():List<Int> {
        var t = this.worldTime
        val day = t / 1440
        t %= 1440
        val hour = t / 60
        val minute = t % 60
        return listOf(day,hour,minute)
    }

    private fun turn() {
        turn++

        worldTime += MINUTES_PER_TURN

        //Fatigue ToDo если еды меньше то больше устает
        plFatigue = PlUtils.putInRange(plFatigue+ FATIGUE_PER_TURN,0f,100f)

        //Food
        plFood = PlUtils.putInRange(plFood- FOOD_PER_TURN,0f,100f)

        //Health
        if (plFatigue>FATIGUE_LOW) plHealth -= FATIGUE_TO_HEALTH
        if (plFatigue<FATIGUE_UP) plHealth += FATIGUE_TO_HEALTH

        if (plFood< FOOD_LOW) plHealth -= FOOD_TO_HEALTH
        if (plFood> FOOD_UP) plHealth += FOOD_TO_HEALTH

        plHealth = PlUtils.putInRange(plHealth,0f,100f)
    }

    private fun setX(tX:Int) {
        if (isTilePosBlocked(tX,y)) return
        this.x = tX
        turn()
    }

    private fun setY(tY:Int) {
        if (isTilePosBlocked(x,tY)) return
        this.y = tY
        turn()
    }

    private fun isTilePosBlocked(tX: Int, tY: Int): Boolean {
        if (tX >= collision.size || tX < 0 || (collision.isNotEmpty() && tY >= collision[0].size) || tY < 0) {
            return true
        }
        return collision[tX][tY] == 1
    }

    fun doCommand(playerCommand: PlayerCommands) {
        when (playerCommand) {
            PlayerCommands.Right -> this.setX(this.x+1)
            PlayerCommands.Left -> this.setX(this.x-1)
            PlayerCommands.Up -> this.setY(this.y+1)
            PlayerCommands.Down -> this.setY(this.y-1)
            else -> {}
        }
    }
}