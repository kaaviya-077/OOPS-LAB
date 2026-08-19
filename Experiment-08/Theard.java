class Data {
    int value;
    boolean available = false;

    synchronized void produce(int v) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        value = v;
        available = true;
        System.out.println("Produced: " + value);
        notifyAll();
    }

    synchronized void consume() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Consumed: " + value);
        available = false;
        notifyAll();
    }
}

public class Theard {
    public static void main(String[] args) {
        Data d = new Data();

        new Thread(() -> d.produce(10)).start();
        new Thread(() -> d.consume()).start();
    }
}
