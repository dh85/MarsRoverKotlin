import Cardinal.*

enum class Cardinal(
    val value: String,
    private val left: String,
    private val right: String
) {
    NORTH("N", "W", "E"),
    EAST("E", "N", "S"),
    SOUTH("S", "E", "W"),
    WEST("W", "S", "N");

    fun right() = directionTo(right)
    fun left() = directionTo(left)

    private fun directionTo(d: String): Cardinal {
        for (direction in entries) if (direction.value == d) {
            return direction
        }
        return NORTH
    }
}

class Rover {
    private var cardinal = NORTH
    private var coordinates = Coordinates(0, 0)

    fun execute(commands: String): String {
        for (command in commands) {
            when (command) {
                'R' -> cardinal = cardinal.right()
                'L' -> cardinal = cardinal.left()
                'M' -> coordinates = coordinates.move(cardinal)
                else -> throw IllegalArgumentException("Invalid command passed in")
            }
        }
        return "$coordinates:${cardinal.value}"
    }
}

private const val GRID_SIZE = 10

data class Coordinates(val x: Int, var y: Int) {
    fun move(cardinal: Cardinal) = when (cardinal) {
        NORTH -> Coordinates(x, if (y == GRID_SIZE) 0 else y + 1)
        EAST -> Coordinates(if (x == GRID_SIZE) 0 else x + 1, y)
        WEST -> Coordinates(if (x == 0) GRID_SIZE else x -1, y)
        SOUTH -> Coordinates(x, if (y == 0) GRID_SIZE else y - 1)
    }
    override fun toString() = "$x:$y"
}
