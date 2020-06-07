package com.solovova.iigame.visual.core.logic

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type


class LogicTiledMap {
    private fun convertToCollisionArray(tiledMap: TiledMap):Array<Array<Int>> {
        val collisionLayer = tiledMap.layers.get("Collisions") as TiledMapTileLayer
        val tiledMapCollisions = Array<Array<Int>>(collisionLayer.width) { Array<Int>(collisionLayer.height) { 0 } }
        for (x in 0..collisionLayer.width)
            for (y in 0..collisionLayer.height)
                if (collisionLayer.getCell(x, y) != null)
                    tiledMapCollisions[x][y] = 1
        return tiledMapCollisions
    }

    private fun convertToWeightArray(tiledMap: TiledMap):Array<Array<Int>> {
        val roadLayer = tiledMap.layers.get("Road") as TiledMapTileLayer
        val tiledMapRoad = Array<Array<Int>>(roadLayer.width) { Array<Int>(roadLayer.height) { 2 } }
        for (x in 0..roadLayer.width)
            for (y in 0..roadLayer.height)
                if (roadLayer.getCell(x, y) != null)
                    tiledMapRoad[x][y] = 1
        return tiledMapRoad
    }

    private fun saveCollision(tiledMap: TiledMap) {
        val tiledMapCollisions = this.convertToCollisionArray(tiledMap)
        val tiledMapCollisionsGson = Gson().toJson(tiledMapCollisions)
        File("tiledMapCollisionsGson.json").writeText(tiledMapCollisionsGson.toString())
    }

    private fun loadCollision(): Array<Array<Int>>? {
        var result:Array<Array<Int>>? = null
        try {
            val jsonStr = File("tiledMapCollisionsGson.json").readText()
            val gson = Gson()
            val arrayType: Type = object : TypeToken<Array<Array<Int>>>() {}.type
            result = gson.fromJson(jsonStr, arrayType)
        } catch (e: Exception) {
            println(e.stackTrace)
        }
        return result
    }

    fun loadCollisionFromCacheOrTMap(tiledMap: TiledMap):Array<Array<Int>> {
        val result:Array<Array<Int>>? = this.loadCollision()
        if (result != null) return result
        return this.convertToCollisionArray(tiledMap)
    }

    fun loadWeightFromTMap(tiledMap: TiledMap):Array<Array<Int>> {
        return this.convertToWeightArray(tiledMap)
    }

    fun saveToCache() {
        val tiledMap = TmxMapLoader().load("map/map.tmx")
        LogicTiledMap().saveCollision(tiledMap)
    }
}