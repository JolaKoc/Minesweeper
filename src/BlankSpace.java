package minesweeper;

import java.util.ArrayList;

public class BlankSpace { 
    //No of rows and columns 
    private  int ROW, COL,cnt=1 ; //counter is inisiated 1 in case that the current position is valid
    ArrayList<Integer> array=new ArrayList<>(2*ROW);
         
    public BlankSpace(int n,int m){
        ROW=n;
        COL=m;
    }
    // A function to check if a given cell (row, col) can 
    // be included in DFS
    boolean isSafe(int M[][], int row, int col, 
                   boolean visited[][]) 
    { 
        // row number is in range, column number is in range 
        // and value is 0 and not yet visited 
        return (row >= 0) && (row < ROW) && 
               (col >= 0) && (col < COL) && 
               (M[row][col]==0 && !visited[row][col]); 
    } 
  
    // A utility function to do DFS for a 2D boolean matrix. 
    // It only considers the 8 neighbors as adjacent vertices 
    ArrayList DFS(int M[][], int row, int col, boolean visited[][]) 
    { 
       if(M[row][col]!=0 ){
                          
           return null;
       }//base case
        // of 8 neighbors of a given cell 
        int rowNbr[] = new int[] {-1, -1, -1,  0, 0,  1, 1, 1}; 
        int colNbr[] = new int[] {-1,  0,  1, -1, 1, -1, 0, 1}; 
        
        // Mark this cell as visited 
        visited[row][col] = true; 
        array.add(row);//insert the indexes to the array
        array.add(col);
        // Recur for all connected neighbours 
        for (int k = 0; k < 8; ++k) {
            if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited) ){ 
                DFS(M, row + rowNbr[k], col + colNbr[k], visited);
                
            }  

        }
        
        return array;
     
    }
 

} 
