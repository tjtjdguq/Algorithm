package ssafy.algorithm.eval;

import java.util.Scanner;

public class Omok {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int[][] arr=new int[19][19];
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		int[] dx= {-1,-1,0,1,1,1,0,-1}; //북,북동,동,동남,남,서남,서,북서
		int[] dy= {0,1,1,1,0,-1,-1,-1}; //북쪽으로 부터 시계방향
		outer:for(int i=0;i<19;i++) {
			for(int j=0;j<19;j++) {
				//				System.out.printf("(%d,%d) \n",i,j);
				if(arr[i][j]!=0) {
					for(int d=2;d<5;d++) {
						if(Search(arr, i, j, d, 1, arr[i][j])) {
							int anti_d=(d+4)%8;
							if(i+dx[anti_d]>=0 && i+dx[anti_d]<19 && j+dy[anti_d]>=0 && j+dy[anti_d]<19) {
								if(arr[i+dx[(d+4)%8]][j+dy[(d+4)%8]]!=arr[i][j]) {
									System.out.println(arr[i][j]);
									System.out.printf("%d %d",i+1,j+1);
									return;
								}
							}else {
								System.out.println(arr[i][j]);
								System.out.printf("%d %d",i+1,j+1);
								return;
							}
						}
					}
					if(Search(arr, i, j, 5, 1, arr[i][j])) {
						int anti_d=(5+4)%8;
						if(i+dx[anti_d]>=0 && i+dx[anti_d]<19 && j+dy[anti_d]>=0 && j+dy[anti_d]<19) {
							if(arr[i+dx[(5+4)%8]][j+dy[(5+4)%8]]!=arr[i][j]) {
								System.out.println(arr[i][j]);
								System.out.printf("%d %d",i+1+(4*dx[5]),j+1+(4*dy[5]));
								return;
							}
						}else {
							System.out.println(arr[i][j]);
							System.out.printf("%d %d",i+1+(4*dx[5]),j+1+(4*dy[5]));
							return;
						}
					}
				}
			}
		}
		System.out.println("0");
	}
	static boolean Search(int[][] arr,int x,int y,int direction,int cnt,int stoneColor) {
		//		System.out.println(cnt);
		int[] dx= {-1,-1,0,1,1,1,0,-1}; //북,북동,동,동남,남,서남,서,북서
		int[] dy= {0,1,1,1,0,-1,-1,-1}; //북쪽으로 부터 시계방향
		if(x+dx[direction]>=0 && x+dx[direction]<19 && y+dy[direction]>=0 && y+dy[direction]<19) {
			if(arr[x+dx[direction]][y+dy[direction]]!=stoneColor && cnt==5)
				return true;
			if(arr[x+dx[direction]][y+dy[direction]]==stoneColor && cnt<5)
				if(Search(arr,x+dx[direction], y+dy[direction], direction, ++cnt,stoneColor))
					return true;
		}else {
			if(cnt==5)
				return true;
		}
		return false;
	}
}
