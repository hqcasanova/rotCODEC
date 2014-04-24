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

import java.awt.Toolkit;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Command line interface for coding/decoding ROT13 and ROT5. It allows two modes of operation: <ul>
 * <li>Asynchronous: employs a text file created previously by the user and specified through the command line.</li>
 * <li>Synchronous: as the user types the text into a window, the characters are encoded/decoded on
 * the fly.</li></ul>
 * @author <a href="http://www.hqcasanova.com">hqcasanova.com</a>
 * @version 8 February 2014
 */
public class RotCLI {
    public final static String sAppVer = "0.15";
    public final static String sAppName = "rotCODEC";
    public final static String sCloseErr = "The stream could not be closed: ";
    public final static String sNotFoundErr = "The input file specified could not be found: ";
    public final static String sIOErr = "The file could not be accessed: ";
    public final static String sSyncMode = "sync";
    public final static String sBriefHelp = "Version " + sAppVer + ". Usage: " + sAppName + " [list_of_files | " + sSyncMode + "]";
    public final static String sWinIcon = "/resources/logo.png";

    /**
     * Releases resources associated with a given stream.
     * @param stream Stream to close
     */
    private static void closeStream(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException io) {
            System.out.println(sCloseErr + io);
            io.printStackTrace();
        }
    }

    /**
     * Main entry point. Accepts up to two arguments at the command line:<ul>
     * <li><b>2 arguments</b>: list of two files. First one with text to code/decode, second one where the result is saved.</li>
     * <li><b>1 argument</b>: the name of the sync mode ("sync" by default). Launches the GUI.</li>
     * <li><b>None</b>: sync mode assumed. Launches GUI.</li>
     * </ul>
     */
    public static void main(String[] args) {

        //Asynchronous mode: use file streams to code/decode text on file char by char
        if (args.length == 2) {
            InputStreamReader toTranslate = null;   //character stream from text file to code/decode
            OutputStreamWriter translated = null;   //character stream to file where the result is saved
            int iChar2Translate = -1;               //ASCII of stream character to code/decode

    	    try {
	            //Set up the character streams
	            toTranslate = new InputStreamReader(new FileInputStream(args[0]));
	            translated = new OutputStreamWriter(new FileOutputStream(args[1]));

   	            //Read one character at a time: code/decode it and write to file
                iChar2Translate = toTranslate.read();
                while (iChar2Translate >= 0) {
                    translated.write (RotCODEC.rotten((char) iChar2Translate));
                    iChar2Translate = toTranslate.read();
                }
            } catch (FileNotFoundException notFound) {
                System.out.println(sNotFoundErr + notFound);
                notFound.printStackTrace();
            } catch (IOException io) {
                System.out.println(sIOErr + io);
                io.printStackTrace();

            //Done with files
            } finally {
                closeStream(toTranslate);
                closeStream(translated);
            }

        //Synchronous mode: launch window into which text to be coded/decoded is typed
        } else if ((args.length == 0) || ((args.length == 1) && (args[0].equals(sSyncMode)))) {
            JFrame window;

            window = new JFrame(sAppName + " " + sAppVer);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setIconImage(Toolkit.getDefaultToolkit().getImage (RotCLI.class.getResource(sWinIcon)));
            window.getContentPane().add(new JScrollPane(new RotTextArea()));
            window.pack();
            window.setVisible(true);

        //Wrong arguments. Shows a brief help message.
        } else {
            System.out.println(sBriefHelp);
        }
    }
}