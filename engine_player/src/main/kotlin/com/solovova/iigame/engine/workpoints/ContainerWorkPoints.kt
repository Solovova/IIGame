package com.solovova.iigame.engine.workpoints

import com.solovova.iigame.engine.player.Player
import com.solovova.iigame.engine.utils.PlUtils

class ContainerWorkPoints {
    var works: MutableMap<String, WorkPoint> = mutableMapOf()

    private fun add(workPoint: WorkPoint) {
        works[PlUtils.coordinateToStr(workPoint.x, workPoint.y)] = workPoint
    }

    fun getByCoordinate(x: Int, y: Int): WorkPoint? = works[PlUtils.coordinateToStr(x, y)]

    init {
        this.add(WorkPoint(19, 16, "Sleep", mutableMapOf("Pep" to 2f)))
        this.add(WorkPoint(23, 18, "Eat",
                mutableMapOf(
                        "FoodQuantity" to -1f,
                        "Food" to 30f)))
        this.add(WorkPoint(29, 21, "Buy food", mutableMapOf(
                "FoodQuantity" to 1f,
                "Money" to -10f)))
        this.add(WorkPoint(38, 24, "Work",  mutableMapOf(
                "Food" to -2f,
                "Pep" to -2f,
                "Money" to 2f)))
    }
}