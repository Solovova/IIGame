package com.solovova.iigame.gui.auto

import com.solovova.iigame.engine.player.Player
import com.solovova.iigame.engine.player.PlayerCommands

class LMAutoGoTo {
    private val lmAutoGetWay: LMAutoGetWay = LMAutoGetWay()

    fun playerGoTo(player: Player, x: Int, y: Int) {
        val way = lmAutoGetWay.getWay(player.collision, Pair(player.getX(),player.getY()), Pair(x, y), player.weight)
        if (way != null)
            for (move in way) {
                player.doCommand(move)
                Thread.sleep(50)
            }

        println("GoTo: $x $y")
        println(way)
    }
}