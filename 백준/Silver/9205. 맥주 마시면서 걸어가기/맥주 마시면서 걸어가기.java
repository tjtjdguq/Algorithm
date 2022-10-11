import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static boolean flag;
	static int[] parent;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			flag=false;
			int n = sc.nextInt();
			makeSet(n);
			ArrayList<Integer[]> store = new ArrayList<>();
			for (int i = 0; i < n + 2; i++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				store.add(new Integer[] {x,y });
			}
			Data[] list=new Data[n+2];
			// logic
			for (int i = 0; i < store.size(); i++) {
				for (int j = 0; j < store.size(); j++) {
					if (store.get(i) == store.get(j)) {
						continue;
					}
					Integer[] a=store.get(i);
					Integer[] b=store.get(j);
					int distance=Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
					if(distance<=1000) {
						list[i]=new Data(j,distance,list[i]);
						unionSet(i,j);
					}
				}
			}
//			print(list);
			if(!unionSet(0,n+1)) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
	}

	static class Data {
		int to, weight;
		Data next;

		public Data(int to, int weight, Data next) {
			super();
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
	}
	static void DFS(int n,int cur,Data[] list,boolean[] v) {
		if(cur==n+1) {
			flag=true;
			return;
		}
		for(Data ptr=list[cur];ptr!=null;ptr=ptr.next) {
			if(!v[ptr.to]) {
				v[ptr.to]=true;
				DFS(n,ptr.to,list,v);
				v[ptr.to]=false;
			}
		}
		
	}
	static void print(Data[] list) {
		for(int i=0;i<list.length;i++) {
			System.out.print(i+" => [");
			for(Data ptr=list[i];ptr!=null;ptr=ptr.next) {
				System.out.print(ptr.to+" ");
			}
			System.out.println("]");
		}
	}
	static void makeSet(int n) {
		parent=new int[n+2];
		for(int i=0;i<parent.length;i++) {
			parent[i]=i;
		}
	}
	static int findParent(int i) {
		if(parent[i]==i) {
			return i;
		}
		return parent[i]=findParent(parent[i]);
	}
	static boolean unionSet(int a,int b) {
		int rootA=findParent(a);
		int rootB=findParent(b);
		if(rootA==rootB) {
			return false;
		}
		parent[rootB]=rootA;
		return true;
	}
}
