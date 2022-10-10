import java.util.Scanner;

public class Main {
	static int[][] map;
	static int[][] dp;
	static int min;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		map=new int[n][n];
		dp=new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j]=sc.nextInt();
				dp[i][j]=map[i][j];
				if(i!=j && dp[i][j]==0) {
					dp[i][j]=1000001;
				}
			}
		}
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				if(i==k) {
					continue;
				}
				for(int j=0;j<n;j++) {
					if(j==k) {
						continue;
					}
					dp[i][j]=Math.min(dp[i][j], dp[i][k]+dp[k][j]);
				}
			}
		}
		
		min=Integer.MAX_VALUE;
		for(int i=0;i<n;i++) {
			boolean[] v=new boolean[n];
			v[i]=true;
			DFS(i,v,i,0);
		}
		System.out.println(min);
	}
	static void DFS(int start,boolean[] v,int cur,int distance) {
		if(allVisited(v)) {
			if(map[cur][start]>0) {
				min=Math.min(min, distance+map[cur][start]);
			}
			return;
		}
		for(int i=0;i<v.length;i++) {
			if(map[cur][i]>0 && !v[i]) {
				v[i]=true;
				DFS(start,v,i,distance+map[cur][i]);
				v[i]=false;
			}
		}
	}
	static boolean allVisited(boolean[] v) {
		for(int i=0;i<v.length;i++) {
			if(!v[i]) {
				return false;
			}
		}
		return true;
	}

}
