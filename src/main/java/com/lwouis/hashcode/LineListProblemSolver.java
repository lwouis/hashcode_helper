package com.lwouis.hashcode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class LineListProblemSolver implements ProblemSolver {

  @Override
  public void solve(String inputFile, String outputFile) {
    try {
      List<String> inputLines = readInput(inputFile);
      List<String> outputLines = solveInternal(inputLines);
      writeOutput(outputFile, outputLines);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  private List<String> solveInternal(List<String> inputLines) {
    return new ArrayList<>();
  }

  private List<String> readInput(String inputFilename) throws IOException {
    return Files.readAllLines(Paths.get(inputFilename));
  }

  private void writeOutput(String outputFilename, List<String> lines) throws IOException {
    Path filePath = Paths.get(outputFilename);
    Path parentDir = filePath.getParent();
    if (parentDir != null) {
      Files.createDirectories(parentDir);
    }
    Files.write(filePath, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
            StandardOpenOption.TRUNCATE_EXISTING);
  }
}
