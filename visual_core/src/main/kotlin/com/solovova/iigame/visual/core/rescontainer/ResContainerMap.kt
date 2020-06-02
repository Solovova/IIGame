package com.solovova.iigame.visual.core.rescontainer

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.solovova.iigame.visual.core.logic.LogicTiledMap

class ResContainerMap {
    private val tiledMap: TiledMap = TmxMapLoader().load("map/map.tmx")
    val collision:Array<Array<Int>> = LogicTiledMap().loadFromCacheOrTMap(tiledMap)

    fun getTiledMap():TiledMap = this.tiledMap
}