package com.CrossingGuardJoe.viewer.images.Font;

public class FontImageFactory {
    public FontImageFactory() {}

    public String[] getImageRepresentation(Object input) {
        if (input instanceof String) {
            return getTextImage((String) input);
        } else if (input instanceof Integer || input instanceof Long || input instanceof Short) {
            return getNumberImage((int) input);
        } else {
            throw new IllegalArgumentException("Unsupported input type");
        }
    }

    private String[] getTextImage(String text) {
        StringBuilder[] result = new StringBuilder[16];

        for (int i = 0; i < result.length; i++) {
            result[i] = new StringBuilder();
        }

        for (Character character : text.toCharArray()) {
            String[] characterToAppend;
            if (character == ' ') {
                characterToAppend = FontImages.SPACE;
            } else {
                characterToAppend = new String[16];
                int characterIndex = getAlphabetIndex(character);
                for (int i = characterIndex * 16; i < (characterIndex + 1) * 16; i++) {
                    String line = FontImages.ALPHABET[i % FontImages.ALPHABET.length];
                    characterToAppend[i - characterIndex * 16] = line;
                }
            }

            for (int i = 0; i < characterToAppend.length; i++) {
                result[i].append(characterToAppend[i]);
            }
        }

        String[] output = new String[result.length];
        for (int i = 0; i < result.length; i++) {
            output[i] = result[i].toString();
        }

        return output;
    }

    public String[] getNumberImage(int number) {
        String numberString = Integer.toString(number);

        StringBuilder[] result = new StringBuilder[16];

        for (int i = 0; i < result.length; i++) {
            result[i] = new StringBuilder();
        }

        for (Character character : numberString.toCharArray()) {
            String[] characterToAppend;

            characterToAppend = new String[16];
            int characterIndex = Character.getNumericValue(character);
            for (int i = characterIndex * 16; i < (characterIndex + 1) * 16; i++) {
                String line = FontImages.NUMBERS[i % FontImages.NUMBERS.length];
                characterToAppend[i - characterIndex * 16] = line;
            }

            for (int i = 0; i < characterToAppend.length; i++) {
                result[i].append(characterToAppend[i]);
            }
        }

        String[] output = new String[result.length];
        for (int i = 0; i < result.length; i++) {
            output[i] = result[i].toString();
        }

        return output;
    }

    private int getAlphabetIndex(char c) {
        if (Character.isLetter(c)) {
            c = Character.toUpperCase(c);
        }
        return c - 'A';
    }
}