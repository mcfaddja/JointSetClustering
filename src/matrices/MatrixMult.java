/*
 * Part of a clustering program for data sets consisting of non-disjoint sets.
 */
package matrices;

/**
 * Class to provide methods to test if two matrices can be multiplied based on 
 *  their order and dimensions, multiply any matrices that pass that test, and 
 *  then return the result of that multiplication as a 2D integer array.
 * 
 * @author Jonathan McFadden (mcfaddja@uw.edu)
 * @version 0.2
 */
public class MatrixMult {
   
    /** A 2D integer array holding the values of the first matrix. */
    private final int[][] myA;
    
    /** A 2D integer array holding the values of the second matrix. */
    private final int[][] myB;
    
    /** Integer representing the number of rows in the first matrix. */
    private final int myAi;
    
    /** Integer representing the number of columns in the first matrix. */
    private final int myAj;
    
    /** Integer representing the number of rows in the second matrix. */
    private final int myBi;
    
    /** Integer representing the number of columns in the second matrix. */
    private final int myBj;
    
    /** Integer representing the number of rows in the product matrix. */
    private final int myCi;
    
    /** Integer representing the number of columns in the product matrix. */
    private final int myCj;
    
    /** A 2D integer array holding the values of the product matrix. */
    private final int[][] myC;
    
    
    /**
     * Creates a matrix multiplier to test and, if possible, multiply the 
     *  passed matrices in the specified order, and return the results.
     * 
     * @param theA A 2D integer array holding the values of the first matrix in 
     *  the product.
     * @param theB A 2D integer array holding the values of the second matrix 
     *  in the product.
     */
    public MatrixMult(final int[][] theA, final int[][] theB) {
        myA = theA.clone();
        myB = theB.clone();
        
        myAi = myA.length;
        myAj = myA[0].length;
        myBi = myB.length;
        myBj = myB[0].length;
        
        myCi = myAi;
        myCj = myBj;
        
        myC = new int[myCi][myCj];
    }//END constructor
    
    
    /**
     * Method to test if the passed matrices can be multiplied in the order 
     *  specified, return a boolean representing the result of that test, 
     *  and, if possible, compute their product.
     * 
     * @return dimMatch A boolean indicating if the dimensions and order of the 
     *  passed matrices allow them to be multiplied.
     */
    public boolean computeC() {
        boolean dimMatch = false;
        
        if (myAj == myBi) {
            dimMatch = true;
            
            for (int i = 0; i < myCi; i++) {
                
                for (int j = 0; j < myCj; j++) {
                    int sum = 0;
                    
                    for (int k = 0; k < myAj; k++) {
                        sum = sum + myA[i][k] * myB[k][j];
                    }//END for (INDEX k)
                    
                }//END for (INDEX j)
            }//END for (INDEX i)
        
        }//END if
     
        return dimMatch;
    }//END computeC() METHOD
    
    /**
     * Method the return either the values of the matrix product or, if the 
     *  matrix product cannot be computed, a 1 by 1 integer array with a 0 
     *  at (0,0).
     * 
     * @return aCout A 2D integer array containing either the values of the 
     *  matrix product or, if the matrix product cannot be computed, a 1 by 1 
     *  integer array with a 0 at (0,0).
     */
    public int[][] getC() {
        boolean dimMatch = this.computeC();
        int[][] aCout = new int[1][1];

        if (dimMatch) {
            aCout = myC.clone();
        } else {
            aCout[0][0] = 0;
        }//END if
        
        return aCout;
    }//END getC() METHOD
    
}//END MatrixMult.java CLASS
            