package logProcessingSystem;

public class LogEvent {
    // Log levels like real systems
    public enum Level {
        INFO, WARN, ERROR
    }

    private final String source;    // who generated this log
    private final Level level;      // INFO, WARN or ERROR
    private final String message;   // the log message
    private final long timestamp;   // when it was created

    public LogEvent(String source, Level level, String message) {
        this.source = source;
        this.level = level;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters
    public String getSource() { return source; }
    public Level getLevel() { return level; }
    public String getMessage() { return message; }
    public long getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + level + "] " + source + " → " + message + " (at " + timestamp + ")";
    }
}

