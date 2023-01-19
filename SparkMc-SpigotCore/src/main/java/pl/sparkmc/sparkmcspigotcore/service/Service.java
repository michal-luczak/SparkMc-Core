package pl.sparkmc.sparkmcspigotcore.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Service {
    private static final ExecutorService service = Executors.newScheduledThreadPool(8);

    public static Future<?> submit(Runnable runnable) {
        return service.submit(runnable);
    }

    public static <V> Future<V> submit(Callable<V> callable) {
        return service.submit(callable);
    }

    public static <T> Future<T> submit(Runnable runnable, T result) {
        return service.submit(runnable, result);
    }

    public static void shutdown() {
        service.shutdown();
    }
}
