package com.solovova.iigame.engine.player

import com.solovova.iigame.engine.utils.PlUtils
import com.solovova.iigame.engine.workpoints.ContainerWorkPoints

class Player(val collision: Array<Array<Int>>, val weight:Array<Array<Int>>) {
    private var turn: Int = 0
    private var x: Int = 12
    private var y: Int = 5
    val works: ContainerWorkPoints = ContainerWorkPoints()

    val stats: MutableMap<String, Float> = mutableMapOf(
            "Pep" to 100f,
            "Health" to 100f,
            "Food" to 100f,
            "Money" to 0f,
            "FoodQuantity" to 0f
    )

    private var worldTime: Int = 0 // Time in minutes

    companion object {
        const val MINUTES_PER_TURN = 3

        const val PEP_LOW = 40f
        const val PEP_UP = 80f
        const val PEP_PER_TURN = -(100f - 30f) / (12 * 60 / MINUTES_PER_TURN) ///12 hour from 100 to 30
        const val PEP_TO_HEALTH_MINUS = -0.2f
        const val PEP_TO_HEALTH_PLUS = 0.1f

        const val FOOD_LOW = 20f
        const val FOOD_UP = 60f
        const val FOOD_PER_TURN = -(100f - 50f) / (6 * 60 / MINUTES_PER_TURN) ///6 hour from 100 to 50
        const val FOOD_TO_HEALTH_MINUS = -0.2f
        const val FOOD_TO_HEALTH_PLUS = 0.1f

        const val HEALTH_LOW = 60f
        const val HEALTH_VERY_LOW = 30f
    }

    fun getX() = this.x
    fun getY() = this.y
    fun getTurn(): Int = this.turn
    fun getHealth(): Float = this.stats["Health"] ?: 0f
    fun getPep(): Float = this.stats["Pep"] ?: 0f
    fun getFood(): Float = this.stats["Food"] ?: 0f
    fun getMoney(): Float = this.stats["Money"] ?: 0f
    fun getFoodQuantity(): Float = this.stats["FoodQuantity"] ?: 0f

    fun getTime(): List<Int> {
        var t = this.worldTime
        val day = t / 1440
        t %= 1440
        val hour = t / 60
        val minute = t % 60
        return listOf(day, hour, minute)
    }

    private fun turn() {
        turn++

        val weight = this.weight[getX()][getY()] //ToDo

        worldTime += MINUTES_PER_TURN

        //Fatigue
        val scalarPep: Float = when {
            getFood() >= FOOD_UP -> 1f
            getFood() >= FOOD_LOW -> 1.5f
            else -> 2f
        }

        this.stats["Pep"] = PlUtils.putInRange(getPep() + PEP_PER_TURN * scalarPep * weight, 0f, 100f)

        //Food
        this.stats["Food"] = PlUtils.putInRange(getFood() + FOOD_PER_TURN*weight, 0f, 100f)

        //Health
        if (getPep() >= PEP_UP) this.stats["Health"] = getHealth() + PEP_TO_HEALTH_PLUS
        if (getPep() <= PEP_LOW) this.stats["Health"] = getHealth() + PEP_TO_HEALTH_MINUS


        if (getFood() <= FOOD_LOW) this.stats["Health"] = getHealth() + FOOD_TO_HEALTH_MINUS
        if (getFood() >= FOOD_UP) this.stats["Health"] = getHealth() + FOOD_TO_HEALTH_PLUS

        this.stats["Health"] = PlUtils.putInRange(getHealth(), 0f, 100f)
    }

    private fun setX(tX: Int) {
        if (isTilePosBlocked(tX, y)) return
        this.x = tX
        turn()
    }

    private fun setY(tY: Int) {
        if (isTilePosBlocked(x, tY)) return
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
            PlayerCommands.Right -> this.setX(this.x + 1)
            PlayerCommands.Left -> this.setX(this.x - 1)
            PlayerCommands.Up -> this.setY(this.y + 1)
            PlayerCommands.Down -> this.setY(this.y - 1)
            PlayerCommands.Do -> this.doWork()
            else -> {
            }
        }
    }

    private fun doWork() {
        turn()
        val work = works.getByCoordinate(this.getX(), this.getY())
        if (work != null) {
            if (getPep() <= 0f || getHealth() <= 0) {
                println("Can't work!!!")
                return
            } else {
                for (key in work.muteStats.keys) {
                    if (((work.muteStats[key] ?: 0f) < 0f) &&
                            (stats[key] ?: 0f) + (work.muteStats[key] ?: 0f) < 0f
                    ) {
                        println("Can't do it not enough ${key}!!!")
                        return
                    }
                }

                for (key in work.muteStats.keys) {
                    stats[key] = (stats[key] ?: 0f) + (work.muteStats[key] ?: 0f)
                    if (key in setOf("Food","Pep","Health")) stats[key] = PlUtils.putInRange((stats[key]?:0f), 0f, 100f)
                }
            }
        }
    }
}