//author: Rahimjon Hakimov

public class SumLong {
    public static void main(String[] args) {
        long numbersSum = 0;
        for (int i = 0; i < args.length; i++) {
            String currentStringNumber = "";
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.isDigit(args[i].charAt(j)) || args[i].charAt(j) == '-') {
                    currentStringNumber += args[i].charAt(j);
                }
                if (Character.isWhitespace(args[i].charAt(j))) {
                    if (currentStringNumber.length() > 0) {
                        numbersSum += Long.parseLong(currentStringNumber);
                        //System.err.println(currentStringNumber);
                        currentStringNumber = "";
                    }
                }
            }
            if (currentStringNumber.length() > 0) {
                numbersSum += Long.parseLong(currentStringNumber);
            }
        }
        System.out.println(numbersSum);
    }
}


