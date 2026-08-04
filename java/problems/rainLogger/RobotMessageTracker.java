import java.util.*;

class RobotMessageTracker {
    Map<String, Long> map = new HashMap<>();

    record Pair(long timeout, String message) {
    }

    PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(pair -> pair.timeout));

    public void shouldPrintMessage(long timeStamp, String message) {
        while (!pq.isEmpty() && pq.peek().timeout <= timeStamp) {
            Pair timedOut = pq.poll();

            if (map.containsKey(timedOut.message) && map.get(timedOut.message) == timedOut.timeout) {
                System.out.println("timestamp: " + (timedOut.timeout - 10) + " | msg: " + timedOut.message);
                map.remove(timedOut.message);
            }
        }

        pq.offer(new Pair(timeStamp + 10, message));
        map.put(message, timeStamp + 10);
    }

    static void main() {
        RobotMessageTracker logger = new RobotMessageTracker();
        logger.shouldPrintMessage(1, "foo");
        logger.shouldPrintMessage(2, "bar");
        logger.shouldPrintMessage(3, "foo");
        logger.shouldPrintMessage(11, "foo");
        logger.shouldPrintMessage(12, "foo");
    }
}
