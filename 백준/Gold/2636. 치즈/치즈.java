import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int r=sc.nextInt();
		int c=sc.nextInt();
		sc.nextLine();
		map=new int[r+2][c+2];
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				map[i][j]=sc.nextInt();
			}
			sc.nextLine();
		}
		boolean[][] v;
		int hour=0,cnt=0;
		while(true) {
			v=checkEdge();
			for(int i=0;i<v.length;i++) {
				for(int j=0;j<v[i].length;j++) {
					if(v[i][j]) {
						map[i][j]=0;
					}
				}
			}
			if(isAllMelted()) {
				for(int i=0;i<map.length;i++) {
					for(int j=0;j<map[i].length;j++) {
						if(v[i][j]) {
							cnt++;
						}
					}
				}
				break;
			}
			hour++;
		}
		System.out.println(hour+1);
		System.out.println(cnt);
	}
	static class Data{
		int x,y;

		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static boolean[][] checkEdge() {
		Queue<Data> q=new LinkedList<>();
		boolean[][] v=new boolean[map.length][map[0].length];
		boolean[][] edge=new boolean[map.length][map[0].length];
		q.offer(new Data(0,0));
		v[0][0]=true;
		while(!q.isEmpty()) {
			Data cur=q.poll();
			for(int d=0;d<4;d++) {
				int x=cur.x+dx[d];
				int y=cur.y+dy[d];
				if(x>=0&&x<map.length&&y>=0&&y<map[x].length&&!v[x][y]) {
					if(map[x][y]==0) {
						v[x][y]=true;
						q.offer(new Data(x,y));
					}else {
						edge[x][y]=true;
					}
				}
				
			}
		}
		return edge;
	}
	static boolean isAllMelted() {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if(map[i][j]==1) {
					return false;
				}
			}
		}
		return true;
	}

}
