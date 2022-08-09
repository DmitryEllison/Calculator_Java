import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}

public class main {

    static String[] arabic = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[] roman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};


    public static String arabicToRoman(int number) {
        if ((number <= 0)|| (number > 4000)) {
            throw new IllegalArgumentException("throws Exception //т.к. в римской системе нет отрицательных чисел");
        }

        List romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = (RomanNumeral) romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }



    public static void main(String[] args) throws Exception {

        System.out.println("Введите выражение:");
        Scanner scanner = new Scanner(System.in);
        String expr = scanner.nextLine();

        String[] s = expr.split(" ");

        if (s.length != 3)
            throw new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");


        Integer res = 0; Integer a; Integer b; Integer[] c = check(s[0], s[2]);

        a = c[0];
        b = c[1];

        switch(s[1]) {
            case ("+"):
                res = a + b;
                break;
            case ("-"):
                res = a - b;
                break;
            case ("*"):
                res = a * b;
                break;
            case ("/"):
                res = a / b;
                break;

            default:
                System.out.println("throws Exception //т.к. строка не является математической операцией");

        }

        if (c[2].equals(2)) {
            System.out.print(expr + " = " + arabicToRoman(res));
        }
        else {

            System.out.print(expr + " = " + res);
            //System.out.format("%.0f",res);
        }


    }

    public static Integer[] check(String a, String b) throws Exception {
        /**
         * определяем a и b, выбрасываем исключения из допустимых значений, возвращаем массив из a и b + конвертируем римские
         */
        Integer flag_a = 0; Integer fin_a = 0;
        Integer flag_b = 0; Integer fin_b = 0;
        for( int i = 0; i < 10; i++) {
            if(a.equals(arabic[i])) {
                flag_a = 1;
                fin_a = i + 1;
            }

            if(a.equals(roman[i])) {
                flag_a = 2;
                fin_a = i + 1;
            }

            if(b.equals(arabic[i])) {
                flag_b = 1;
                fin_b = i + 1;
            }

            if(b.equals(roman[i])) {
                flag_b = 2;
                fin_b = i + 1;
            }
        }
        if(flag_a == 0 || flag_b == 0)
            throw new Exception("");

        if(flag_a != flag_b)
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");

        return new Integer[] {fin_a, fin_b, flag_a};
    }

}
