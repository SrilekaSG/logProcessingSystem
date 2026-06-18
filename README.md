# Concurrent Log Processing System in Java

## About
A real-world inspired Log Processing System built using Java Multithreading,
demonstrating how large-scale systems like Google, Netflix, and Amazon 
process millions of log events concurrently.

## Problem Statement
In real-world applications, multiple servers continuously generate log events.
These logs need to be collected, filtered, and processed based on severity
without data loss or race conditions.

## System Architecture
## Log Levels
| Level | Meaning | Example |
|---|---|---|
| INFO | Normal operation | User logged in successfully |
| WARN | Needs attention | High memory usage detected |
| ERROR | Critical issue | Database connection failed |

## Concepts Demonstrated
- Multi-threading (2 producer + 2 consumer threads)
- Thread Synchronization (synchronized keyword)
- Inter-thread communication (wait() and notifyAll())
- Bounded buffer management (max size 10)
- Severity-based log filtering and processing
- Performance statistics tracking
- Deadlock prevention
- Race condition handling

## Project Structure
logProcessingSystem/
└── src/
└── logProcessingSystem/
├── LogEvent.java       → Log message blueprint (source, level, message, timestamp)
├── sharedBuffer.java   → Thread-safe bounded buffer + statistics tracking
├── producer.java       → AppServer generating random log events
├── consumer.java       → LogProcessor handling logs by severity
└── main.java           → Entry point + statistics report

## Sample Output

Produced: [ERROR] AppServer-1 → Database connection failed

🔴 [LogProcessor-1] CRITICAL! → Database connection failed
Produced: [WARN] AppServer-2 → High memory usage detected
🟡 [LogProcessor-2] WARNING! → High memory usage detected
Produced: [INFO] AppServer-1 → User logged in successfully
🟢 [LogProcessor-1] INFO → User logged in successfully
Buffer FULL! Producer waiting...


## Technologies Used
- Java
- Core Java Multithreading
- synchronized, wait(), notifyAll()
- Java Collections (LinkedList, Queue)
- Object Oriented Programming (OOP)
- Enum (for log levels)
