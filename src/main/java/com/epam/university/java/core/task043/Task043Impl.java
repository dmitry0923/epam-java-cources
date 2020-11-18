package com.epam.university.java.core.task043;

import java.util.HashMap;
import java.util.Map;

public class Task043Impl implements Task043 {
    private static Map<Character, String> mapForEncode = new HashMap<>();
    private static Map<String, Character> mapForDecode = new HashMap<>();

    /**
     * First of all we fill in the map for encoding and decoding
     * with symbols. Then check if symbol exists in the map, if so,
     * we get value.
     */
    public Task043Impl() {

        mapForEncode.put('a', ".-");
        mapForEncode.put('b', "-...");
        mapForEncode.put('c', "-.-.");
        mapForEncode.put('d', "-..");
        mapForEncode.put('e', ".");
        mapForEncode.put('f', "..-.");
        mapForEncode.put('g', "--.");
        mapForEncode.put('h', "....");
        mapForEncode.put('i', "..");
        mapForEncode.put('j', ".---");
        mapForEncode.put('k', "-.-");
        mapForEncode.put('l', ".-..");
        mapForEncode.put('m', "--");
        mapForEncode.put('n', "-.");
        mapForEncode.put('o', "---");
        mapForEncode.put('p', ".--.");
        mapForEncode.put('q', "--.-");
        mapForEncode.put('r', ".-.");
        mapForEncode.put('s', "...");
        mapForEncode.put('t', "-");
        mapForEncode.put('u', "..-");
        mapForEncode.put('v', "...-");
        mapForEncode.put('w', ".--");
        mapForEncode.put('x', "-..-");
        mapForEncode.put('y', "-.--");
        mapForEncode.put('z', "--..");
        mapForEncode.put(',', "--..--");
        mapForEncode.put('0', "-----");
        mapForEncode.put('1', ".----");
        mapForEncode.put('2', "..---");
        mapForEncode.put('3', "...--");
        mapForEncode.put('4', "....-");
        mapForEncode.put('5', ".....");
        mapForEncode.put('6', "-....");
        mapForEncode.put('7', "--...");
        mapForEncode.put('8', "---..");
        mapForEncode.put('9', "----.");


        mapForDecode.put(".-", 'a');
        mapForDecode.put("-...", 'b');
        mapForDecode.put("-.-.", 'c');
        mapForDecode.put("-..", 'd');
        mapForDecode.put(".", 'e');
        mapForDecode.put("..-.", 'f');
        mapForDecode.put("--.", 'g');
        mapForDecode.put("....", 'h');
        mapForDecode.put("..", 'i');
        mapForDecode.put(".---", 'j');
        mapForDecode.put("-.-", 'k');
        mapForDecode.put(".-..", 'l');
        mapForDecode.put("--", 'm');
        mapForDecode.put("-.", 'n');
        mapForDecode.put("---", 'o');
        mapForDecode.put(".--.", 'p');
        mapForDecode.put("--.-", 'q');
        mapForDecode.put(".-.", 'r');
        mapForDecode.put("...", 's');
        mapForDecode.put("-", 't');
        mapForDecode.put("..-", 'u');
        mapForDecode.put("...-", 'v');
        mapForDecode.put(".--", 'w');
        mapForDecode.put("-..-", 'x');
        mapForDecode.put("-.--", 'y');
        mapForDecode.put("--..", 'z');
        mapForDecode.put("--..--", ',');
        mapForDecode.put("-----", '0');
        mapForDecode.put(".----", '1');
        mapForDecode.put("..---", '2');
        mapForDecode.put("...--", '3');
        mapForDecode.put("....-", '4');
        mapForDecode.put(".....", '5');
        mapForDecode.put("-....", '6');
        mapForDecode.put("--...", '7');
        mapForDecode.put("---..", '8');
        mapForDecode.put("----.", '9');
    }

    @Override
    public String encode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder res = new StringBuilder();
        char[] sourceStringArray = sourceString.toCharArray();

        for (char c : sourceStringArray
        ) {
            if (mapForEncode.containsKey(Character.toLowerCase(c))) {
                res.append(mapForEncode.get(Character.toLowerCase(c)));
                res.append(" ");
            } else if (Character.isSpaceChar(c)) {
                res.append(c);
                res.append(" ");
            }
        }
        return res.toString().trim();
    }

    @Override
    public String decode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }

        String[] sourceStringArray = sourceString.split(" ");
        StringBuilder res = new StringBuilder();
        int countSpaces = 0;

        for (String s : sourceStringArray
        ) {
            if (mapForDecode.containsKey(s)) {
                res.append(mapForDecode.get(s));
                countSpaces = 0;
            } else if (countSpaces == 0) {
                res.append(" ");
                countSpaces++;
            }
        }
        return res.toString().toUpperCase();
    }
}
