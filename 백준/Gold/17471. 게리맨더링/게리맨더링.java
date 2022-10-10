import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer[]> list;
	static int min;
	static Node[] node;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] population=new int[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			population[i]=Integer.parseInt(st.nextToken());
		}
		node=new Node[n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int adjcnt=Integer.parseInt(st.nextToken());
			for(int j=0;j<adjcnt;j++) {
				int to=Integer.parseInt(st.nextToken())-1;
				node[i]=new Node(to,node[i]);
				node[to]=new Node(i,node[to]);
			}
		}
		list=new ArrayList<>();
		for(int i=1;i<=n/2;i++) {
			comb(n,0,0,i,new Integer[i]);
		}
		min=Integer.MAX_VALUE;
		for(Integer[] A:list) {
			Integer[] B=remain(n,A);
			if(isLinked(A)&&isLinked(B)) {
//				System.out.println(Arrays.toString(A)+" Linked");
				int sumA=0,sumB=0;
				for(Integer i:A) {
					sumA+=population[i];
				}
				for(Integer i:B) {
					sumB+=population[i];
				}
				min=Math.min(min,Math.abs(sumA-sumB));
			}
		}
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
	}
	static class Node{
		int data;
		Node next;
		public Node(int data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
	}
	public static boolean isLinked(Integer[] comb) {
		if(comb.length==1) {
			return true;
		}
		Queue<Integer> q=new LinkedList<>();
		boolean[] visited=new boolean[comb.length];
		q.offer(comb[0]);
		visited[0]=true;
		while(!q.isEmpty()) {
			int district=q.poll();
			if(in(district,comb)!=-1 ) {
				for(Node ptr=node[district];ptr!=null;ptr=ptr.next) {
					int index=in(ptr.data,comb);
					if(index!=-1 && !visited[index]) {
						visited[index]=true;
						q.offer(comb[index]);
					}
				}
			}
		}
		for(int i=0;i<visited.length;i++) {
			if(!visited[i]) {
				return false;
			}
		}
		return true;
	}
	public static int in(int a,Integer[] comb) {
		for(int i=0;i<comb.length;i++) {
			if(a==comb[i]) {
				return i;
			}
		}
		return -1;
	}
	public static void comb(int n,int depth,int start,int r,Integer[] output) {
		if(depth==r) {
			list.add(Arrays.copyOf(output,output.length));
			return;
		}
		for(int i=start;i<n;i++) {
			output[depth]=i;
			comb(n,depth+1,i+1,r,output);
		}

	}
	public static Integer[] remain(int n,Integer[] arr) {
		ArrayList<Integer> all=new ArrayList<>();
		ArrayList<Integer> A=new ArrayList<>();
		for(int i=0;i<n;i++) {
			all.add(i);
		}
		for(Integer i:arr) {
			A.add(i);
		}
		if(all.removeAll(A)) {
			return all.toArray(new Integer[n-arr.length]);
		}else {
			return null;
		}
	}

}
