package logProcessingSystem;

import java.util.LinkedList;
import java.util.Queue;

public class sharedBuffer {
    private final Queue<LogEvent> buffer = new LinkedList<>();
    private final int MAX_SIZE = 10;

    // Statistics
    private int totalProduced = 0;
    private int totalConsumed = 0;
    private int errorCount = 0;
    private int warnCount = 0;
    private int infoCount = 0;

    public synchronized void produce(LogEvent event) throws InterruptedException {
        while (buffer.size() == MAX_SIZE) {
            System.out.println(" Buffer FULL! Producer waiting...");
            wait();
        }
        buffer.add(event);
        totalProduced++;
        System.out.println(" Produced: " + event);
        notifyAll();
    }

    public synchronized LogEvent consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer EMPTY! Consumer waiting...");
            wait();
        }
        LogEvent event = buffer.poll();
        totalConsumed++;

        // Count by level
        if (event.getLevel() == LogEvent.Level.ERROR) errorCount++;
        else if (event.getLevel() == LogEvent.Level.WARN) warnCount++;
        else infoCount++;

        notifyAll();
        return event;
    }

    // Statistics getters
    public int getTotalProduced() { return totalProduced; }
    public int getTotalConsumed() { return totalConsumed; }
    public int getErrorCount() { return errorCount; }
    public int getWarnCount() { return warnCount; }
    public int getInfoCount() { return infoCount; }
}

