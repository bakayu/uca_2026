
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SnakeGame {
    private class Point {
        final int coordX;
        final int coordY;

        public Point(int x, int y) {
            coordX = x;
            coordY = y;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Point p))
                return false;
            return coordX == p.coordX && coordY == p.coordY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(coordX, coordY);
        }
    }

    private final int width;
    private final int height;
    private Point currentFoodPoint;
    private final Deque<Point> foodSpawnQueue;
    private final Deque<Point> snakeBodyQueue;
    private final Set<Point> snakeBodySet;
    private int score;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        foodSpawnQueue = new ArrayDeque<>();
        snakeBodyQueue = new ArrayDeque<>();
        snakeBodySet = new HashSet<>();
        score = 0;

        Point origin = new Point(0, 0);
        snakeBodySet.add(origin);
        snakeBodyQueue.offer(origin);

        for (int[] f : food) {
            foodSpawnQueue.offer(new Point(f[0], f[1]));
        }
        currentFoodPoint = foodSpawnQueue.poll();
    }

    public int move(String direction) {
        Point snakeHead = snakeBodyQueue.peekFirst();
        Point snakeTail = snakeBodyQueue.peekLast();
        Point newSnakeHead;
        switch (direction) {
            case "U" -> newSnakeHead = new Point(snakeHead.coordX - 1, snakeHead.coordY);
            case "D" -> newSnakeHead = new Point(snakeHead.coordX + 1, snakeHead.coordY);
            case "L" -> newSnakeHead = new Point(snakeHead.coordX, snakeHead.coordY - 1);
            default -> newSnakeHead = new Point(snakeHead.coordX, snakeHead.coordY + 1);

        }

        if (snakeBodySet.contains(newSnakeHead) && !newSnakeHead.equals(snakeTail)) {
            return -1;
        }
        if (newSnakeHead.coordX < 0 || newSnakeHead.coordX >= height ||
                newSnakeHead.coordY < 0 || newSnakeHead.coordY >= width) {
            return -1;
        }

        if (newSnakeHead.equals(currentFoodPoint)) {
            score++;
            currentFoodPoint = foodSpawnQueue.poll();
            snakeBodyQueue.offerFirst(newSnakeHead);
            snakeBodySet.add(newSnakeHead);
            return score;
        }

        snakeBodyQueue.pollLast();
        snakeBodySet.remove(snakeTail);

        snakeBodyQueue.offerFirst(newSnakeHead);
        snakeBodySet.add(newSnakeHead);

        return score;
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame(3, 2, new int[][] { { 1, 2 }, { 0, 1 } });
        System.out.println(snakeGame.move("R")); // move to (0,1), no food there -> score 0
        System.out.println(snakeGame.move("D")); // move to (1,1), no food there -> score 0
        System.out.println(snakeGame.move("R")); // move to (1,2), food here! -> score 1, snake grows
        System.out.println(snakeGame.move("U")); // move to (0,2), no food -> score 1
        System.out.println(snakeGame.move("L")); // move to (0,1), food here! -> score 2, snake grows
        System.out.println(snakeGame.move("U")); // moves out of bounds -> game over, return -1
    }
}
