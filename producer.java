package logProcessingSystem;


import java.util.Random;

public class producer implements Runnable {
    private final sharedBuffer buffer;
    private final String name;
    private final Random random = new Random();

    // Sample log messages
    private final String[] infoMessages = {
        "User logged in successfully",
        "Database connection established",
        "File uploaded successfully",
        "Cache refreshed"
    };

    private final String[] warnMessages = {
        "High memory usage detected",
        "Slow database query",
        "API response time high",
        "Disk space below 20%"
    };

    private final String[] errorMessages = {
        "Database connection failed",
        "Null pointer exception occurred",
        "File not found",
        "Authentication failed"
    };

    public producer(sharedBuffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Randomly pick log level
                int levelChoice = random.nextInt(3);
                LogEvent event;

                if (levelChoice == 0) {
                    String msg = infoMessages[random.nextInt(infoMessages.length)];
                    event = new LogEvent(name, LogEvent.Level.INFO, msg);
                } else if (levelChoice == 1) {
                    String msg = warnMessages[random.nextInt(warnMessages.length)];
                    event = new LogEvent(name, LogEvent.Level.WARN, msg);
                } else {
                    String msg = errorMessages[random.nextInt(errorMessages.length)];
                    event = new LogEvent(name, LogEvent.Level.ERROR, msg);
                }

                buffer.produce(event);
                Thread.sleep(500);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
