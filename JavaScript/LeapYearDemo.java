public class LeapYearDemo{
	public static void main(String[] args) {
		System.out.println("请输入年份：");
		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
		//闰年的判断规则：能被 4 整除但不能被 100 整除的年份，或者能被 400 整除的年份
		if ((year%4==0)&(year%100!=0)|(year%400==0)) {
			System.out.println(year+"是闰年!");
		}else{
			System.out.println(year+"不是闰年！");
		}
	}
}