package com.solovova.iigame.engine.player

class Player(private val collision:Array<Array<Int>>) {
    private var turn: Int = 0
    private var x: Int = 0
    private var y: Int = 0

    fun getX() = this.x
    fun getY() = this.y
    fun getTurn() = this.turn

    fun setX(tX:Int) {
        if (isTilePosBlocked(tX,y)) return
        this.x = tX
        turn++
    }

    fun setY(tY:Int) {
        if (isTilePosBlocked(x,tY)) return
        this.y = tY
        turn++
    }

    private fun isTilePosBlocked(tX: Int, tY: Int): Boolean {
        if (tX >= collision.size || tX < 0 || (collision.isNotEmpty() && tY >= collision[0].size) || tY < 0) {
            return true
        }
        return collision[tX][tY] == 1
    }

    fun doCommand(playerCommand: PlayerCommands) {
        when (playerCommand) {
            PlayerCommands.Right -> this.setX(this.x+1)
            PlayerCommands.Left -> this.setX(this.x-1)
            PlayerCommands.Up -> this.setY(this.y+1)
            PlayerCommands.Down -> this.setY(this.y-1)
            else -> {}
        }
    }
}