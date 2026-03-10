package bt6;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<List<String>> data = List.of(
                List.of("A","B","C"),
                List.of("D","E"),
                List.of("F","G","H")
        );

        data.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);
    }
}