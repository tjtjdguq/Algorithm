import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[][] map;
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String[] str=br.readLine().split(" ");
		int r=Integer.parseInt(str[0]);
		int c=Integer.parseInt(str[1]);
		map=new char[r][c];
		Queue<Data> fire=new LinkedList<>();
		Queue<Data> jihoon=new LinkedList<>();
		boolean[][] v=new boolean[r][c];
		for (int i = 0; i < map.length; i++) {
			String line=br.readLine();
			for (int j = 0; j < map[i].length; j++) {
				map[i][j]=line.charAt(j);
				if(map[i][j]=='J') {
					jihoon.offer(new Data(i,j));
					v[i][j]=true;
				}else if(map[i][j]=='F') {
					fire.offer(new Data(i,j));
				}
			}
		}
		int time=0;
		while(!jihoon.isEmpty()) {
			int size=fire.size();
			for(int i=0;i<size;i++) {
				Data cur=fire.poll();
				for(int d=0;d<4;d++) {
					int x=cur.x+dx[d];
					int y=cur.y+dy[d];
					if(x>=0&&x<map.length&&y>=0&&y<map[x].length&&map[x][y]!='#'&&map[x][y]!='F') {
						fire.offer(new Data(x,y));
						map[x][y]='F';
					}
				}
			}
			size=jihoon.size();
			for(int i=0;i<size;i++) {
				Data cur=jihoon.poll();
				for(int d=0;d<4;d++) {
					int x=cur.x+dx[d];
					int y=cur.y+dy[d];
					if(x<0||x>=map.length||y<0||y>=map[x].length) {
						System.out.println(time+1);
						return;
					}
					if(x>=0&&x<map.length&&y>=0&&y<map[x].length&&map[x][y]=='.'&&!v[x][y]) {
						jihoon.offer(new Data(x,y));
						v[x][y]=true;
					}
				}
			}
			time++;
		}
		System.out.println("IMPOSSIBLE");
		
		
	}
	static class Data{
		int x,y;

		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}
