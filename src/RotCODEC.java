/*
 *  This file is part of rotCODEC.
 *
 *  rotCODEC is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  rotCODEC is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with rotCODEC.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Utility class for coding/decoding letter or number characters in ROT13 and ROT5 respectively.
 * See <a href="http://en.wikipedia.org/wiki/ROT13">http://en.wikipedia.org/wiki/ROT13</a>.
 * @author <a href="http://www.hqcasanova.com">hqcasanova.com</a>
 * @version 13 June 2013
 */
public class RotCODEC {

    /**
     * Converts a character, be it a letter (upper or lower case) or number, to
     * a new one according to ROT13/5.
     * Based on Robert Sedgewick and Kevin Wayne's code: <a href="http://introcs.cs.princeton.edu/java/31datatype/Rot13.java.html">http://introcs.cs.princeton.edu/java/31datatype/Rot13.java.html</a>
     * @param iChar ASCII code of character to code/decode.
     * @return Converted character.
     */
    public static char rotten(char iChar) {
        //ROT13
        if      (iChar >= 'a' && iChar <= 'm') iChar += 13;
        else if (iChar >= 'A' && iChar <= 'M') iChar += 13;
        else if (iChar >= 'n' && iChar <= 'z') iChar -= 13;
        else if (iChar >= 'N' && iChar <= 'Z') iChar -= 13;

        //ROT5
        else if (iChar >= '0' && iChar <= '4') iChar += 5;
        else if (iChar >= '4' && iChar <= '9') iChar -= 5;
        return iChar;
    }

    /**
     * The string version of the above method: traverses the whole string to
     * convert it character by character. If the string is only 1 character in length,
     * traversing is avoided.
     * @param sText String to encode/decode
     * @return Converted string.
     */
    public static String rotten(String sText) {
        char[] characters;  //translated characters

        if (sText.length() > 1) {
            characters = new char[sText.length()];
            for (int i = 0; i < sText.length(); i++)
                characters[i] = rotten(sText.charAt(i));
            return new String(characters);
        } else {
            return Character.toString(rotten(sText.charAt(0)));
        }
    }
}
