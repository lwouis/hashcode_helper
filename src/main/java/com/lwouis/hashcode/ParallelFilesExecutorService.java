package com.lwouis.hashcode;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ParallelFilesExecutorService {
  public static void processInputFiles(String[] fileNames, int timeout, TimeUnit timeUnit, int nThreads,
          ProblemSolver problemSolver) {
    checkArguments(fileNames);
    ExecutorService threadPool = createThreadPool(nThreads);
    launchWorkerThreads(fileNames, problemSolver, threadPool);
    awaitWorkerThreadsTermination(timeout, timeUnit, threadPool);
  }

  private static void awaitWorkerThreadsTermination(int timeout, TimeUnit timeUnit, ExecutorService threadPool) {
    try {
      threadPool.shutdown();
      threadPool.awaitTermination(timeout, timeUnit);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void launchWorkerThreads(String[] fileNames, ProblemSolver problemSolver, ExecutorService threadPool) {
    for (String inputFilename : fileNames) {
      String outputFilename = inputFilename.replaceAll("in", "out");
      threadPool.submit(new InputWorker(inputFilename, outputFilename, problemSolver));
    }
  }

  private static void checkArguments(String[] args) {
    if (args.length < 1) {
      throw new IllegalArgumentException("No input passed as argument");
    }
    System.out.println("Running program on inputs: " + Arrays.toString(args));
  }

  private static ExecutorService createThreadPool(int nThreads) {
    ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(false).setNameFormat("InputWorker %d")
            .setUncaughtExceptionHandler((t, e) -> System.out.println("Failed to finish.\n" + e.fillInStackTrace()))
            .build();
    return Executors.newFixedThreadPool(nThreads, threadFactory);
  }
}
