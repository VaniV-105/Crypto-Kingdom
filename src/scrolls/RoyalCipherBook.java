package scrolls;

public class RoyalCipherBook {

    public static String handle(RoyalScroll scroll) throws Exception {
        CipherType algorithm = scroll.getAlgorithm();
        ScrollType operation = scroll.getOperation();
        String data = scroll.getData();
        String key = scroll.getKey();

        switch (algorithm) {
            case CAESAR:
                int shift = Integer.parseInt(key);
                return (operation == ScrollType.ENCRYPT)
                        ? encryptCaesar(data, shift)
                        : decryptCaesar(data, shift);
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
    }

    private static String encryptCaesar(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append((char) ('A' + (c - 'A' + shift) % 26));
            } else if (Character.isLowerCase(c)) {
                result.append((char) ('a' + (c - 'a' + shift) % 26));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static String decryptCaesar(String text, int shift) {
        return encryptCaesar(text, 26 - (shift % 26));
    }
}
