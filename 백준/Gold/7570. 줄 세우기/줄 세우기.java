import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] baby = new int[n+1];
		String[] str = br.readLine().split(" ");
		dp = new int[n+1];
		int max=0;
		for (int i = 1; i <= n; i++) {
			baby[i] = Integer.parseInt(str[i-1]);
			dp[baby[i]]=dp[baby[i]-1]+1;
			max=Math.max(dp[baby[i]], max);
		}
		System.out.println(n-max);
//		int size = 0;
//		for (int i = 0; i < baby.length; i++) {
//			int index = Arrays.binarySearch(dp, 0, size, baby[i]);
//			if (index >= 0) {
//				continue;
//			}
//			index = Math.abs(index + 1);
//			dp[index] = baby[i];
//			if(index==size) {
//				size++;
//			}
//		}
//		System.out.println(size);

	}

}
