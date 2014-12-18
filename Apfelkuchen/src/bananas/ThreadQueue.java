package bananas;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadQueue<E> {

    private final BlockingQueue<Runnable> queue;
    private final ThreadPoolExecutor executor;

    public ThreadQueue() {
        queue = new LinkedBlockingQueue<Runnable>();
        this.executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, queue);
    }

    public void schedule(Runnable task) {
        executor.submit(task);
    }

    public int getThreadsInQueue() {
        return queue.size();

    }
}
