/*
 * Part of a clustering program for data sets consisting of non-disjoint sets.
 */
package matrices;

/**
 * Class to compute the transpose of a matrix.
 * 
 * @author Jonathan McFadden (mcfaddja@uw.edu)
 * @version 0.2
 */
public class MatTranspose {
   
    /** A 2D integer array holding the values of the input matrix. */
    private final int[][] myLin;
    
    /** Integer holding the number of rows in the input matrix. */
    private final int myI;
    
    /** Integer holding the number of columns in the input matrix. */
    private final int myJ;
    
    /** A 2D integer array holding the transposed values of the input matrix. */
    private final int[][] myLout;
    
    
    /**
     * Allows the passed matrix to be transposed.
     * 
     * @param theLin A 2D integer array holding the values of the input matrix.
     */
    public MatTranspose(final int[][] theLin) {
        myLin = theLin.clone();
        
        myI = theLin.length;
        myJ = theLin[0].length;
        
        myLout = new int[myI][myJ];
    }//END constructor
    
    
    /**
     * Method to compute the Transpose of the matrix passed to the constructor.
     */
    public void computeLt() {
        for (int i = 0; i < myI; i++) {
            for (int j = 0; j < myJ; j++) {
                myLout[i][j] = myLin[j][i];
            }//END for (INDEX j)
        }//END for (INDEX i)    
    }//END computeLt() METHOD
    
    /**
     * Method to compute the transpose of the matrix passed in the constructor 
     *  and then return the result in another 2D integer array.
     * 
     * @return aLout A 2D integer array containing the values of the Lt matrix.
     */
    public int[][] getLt() {
        this.computeLt();
        final int[][] aLout = myLout.clone();
        
        return aLout;
    }//END getLt() METHOD
    
}//END MatTranspose.java CLASS
