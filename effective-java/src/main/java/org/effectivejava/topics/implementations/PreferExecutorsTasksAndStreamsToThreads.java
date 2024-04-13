package org.effectivejava.topics.implementations;

import com.google.auto.service.AutoService;
import org.abstractions.Item;
import org.effectivejava.topics.helpers.EjChapter;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@AutoService(Item.class)
public class PreferExecutorsTasksAndStreamsToThreads implements Item {
    @Override
    public EjChapter getChapter() {
        return EjChapter.CHAPTER_11;
    }

    @Override
    public String getTheme() {
        return "Prefer executors, tasks, and streams to threads";
    }

    @Override
    public List<String> getBulletPoints() {
        return List.of(
                "executor framework is flexible interface-based task execution facility",
                "Executors have static factories for providing most of the executors you will ever need",
                "ot you can use class ThreadPoolExecutor directly, it lets you configure every aspect of a thread " +
                        "pool's operation",
                "for small or with light load service - newCachedThreadPool is generally a good choice",
                "in a cached thread pool, submitted tasks are not queued that immediately handed off to a thread for " +
                        "execution; if no threads are available - new one is created",
                "for highly loaded service - it is better to use newFixedThreadPool",
                "you should refrain from using Thread; because it consolidates both: unit of work and the execution " +
                        "mechanism",
                """
                    execution framework separates these concepts into:
                        task as unit of a work: can be Runnable or Callable, which can return value or throw exception
                        mechanism for execution tasks is executor service""",
                "in Java 7 fork-join pool was added, it provides ForkJoinTask type of tasks, which can be split into " +
                        "smaller subtasks, and other threads can steal those tasks to ensure full resource utilization",
                "parallel streams are written atop for join pools",
                "further reading - Java Concurrency in Practice"
        );
    }

    @Override
    public void runExamples() {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Runnable task = () -> System.out.println("task executed");
        Future<?> submit = exec.submit(task);
        try {
            Object o = submit.get();
            System.out.println(o);
        } catch (InterruptedException | ExecutionException ignored) {}
        exec.shutdown();
    }

    @Override
    public List<Class<?>> examplesInCode() {
        return List.of(

        );
    }
}
