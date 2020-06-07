package com.solovova.iigame.gui.auto

import com.google.gson.Gson
import com.solovova.iigame.engine.player.PlayerCommands

class LMAutoGetWay {
    companion object {
        const val DEF_MARK = 1000000
    }

    private fun getWayMark(x: Int, y: Int, mark: Int, list: MutableList<Pair<Int, Int>>, arr: Array<Array<Int>>, sX: Int, sY: Int, arrCollision: Array<Array<Int>>, arrWeight: Array<Array<Int>>): Boolean {
        if (x !in 0..sX || y !in 0..sY || arrCollision[x][y] == 1) return false
        if (mark + arrWeight[x][y] < arr[x][y]) {
            arr[x][y] = mark + arrWeight[x][y]
            list.add(Pair(x, y))
            return true
        }
        return false
    }

    private fun getMarkIn(x: Int, y: Int, arr: Array<Array<Int>>, sX: Int, sY: Int): Int {
        if (x !in 0..sX || y !in 0..sY) return DEF_MARK
        return arr[x][y]
    }

    fun getWay(arrCollision: Array<Array<Int>>, fromPoint: Pair<Int, Int>, toPoint: Pair<Int, Int>, arrWeight: Array<Array<Int>>): List<PlayerCommands>? {
        val sX: Int = arrCollision.size
        if (sX == 0) return null
        val sY: Int = arrCollision.size
        if (sY == 0) return null
        if (toPoint.first !in 0..sX || toPoint.first !in 0..sY) return null
        if (arrCollision[fromPoint.first][fromPoint.second] != 0) return null
        if (arrCollision[toPoint.first][toPoint.second] != 0) return null

        val arrWay: Array<Array<Int>> = Array(sX) { Array(sY) { DEF_MARK } }
        var mark = 0
        arrWay[fromPoint.first][fromPoint.second] = mark
        val listWay: MutableList<Pair<Int, Int>> = mutableListOf(Pair(fromPoint.first, fromPoint.second))

        var wayFind = false
        var indListWay = 0
        while (!wayFind) {

            val pos = listWay[indListWay]
            mark = arrWay[pos.first][pos.second]
            println(mark)
            getWayMark(pos.first - 1, pos.second, mark, listWay, arrWay, sX, sY, arrCollision, arrWeight)
            getWayMark(pos.first + 1, pos.second, mark, listWay, arrWay, sX, sY, arrCollision, arrWeight)
            getWayMark(pos.first, pos.second - 1, mark, listWay, arrWay, sX, sY, arrCollision, arrWeight)
            getWayMark(pos.first, pos.second + 1, mark, listWay, arrWay, sX, sY, arrCollision, arrWeight)


            indListWay++

            if (listWay.size == indListWay) {
                if (arrWay[toPoint.first][toPoint.second] == DEF_MARK) {
                    return null
                } else {
                    wayFind = true
                }
            }

        }


        mark = arrWay[toPoint.first][toPoint.second]
        println(mark)
        var posX: Int = toPoint.first
        var posY: Int = toPoint.second
        val wayPoints: MutableList<PlayerCommands> = mutableListOf()
        var backToStart = false
        while (!backToStart) {

            var turn: PlayerCommands? = null
            if (getMarkIn(posX - 1, posY, arrWay, sX, sY) < mark) {
                mark = getMarkIn(posX - 1, posY, arrWay, sX, sY)
                turn = PlayerCommands.Right
            }
            if (getMarkIn(posX + 1, posY, arrWay, sX, sY) < mark) {
                mark = getMarkIn(posX + 1, posY, arrWay, sX, sY)
                turn = PlayerCommands.Left
            }
            if (getMarkIn(posX, posY - 1, arrWay, sX, sY) < mark) {
                mark = getMarkIn(posX, posY - 1, arrWay, sX, sY)
                turn = PlayerCommands.Up
            }

            if (getMarkIn(posX, posY + 1, arrWay, sX, sY) < mark) {
                mark = getMarkIn(posX, posY + 1, arrWay, sX, sY)
                turn = PlayerCommands.Down
            }

            if (turn != null) wayPoints.add(turn)


            when (turn) {
                PlayerCommands.Right -> posX--
                PlayerCommands.Left -> posX++
                PlayerCommands.Up -> posY--
                PlayerCommands.Down -> posY++
                else -> {
                }
            }

            if (posX == fromPoint.first && posY == fromPoint.second) backToStart = true

        }
        return wayPoints.reversed()
    }
}