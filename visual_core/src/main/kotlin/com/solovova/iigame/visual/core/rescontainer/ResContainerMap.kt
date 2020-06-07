package com.solovova.iigame.visual.core.rescontainer

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.solovova.iigame.visual.core.logic.LogicTiledMap

class ResContainerMap {
    private val tiledMap: TiledMap = TmxMapLoader().load("map/map.tmx")
    val collision:Array<Array<Int>> = LogicTiledMap().loadCollisionFromCacheOrTMap(tiledMap)
    val weight:Array<Array<Int>> = LogicTiledMap().loadWeightFromTMap(tiledMap)
    val mapWith: Int
    val mapHeight: Int

    init {
        mapWith = collision.size
        mapHeight = if (collision.isEmpty()) 0 else collision[0].size
    }

    fun getTiledMap():TiledMap = this.tiledMap
}