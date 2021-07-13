package chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ExecuteAround {

  private static final String FILE = Objects
      .requireNonNull(ExecuteAround.class.getResource("./data.txt")).getFile();
//  private static final String FILE = ExecuteAround.class.getResource("chaper03/data.txt").getFile();
//  private static final String FILE = ExecuteAround.class.getResource("resources/chaper03/data.txt").getFile();
//  private static final String FILE = ExecuteAround.class.getResource("/Users/boki/Documents/modern java in action/src/main/resources/chaper03/data.txt").getFile();

  public static void main(String... args) throws IOException {
    // 더 유연하게 리팩토링할 메서드
    String result = processFileLimited();
    System.out.println(result);

    System.out.println("---");

    String oneLine = processFile((BufferedReader b) -> b.readLine());
    System.out.println(oneLine);

    String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
    System.out.println(twoLines);
  }

  public static String processFileLimited() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return br.readLine();
    }
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
      return p.process(br);
    }
  }

  public interface BufferedReaderProcessor {

    String process(BufferedReader b) throws IOException;

  }

}