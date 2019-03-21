
package minesweeper;

import java.util.Random;

public class MatrixGeneration {

    private int n;//matrix length
    private int m;//matrix length
    int mat[][];
	
	//constructor
public MatrixGeneration(int n, int m) {
		this.n=n;
		this.m=m;
                mat=new int[n+1][m+1];
	}
	
public int[][] bombGenerator(int nrOfBombs) {//Bomb generator
	for(int i=0;i<nrOfBombs;i++) {
		Random mine=new Random();
		int a=mine.nextInt(this.n-1)+0;//index
		int b=mine.nextInt(this.m-1)+0;//index
		mat[a][b]=-1;
	}
	return mat;
	}
	
public int [][] matrixGenerator(int matrix[][]) {//calculate centered
	int dx,dy,sum=0;
	for(int i=0;i<this.n;i++) {
		for(int j=0;j<this.m;j++) {
			if(matrix[i][j]!=-1) {
			sum=0;
			dx=i-1;
			dy=j-1;
			if(isVisitable(dx,dy) && matrix[dx][dy]==-1) {
				sum++;
			}
			dx=i;
			dy=j-1;
			if(isVisitable(dx,dy) && matrix[dx][dy]==-1) {
				sum++;
				}
			dx=i-1;
			dy=j;
			if(isVisitable(dx,dy) && matrix[dx][dy]==-1) {
				sum++;
				}
			dx=i+1;
			dy=j+1;
			if(isVisitable(dx,dy) && dx!=i && dy!=j && matrix[dx][dy]==-1) {
				sum++;
				}
			dx=i;
			dy=j+1;
			if(isVisitable(dx,dy) && matrix[dx][dy]==-1) {
				sum++;
				}
			dx=i+1;
			dy=j;
			if(isVisitable(dx,dy) && matrix[dx][dy]==-1) {
				sum++;
				}
			dx=i+1;
			dy=j-1;
			if(isVisitable(dx,dy) && matrix[dx][dy]==-1) {
				sum++;
				}
			dx=i-1;
			dy=j+1;
			if(isVisitable(dx,dy) && matrix[dx][dy]==-1) {
				sum++;;
			}
			matrix[i][j]=sum;
			}
			System.out.print(matrix[i][j]+"\t");
		}
		System.out.println();
	}
        return matrix;

}

private  boolean isVisitable(int dx, int dy) {
	if(dx>=0 && dy>=0 && dx<=this.n && dy<=this.m)
		return true;
	return false;
}
 
    
}


