package main.study;

/**
 * @author duancf
 * @version 1.0
 * @date created in 2024年04月12日 11:03
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {
        BlockQueue queue = new BlockQueue();
        Thread thread1 = new MyGetThread(queue);
        Thread thread2 = new MyPutThread(queue);
        thread1.start();
        thread2.start();

    }

    static class MyGetThread extends Thread {
        private BlockQueue queue;

        public MyGetThread(BlockQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                System.out.println(queue.get());
            }
        }
    }

    static class MyPutThread extends Thread {
        private BlockQueue queue;

        public MyPutThread(BlockQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 1000; i++) {
                System.out.println("写入队列");
                queue.put(i);
            }
        }
    }
}
