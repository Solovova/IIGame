package com.solovova.iigame.gui.auto

import com.solovova.iigame.engine.player.Player
import com.solovova.iigame.engine.player.PlayerCommands

class LMAutoGoTo {
    private fun getWayMark(x: Int, y: Int, mark: Int, list: MutableList<Pair<Int, Int>>, arr: Array<Array<Int>>, sX: Int, sY: Int, player: Player): Boolean {
        if (x !in 0..sX || y !in 0..sY || player.collision[x][y] == 1) return false
        if (arr[x][y]!=0) return false
        arr[x][y] = mark
        list.add(Pair(x, y))
        return true
    }

    private fun getMarkIn(x:Int,y:Int,arr: Array<Array<Int>>,sX:Int,sY:Int):Int {
        if (x !in 0..sX || y !in 0..sY) return 999
        return arr[x][y]
    }

    private fun getWay(player: Player, x: Int, y: Int) :List<PlayerCommands>?{
        val sX: Int = player.collision.size
        if (sX == 0) return null
        val sY: Int = player.collision[0].size
        val arrForWay: Array<Array<Int>> = Array(sX) {Array(sY) {0} }
        var mark:Int = 1
        arrForWay[player.getX()][player.getY()] = mark
        var listWay: MutableList<Pair<Int, Int>> = mutableListOf()
        listWay.add(Pair(player.getX(), player.getY()))

        var wayFind: Boolean = false
        while (!wayFind) {
            val newListWay: MutableList<Pair<Int, Int>> = mutableListOf()
            mark++
            for (pos in listWay) {
                getWayMark(pos.first-1,pos.second,mark,newListWay,arrForWay,sX,sY,player)
                getWayMark(pos.first+1,pos.second,mark,newListWay,arrForWay,sX,sY,player)
                getWayMark(pos.first,pos.second-1,mark,newListWay,arrForWay,sX,sY,player)
                getWayMark(pos.first,pos.second+1,mark,newListWay,arrForWay,sX,sY,player)
            }

            if (arrForWay[x][y]==0) {
                listWay = newListWay
            }else{
                wayFind = true
            }
        }

        //println("Way:${arrForWay[x][y]}")
        mark = arrForWay[x][y]
        var posX: Int = x
        var posY: Int = y
        val wayPoints:MutableList<PlayerCommands> = mutableListOf()
        var backToStart: Boolean = false
        while (!backToStart) {
            mark--
            if (getMarkIn(posX-1,posY,arrForWay,sX,sY) == mark) {
                posX--
                wayPoints.add(PlayerCommands.Right)
            }
            if (getMarkIn(posX+1,posY,arrForWay,sX,sY) == mark) {
                posX++
                wayPoints.add(PlayerCommands.Left)
            }
            if (getMarkIn(posX,posY-1,arrForWay,sX,sY) == mark) {
                posY--
                wayPoints.add(PlayerCommands.Up)
            }

            if (getMarkIn(posX,posY+1,arrForWay,sX,sY) == mark) {
                posY++
                wayPoints.add(PlayerCommands.Down)
            }

            if (posX == player.getX() && posY==player.getY()) backToStart=true

        }
        return wayPoints.reversed()
    }

    fun playerGoTo(player: Player, x: Int, y: Int) {
        val way = getWay(player, x, y)
        if (way!=null)
            for (move in way) {
                player.doCommand(move)
                Thread.sleep(50)
            }

        println("GoTo: $x $y")
        println(way)
    }
}