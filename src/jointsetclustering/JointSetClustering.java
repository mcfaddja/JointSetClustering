/*
 * Part of a clustering program for data sets consisting of non-disjoint sets.
 */
package jointsetclustering;

import files.Reader;
import files.Writer;
import java.io.IOException;
import matrices.LmatMaker;
import matrices.MatTranspose;
import matrices.MatrixMult;
import java.lang.StringBuilder;

/**
 * Utility class used to run the various parts required for the Joint Set 
 *  Clustering.
 * 
 * @author Jonathan McFadden (mcfaddja@uw.edu)
 * @version 0.4
 */
public class JointSetClustering {
    
    /** Constant for the index of the words file's filename. */
    private static final int WORDS_FILE_IND = 0;
    
    /** Constant for the index of the n-grams file's filename. */
    private static final int NGRAMS_FILE_IND = 1;
    
    /** Constant for the index of the L matrix file's filename. */
    private static final int LMAT_FILE_IND = 2;
    
    /** Constant for the index of the LLt matrix file's filename. */
    private static final int LLT_FILE_IND = 3;
    
    /** A String array to hold the various filenames. */
    private final String[] myFileNames;
    
    /** String array to hold the words read from the words file. */
    private String[] myWords;
    
    /** String array to hold the n-grams read from the n-grams file. */
    private String[] myNgrams;
    
    /** Two dimensional integer array to hold the values of the L matrix. */
    private int[][] myL;
    
    /** Two dimensional integer array to hold the values of the LLt matrix. */
    private int[][] myLLt;
    
    
    /**
     * Runs all of the required routines in the proper order to read from the 
     *  specified input files, compute the L matrix, compute the LLt matrix, 
     *  and write the results to the specified output files.
     * 
     * @param theFileNames String array containing the file names to use for 
     *  input and output.
     * 
     * @throws java.io.IOException
     */
    public JointSetClustering(final String[] theFileNames) throws IOException {
        myFileNames = theFileNames.clone();
        
        myWords = new String[1];
        myNgrams = new String[1];
        myL = new int[1][1];
        myLLt = new int[1][1];
    }//END constructor
    
    
    /**
     * Method to run all of the subroutines in the proper order using the 
     *  filenames specified in the constructor.
     * 
     * @throws java.io.IOException
     */
    public void runThis() throws IOException {
        myWords = getWords().clone();
        myNgrams = getNgrams().clone();
        
        myL = getL().clone();
        myLLt = getLLt().clone();
        
        writeLmat();
        writeLLt();
    }//END runThis() METHOD
    
    
    /** 
     * Private helper to read the words file into a String array.
     * 
     * @return someStrings Array of strings containing the words read in from 
     *  the specified words file.
     */
    private String[] getWords() throws IOException {
        final String aFileName = myFileNames[WORDS_FILE_IND];
        
        final Reader read = new Reader(aFileName);
        read.readIt();
        
        final String[] someStrings = read.getIt().clone();
        
        return someStrings;
    }//END getWords() METOHD
    
    /** 
     * Private helper to read the n-grams file into a String array.
     * 
     * @return someStrings Array of strings containing the n-grams read in from 
     *  the specified words file.
     */
    private String[] getNgrams() throws IOException {
        final String aFileName = myFileNames[NGRAMS_FILE_IND];
        
        final Reader read = new Reader(aFileName);
        read.readIt();
        
        final String[] someStrings = read.getIt().clone();
        
        return someStrings;
    }//END getNgrams() METOHD

    /**
     * Private helper to compute values for the L matrix based on the words 
     *  and n-grams previously read in.
     * 
     * @return anL Two dimensional integer array containing the values for the 
     *  L matrix.
     */
    private int[][] getL() {
        final LmatMaker maker = new LmatMaker(myWords, myNgrams);
        
        final int[][] anL;
        anL = maker.getL().clone();
        
        return anL;
    }//END getL() METHOD
    
    /**
     * Private helper to compute values for the LLt matrix based on the 
     *  previously computed values of the L matrix.
     * 
     * @return anL Two dimensional integer array containing the values for the 
     *  LLt matrix.
     */
    private int[][] getLLt() {
        final MatTranspose matT = new MatTranspose(myL);
        
        final int[][] aLt;
        aLt = matT.getLt().clone();
        
        final MatrixMult aMult = new MatrixMult(myL, aLt);
        
        final int[][] aCmat;
        aCmat = aMult.getC();
        
        return aCmat;
    }//END getLLt() METHOD
    
    /** 
     * Private helper to convert the 2D integer array of L matrix values into 
     *  a 1D String array.
     * 
     * @return vals A String array constructed from the integer array holding 
     *  the values of the L matrix.
     */
    private String[] convertLmat() {
        final int nRows = myL.length;
        final int nCols = myL[0].length;
        
        String[] vals = new String[nRows];
        for (int i = 0; i < nRows; i++) {
            StringBuilder aBuilder = new StringBuilder();
            
            for (int j = 0; j < nCols; j++) {
                aBuilder.append(myL[i][j]);
                aBuilder.append(' ');
            }//END for (INDEX j)
            
            vals[i] = aBuilder.toString();
        }//END for (INDEX i)
        
        return vals;
    }//END convertLmat() METHOD
    
    /** 
     * Private helper write the String array with the values of the L matrix  
     *  to the file specified in the constructor.
     */
    private void writeLmat() throws IOException {
        final String[] vals;
        vals = this.convertLmat().clone();
        
        final String aFileName = myFileNames[LMAT_FILE_IND];
        final Writer writes = new Writer(aFileName, vals);
        writes.writeIt();
    }//END writeLmat() METHOD
    
    /** 
     * Private helper to convert the 2D integer array of LLt matrix values into 
     *  a 1D String array.
     * 
     * @return vals A String array constructed from the integer array holding 
     *  the values of the LLt matrix.
     */
    private String[] convertLLt() {
        final int nRows = myLLt.length;
        final int nCols = myLLt[0].length;
        
        String[] vals = new String[nRows];
        for (int i = 0; i < nRows; i++) {
            StringBuilder aBuilder = new StringBuilder();
            
            for (int j = 0; j < nCols; j++) {
                aBuilder.append(myLLt[i][j]);
                aBuilder.append(' ');
            }//END for (INDEX j)
            
            vals[i] = aBuilder.toString();
        }//END for (INDEX i)
        
        return vals;
    }//END convertLLt() METHOD
    
    /** 
     * Private helper write the String array with the values of the LLt matrix 
     *  to the file specified in the constructor.
     */
    private void writeLLt() throws IOException { 
        final String[] vals;
        vals = this.convertLLt().clone();
        
        final String aFileName = myFileNames[LLT_FILE_IND];
        final Writer writes = new Writer(aFileName, vals);
        writes.writeIt();
    }//END writeLLt() METHOD

    
}//END JointSetClustering.java CLASS
