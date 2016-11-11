/*
 * Part of a clustering program for data sets consisting of non-disjoint sets.
 */
package jointsetclustering;

import java.io.IOException;

/**
 * Utility class with the Main method used to read filenames for the words, 
 *  n-grams, L matrix, and LLt matrix files from the command line, then pass 
 *  these values to and run the Joint Set Clustering utility class. 
 * 
 * @author Jonathan McFadden (mcfaddja@uw.edu)
 * @version 0.4
 */
public class JointSetClusteringMain {

    /** Private constructor to prevent instantiation or sub-classing. */
    private JointSetClusteringMain() {
        // No instantiation.
    }//END constructor
    
    
    /**
     * Main method for calling and running the Joint Set Clustering utility 
     *  class in addition to retrieving the four String holding the requisite 
     *  filenames from the command line.
     * 
     * @param theArgs Command line arguments giving the names of the files 
     *  with the words and n-grams to read followed by the files where the 
     *  resulting L and LLt matrices are to be written.  The order of the 
     *  filenames is words file, n-grams file, L matrix file, LLt matrix file.
     * 
     * @throws java.io.IOException
     */
    public static void main(final String[] theArgs) throws IOException {
        String[] someArgs;
        someArgs = theArgs.clone();
        
        final JointSetClustering aCluster;
        aCluster = new JointSetClustering(someArgs);
        
        aCluster.runThis();
    }//END main METHOD
    
}//END JointSetClusteringMain.java CLASS
