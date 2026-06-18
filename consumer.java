package logProcessingSystem;


public class consumer implements Runnable {
    private final sharedBuffer buffer;
    private final String name;

    public consumer(sharedBuffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                LogEvent event = buffer.consume();
                processLog(event);
                Thread.sleep(800);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void processLog(LogEvent event) {
        // Handle differently based on severity
        if (event.getLevel() == LogEvent.Level.ERROR) {
            System.out.println("🔴 [" + name + "] CRITICAL! → " + event.getMessage());
        } else if (event.getLevel() == LogEvent.Level.WARN) {
            System.out.println("🟡 [" + name + "] WARNING! → " + event.getMessage());
        } else {
            System.out.println("🟢 [" + name + "] INFO → " + event.getMessage());
        }
    }
}
