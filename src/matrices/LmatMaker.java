/*
 * Part of a clustering program for data sets consisting of non-disjoint sets.
 */
package matrices;

/**
 * Class with methods to create a matrix, L, that represents the connections 
 *  between the words and N-grams in the passed String arrays.
 * 
 * @author Jonathan McFadden (mcfaddja@uw.edu)
 * @version 0.1
 */
public class LmatMaker {
    
    /** String array to store the words. */
    private final String[] myWords;
    
    /** String array to store the N-grams. */
    private final String[] myNgrams;
    
    /** Integer representing the number of words. */
    private final int myWordCnt;
    
    /** Integer representing the number of N-grams. */
    private final int myNgramCnt;
    
    /** Two dimensional integer array to store the values for the L matrix. */
    private final int[][] myL;
    
    
    /**
     * Specifies the String arrays to use for the words and N-grams, clones 
     *  those arrays (defensive copy), stores their size, and initializes the 
     *  array for the L matrix based on those sizes.
     * 
     * @param theWords String array containing the words to use.
     * @param theNgrams String array containing the N-grams to use.
     */
    public LmatMaker(final String[] theWords, final String[] theNgrams) {
        myWords = theWords;
        myNgrams = theNgrams;
        
        myWordCnt = myWords.length;
        myNgramCnt = myNgrams.length;
        
        myL = new int[myWordCnt][myNgramCnt];
        for (int i = 0; i < myWordCnt; i++) {
            for (int j = 0; j < myNgramCnt; j++) {
                myL[i][j] = 0;
            }//END for (INDEX j)
        }//END for (INDEX i)
    } //END constructor
    
    
    /**
     * Method to compute the L matrix.
     */
    public void computeL() {
        for (int i = 0; i < myWordCnt; i++) {
            String aWord;
            aWord = myWords[i];
            final int aWordSize = aWord.length();
            
            for (int j = 0; j < myNgramCnt; j++) {
                final String aNgram = myNgrams[j];
                final int aNgramSize = aNgram.length();
                
                if (aWord.contains(aNgram)) {
                    int aPos = 0;
                    
                    while (aPos <= aWordSize) {
//                        aPos = aWord.indexOf(aNgram, aPos) + aWordSize;
                        aPos = aWord.indexOf(aNgram, aPos);
                        myL[i][j]++;
                    }//END while LOOP
                }//END if
                
            }//END for (INDEX j)
            
        }//END for (INDEX i)
        
    } //END computeL() METHOD
    
    /** Method to return the computed values for the L matrix in the form of 
     *   a two dimensional integer array. Calls the computeL() method from 
     *   within this class.
     * 
     * @return anL A two dimensional integer array containing the computed 
     *  values for the L matrix.
     */
    public int[][] getL() {
        this.computeL();
        
        final int[][] anL = new int[myWordCnt][myNgramCnt];
        
        for (int i = 0; i < myWordCnt; i++) {
            System.arraycopy(myL[i], 0, anL[i], 0, myNgramCnt);
        }//END for (INDEX i)
        
        return anL;
    } //END getL() METHOD
    
} //END LmatMaker CLASS
