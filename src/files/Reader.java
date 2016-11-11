/*
 * Part of a clustering program for data sets consisting of non-disjoint sets.
 */

package files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


/**
 * Provides a file reading utility class which will read input files containing
 * a single word or N-gram per line.
 * 
 * @author Jonathan McFadden (mcfaddja@uw.edu)
 * @version 0.1
 */
public class Reader {

    /** String holding the name of the file to read from. */
    private final String myFileName;

    /** An ArrayList of Strings containing the input from the file. */
    private final List<String> myInput;

    /**
     * Creates a reader with methods to read the file specified by the passed
     * filename as well as to return the data read from the file in the form of
     * a String array.
     * 
     * @param theFileName The filename of the file to be read.
     */
    public Reader(final String theFileName) {
        myFileName = theFileName;

        myInput = new ArrayList<String>();
    } // END constructor

    /**
     * Method to read in the file specified by the filename passed to the
     * constructor.
     */
    public void readIt() {
        try {
            final FileReader aReader;
            aReader = new FileReader(myFileName);
            final BufferedReader in = new BufferedReader(aReader);

            String line = in.readLine();
            while (line != null) {
                myInput.add(line);
                line = in.readLine();
            } // END while LOOP

            in.close();
            aReader.close();
        } catch (final IOException e) {
            System.out.println("IO input error" + e.getMessage());
        } // END try/catch BLOCK
    } // END readIt() METHOD

    /**
     * Method to return the data read from the file by the readIt() method, this
     * method requires that the readIt() method is called first.
     * 
     * @return aStrings String array created from the ArrayList of Strings that
     *         contains the input read from the file.
     */
    public String[] getIt() {
        String[] aStrings = new String[1];
        aStrings = myInput.toArray(aStrings);

        return aStrings;
    } // END getIt() METHOD

    
} // END Reader.java CLASS
