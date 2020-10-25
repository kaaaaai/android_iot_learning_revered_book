public class PlusDemo {
	public static void main(String[] args) {
		//求1到5的累加和
		int n=1;
		int sum = 0;
		while(n<=5){
			sum=sum+n;
			n++;
		}
		System.out.println("1到5的累加和为:"+sum);
	}
}