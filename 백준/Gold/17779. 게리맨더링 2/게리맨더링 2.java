import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static int[][] population;
	static int min;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		map=new int[n][n];
		population=new int[n][n];
		min=Integer.MAX_VALUE;
		for(int i=0;i<n;i++) {
			for (int j = 0; j < n; j++) {
				population[i][j]=sc.nextInt();
			}
		}
		//logic
		for(int x=0;x<n-2;x++) {
			for(int y=1;y<n-1;y++) {
				for(int d1=1;0<=y-d1 && y-d1<y;d1++) {
					for(int d2=1;y<y+d2 && y+d2<n;d2++) {
						map=new int[n][n];
						makeDistrict(x,y,d1,d2);
//						System.out.println(x+","+y+","+d1+","+d2);
//						System.out.println(calcPopulation());
//						print();
						min=Math.min(min,calcPopulation());
					}
				}
			}
		}
		System.out.println(min);
		
	}
	private static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------------------");
	}
	private static void makeDistrict(int x,int y,int d1,int d2) {
		for(int i=0;i<map.length;i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(0<=i && i<x+d1 && 0<=j && j<=y) {
					map[i][j]=1;
				}else if(0<=i && i<=x+d2 && y<j && j<map[i].length) {
					map[i][j]=2;
				}else if(x+d1<=i && i<map.length && 0<=j && j<y-d1+d2) {
					map[i][j]=3;
				}else if(x+d2<i && i<map.length && y-d1+d2<=j && j<map[i].length) {
					map[i][j]=4;
				}
			}
		}
		Queue<Integer> q=new LinkedList<>();
		for(int i=0;i<=d1;i++) {
			if(isInBound(x+i, y-i)) {
				map[x+i][y-i]=5;
			}
			if(isInBound(x+i+1, y-i)&&i<d1) {
				map[x+i+1][y-i]=5;
			}
			if(isInBound(x+d2+i, y+d2-i)) {
				map[x+d2+i][y+d2-i]=5;
			}
		}
		for(int i=0;i<=d2;i++) {
			if(isInBound(x+i, y+i)) {
				map[x+i][y+i]=5;
			}
			if(isInBound(x+i+1, y+i)&&i<d2) {
				map[x+i+1][y+i]=5;
			}
			if(isInBound(x+d1+i, y-d1+i)) {
				map[x+d1+i][y-d1+i]=5;
			}
		}
		q.offer((x+1)*map.length+y);
		map[x+1][y]=5;
		while(!q.isEmpty()) {
			int cur=q.poll();
			for(int d=0;d<4;d++) {
				int i=cur/map.length+dx[d];
				int j=cur%map.length+dy[d];
				if(i>=0&&i<map.length&&j>=0&&j<map[i].length&&map[i][j]!=5) {
					q.offer(i*map.length+j);
					map[i][j]=5;
				}
			}
		}
	}
	private static int calcPopulation() {
		int[] cnt=new int[5];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				cnt[map[i][j]-1]+=population[i][j];
			}
		}
		int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
		for(int i=0;i<5;i++) {
			max=Math.max(max,cnt[i]);
			min=Math.min(min,cnt[i]);
		}
		return Math.abs(max-min);
	}
	private static boolean isInBound(int x,int y) {
		if(x>=0&&x<map.length&&y>=0&&y<map[x].length) {
			return true;
		}
		return false;
	}

}
