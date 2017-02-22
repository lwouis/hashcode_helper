package com.lwouis.hashcode;

public class InputWorker implements Runnable {

  private final String inputFilename;

  private final String outputFilename;

  private final ProblemSolver problemSolver;

  public InputWorker(String inputFilename, String outputFilename, ProblemSolver problemSolver) {
    this.inputFilename = inputFilename;
    this.outputFilename = outputFilename;
    this.problemSolver = problemSolver;
  }

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();
    System.out.println(threadName + ": started solving '" + inputFilename + "'");
    problemSolver.solve(inputFilename, outputFilename);
    System.out.println(threadName + ": finished solving '" + inputFilename + "' in '" + outputFilename + "'");
  }
}