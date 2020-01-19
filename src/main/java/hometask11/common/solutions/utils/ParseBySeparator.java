package hometask11.common.solutions.utils;

import java.util.ArrayList;
import java.util.List;

public class ParseBySeparator {
    private ParseBySeparator(){

    }

    public static List<String> getSeparated(String line, char Separator) {
        List<String> answer = new ArrayList<>();
        StringBuilder oneValue = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if (symbol != Separator) {
                oneValue.append(symbol);
            }
            else {
                answer.add(oneValue.toString());
                oneValue = new StringBuilder();
            }
        }
        return answer;
    }
}
