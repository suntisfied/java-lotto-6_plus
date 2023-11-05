package lotto.winning;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.Settings.MINIMUM;
import static lotto.Settings.MAXIMUM;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lotto.Askable;

public class BonusNumber implements Askable {
    @Override
    public Integer ask() {
        System.out.println(inputBonusNumber);

        String input;
        List<Integer> convertedInput;
        do {
            input = readLine();
            convertedInput = convertInput(input);

        } while (!validate(convertedInput));

        System.out.println();

        return convertedInput.get(0);
    }

    private boolean validate(List<Integer> convertedInput) {
        boolean corretInput = true;

        try {
            if (!isCorrectRange.test(convertedInput)) {
                throw new IllegalArgumentException(numberRangeError);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            corretInput = false;
        }

        return corretInput;
    }

    private List<Integer> convertInput(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private final Predicate<List<Integer>> isCorrectRange = input ->
            input.stream().mapToInt(Integer::intValue).min().getAsInt() >= MINIMUM.getNumber() &&
                    input.stream().mapToInt(Integer::intValue).max().getAsInt() <= MAXIMUM.getNumber();
}
