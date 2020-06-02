package com.solovova.iigame.engine.utils

class PlUtils {
    companion object {
        fun putInRange(v: Float, minV:Float,maxV:Float):Float {
            if (v<minV) return minV
            if (v>maxV) return maxV
            return v
        }

        fun putInRange(v: Int, minV:Int,maxV:Int):Int {
            if (v<minV) return minV
            if (v>maxV) return maxV
            return v
        }

        fun coordinateToStr(x: Int,y:Int): String = "${x}_${y}"
    }
}