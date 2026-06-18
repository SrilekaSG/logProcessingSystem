package logProcessingSystem;

public class main {
    public static void main(String[] args) {
        sharedBuffer buffer = new sharedBuffer();

        Thread producer1 = new Thread(new producer(buffer, "AppServer-1"));
        Thread producer2 = new Thread(new producer(buffer, "AppServer-2"));
        Thread consumer1 = new Thread(new consumer(buffer, "LogProcessor-1"));
        Thread consumer2 = new Thread(new consumer(buffer, "LogProcessor-2"));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        try {
            Thread.sleep(10000); // run for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        producer1.interrupt();
        producer2.interrupt();
        consumer1.interrupt();
        consumer2.interrupt();

        // Wait for threads to finish
        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print statistics
        System.out.println("\n========== LOG STATISTICS ==========");
        System.out.println("Total Logs Produced : " + buffer.getTotalProduced());
        System.out.println("Total Logs Consumed : " + buffer.getTotalConsumed());
        System.out.println("INFO  logs processed: " + buffer.getInfoCount());
        System.out.println("WARN  logs processed: " + buffer.getWarnCount());
        System.out.println("ERROR logs processed: " + buffer.getErrorCount());
        System.out.println("=====================================");
        System.out.println("Program ended.");
    }

}
