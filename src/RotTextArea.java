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

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Sets up a Swing text area with a customised document filter. The latter replaces every typed
 * character according to the ROT13/5 cipher.
 * @author <a href="http://www.hqcasanova.com">hqcasanova.com</a>
 * @version 7 February 2014
 */
@SuppressWarnings("serial")
public class RotTextArea extends JTextArea {
    public final static String posErr = "Wrong position within the input field: ";
    public final static String defText = "Type or paste the text into this area. The program will cipher/decipher it on the fly.";
    public final static int defRows = 10;
    public final static int defCols = 80;

    /**
     * Customised constructor. Sets the size of the text area and changes its document filter.
     * @param noRows Number of rows
     * @param noCols Number of columns
     */
    public RotTextArea(String hintText, int noRows, int noCols) {
        super(noRows, noCols);

        //Show an inline tip
        TextPrompt hint = new TextPrompt(hintText, this);
        hint.changeAlpha(0.5f);
        hint.changeStyle(Font.BOLD);
        hint.setHorizontalAlignment(JLabel.CENTER);

        ((AbstractDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {
              public void insertString(DocumentFilter.FilterBypass bypass, int iOffset,
                                       String sText, AttributeSet attrib) throws BadLocationException {
                  bypass.insertString(iOffset, RotCODEC.rotten(sText), attrib);
              }

              public void replace(DocumentFilter.FilterBypass bypass, int iOffset, int iLength,
                                  String sText, AttributeSet attrib) throws BadLocationException {
                  bypass.replace(iOffset, iLength, RotCODEC.rotten(sText), attrib);
              }
        });
    }

    /**
     * Default constructor. Synonym for the above with default size values.
     */
    public RotTextArea() {
        this(defText, defRows, defCols);
    }
}