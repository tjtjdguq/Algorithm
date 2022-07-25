package ssafy.algorithm.eval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Resgination {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int workdays=sc.nextInt();
		sc.nextLine();
		ArrayList<Schedule> list=new ArrayList<>();
		for(int i=0;i<workdays;i++) {
			list.add(new Schedule(i,sc.nextInt(),sc.nextInt()));
			sc.nextLine();
		}
		Collections.sort(list,Collections.reverseOrder());
		int[] scheduler=new int[workdays];
		int sum=0;
		for(Schedule s:list) {
			boolean can_schedule=true;
			if(s.time+s.index<=workdays) {
				for(int i=s.index;i<s.time+s.index;i++)
					if(scheduler[i]!=0) {
						can_schedule=false;
						break;
					}
			}else
				can_schedule=false;
			if(can_schedule) {
				sum+=s.pay;
//				System.out.printf("%d %d %d %.3f\n",s.index,s.time,s.pay,s.effectiveness);
				for(int i=0;i<s.time;i++)
					scheduler[s.index+i]=1;
			}
		}
		System.out.println(sum);
	}

}
class Schedule implements Comparable<Schedule>{
	int index;
	int time;
	int pay;
	double effectiveness;
	Schedule(){}
	Schedule(int index,int time,int pay){
		this.index=index;
		this.time=time;
		this.pay=pay;
		effectiveness=1.0*pay/time;
	}
	@Override
	public int compareTo(Schedule o) {
		// TODO Auto-generated method stub
		if(this.effectiveness<o.effectiveness)
			return -1;
		if(this.effectiveness==o.effectiveness) {
			if(this.time<o.time)
				return -1;
			if(this.time>o.time)
				return 1;
			return 0;
		}
		return 1;
	}
}
