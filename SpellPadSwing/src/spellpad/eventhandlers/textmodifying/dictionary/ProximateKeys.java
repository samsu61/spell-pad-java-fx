package spellpad.eventhandlers.textmodifying.dictionary;

import java.util.HashMap;

/**
 *
 * @author Jesse Allen
 */
public class ProximateKeys {

    private HashMap<Character, char[]> charMap;

    private ProximateKeys() {
        charMap = new HashMap<>();
    }

    public static ProximateKeys getInstance() {
        ProximateKeys keys = new ProximateKeys();
        keys.charMap.put('q', new char[]{'a', 'w'});
        keys.charMap.put('w', new char[]{'q', 'a', 's', 'e'});
        keys.charMap.put('e', new char[]{'w', 's', 'd', 'r'});
        keys.charMap.put('r', new char[]{'e', 'd', 'f', 'r'});
        keys.charMap.put('t', new char[]{'r', 'f', 'g', 'y'});
        keys.charMap.put('y', new char[]{'t', 'g', 'h', 'u'});
        keys.charMap.put('u', new char[]{'y', 'h', 'j', 'i'});
        keys.charMap.put('i', new char[]{'u', 'j', 'k', 'o'});
        keys.charMap.put('o', new char[]{'i', 'k', 'l', 'p'});
        keys.charMap.put('p', new char[]{'o', 'l'});
        keys.charMap.put('a', new char[]{'q', 'w', 's', 'z'});
        keys.charMap.put('s', new char[]{'q', 'w', 'e', 'd', 'x', 'z', 'a'});
        keys.charMap.put('d', new char[]{'e', 'r', 'f', 'c', 'x', 's'});
        keys.charMap.put('f', new char[]{'r', 't', 'g', 'v', 'c', 'd'});
        keys.charMap.put('g', new char[]{'t', 'y', 'h', 'b', 'v', 'f'});
        keys.charMap.put('h', new char[]{'y', 'u', 'j', 'n', 'b', 'g'});
        keys.charMap.put('j', new char[]{'u', 'i', 'k', 'm', 'n', 'h'});
        keys.charMap.put('k', new char[]{'i', 'o', 'l', 'm', 'j'});
        keys.charMap.put('l', new char[]{'o', 'p', 'k'});
        keys.charMap.put('z', new char[]{'a', 's', 'x'});
        keys.charMap.put('x', new char[]{'z', 's', 'd', 'c'});
        keys.charMap.put('c', new char[]{'x', 'd', 'f', 'v'});
        keys.charMap.put('v', new char[]{'c', 'f', 'g', 'b'});
        keys.charMap.put('b', new char[]{'v', 'g', 'h', 'n'});
        keys.charMap.put('n', new char[]{'b', 'h', 'j', 'm'});
        keys.charMap.put('m', new char[]{'n', 'j', 'k'});

        return keys;
    }

    public char[] getProximate(char c) {
        return charMap.get(c);
    }
}
