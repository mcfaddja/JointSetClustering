/*
 * Part of a clustering program for data sets consisting of non-disjoint sets.
 */
package files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Provides a file writing utility class which will write the passed array of 
 *  Strings to file with the passed filename.
 * 
 * @author Jonathan McFadden (mcfaddja@uw.edu)
 * @version 0.3
 */
public class Writer {
    
    /** String holding the filename for the output file. */
    private final String myFileName;
    
    /** String array holding the data to be written to the file. */
    private final String[] myOutput;
    
    /** Integer representing the number of lines in the String array. */
    private final int myLines;
    
    
    /**
     * Creates a writer with a method to write the data specified here to a 
     *  file with the given name.
     * 
     * @param theFileName Name of the file to write to.
     * @param theOutput String Array containing the data to be written.
     */
    public Writer(final String theFileName, final String[] theOutput) {
        myFileName = theFileName;
        
        myOutput = theOutput.clone();
        
        myLines = myOutput.length;
    }//END constructor
    
    
    /**
     * Method to do the file writing based on the data and filename passed to
     *  the constructor.
     */
    public void writeIt() {
        try {
            final FileWriter writer = new FileWriter(myFileName);
            final BufferedWriter out = new BufferedWriter(writer);
            
            for (int i = 0; i < myLines; i++) {
                final String aString = myOutput[i];
                out.write(aString);
                out.newLine();
            }//END for (INDEX i)
            
            out.close();
            writer.close();
        } catch (final IOException e) {
            System.out.println("IO output error " + e.getMessage());
        }//END try/catch BLOCK
    }//END writeIt() METHOD
    
    
}//END Writer.java CLASS
