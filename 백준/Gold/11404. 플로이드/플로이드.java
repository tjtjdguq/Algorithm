import java.util.Scanner;

public class Main{
	static int[][] dp;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		dp=new int[n][n];
		for(int i=0;i<m;i++) {
			int from=sc.nextInt()-1;
			int to=sc.nextInt()-1;
			int weight=sc.nextInt();
			if(dp[from][to]==0) {
				dp[from][to]=weight;
			}else {
				dp[from][to]=Math.min(dp[from][to], weight);
			}
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i!=j&&dp[i][j]==0) {
					dp[i][j]=10000001;
				}
			}
		}
		for(int k=0;k<n;k++) {
			for(int i=0;i<n;i++) {
				if(i==k) {
					continue;
				}
				for(int j=0;j<n;j++) {
					if(j==i) {
						continue;
					}
					dp[i][j]=Math.min(dp[i][j],dp[i][k]+dp[k][j]);
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(dp[i][j]>10000000) {
					sb.append("0 ");
				}else {
					sb.append(dp[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

}
