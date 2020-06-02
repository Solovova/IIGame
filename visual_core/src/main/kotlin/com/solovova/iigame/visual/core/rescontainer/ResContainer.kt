package com.solovova.iigame.visual.core.rescontainer

import com.solovova.iigame.engine.player.Player

class ResContainer {
    val rcMap:ResContainerMap = ResContainerMap()
    val player: Player = Player(rcMap.collision)
}