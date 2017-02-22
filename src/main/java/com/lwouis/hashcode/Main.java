package com.lwouis.hashcode;

import java.util.concurrent.TimeUnit;

public class Main {
  private static final int TIMEOUT = 30;

  private static final TimeUnit TIMEOUT_UNIT = TimeUnit.MINUTES;

  private static final int N_THREADS = 4;

  public static void main(String[] args) {
    ParallelFilesExecutorService.processInputFiles(args, TIMEOUT, TIMEOUT_UNIT, N_THREADS, new LineListProblemSolver());
  }
}