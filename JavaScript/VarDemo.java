public class VarDemo{
	public static void main(String[] args) {
		//定义两个整型变量x,y
		// int x1=3,y=5;
		int x,y;
		x=3;y=5;
		int x1=4;
		System.out.println("x="+x);
		System.out.println("y="+y);
		//关于换行的问题
		System.out.println(x+"\t"+y+'\n');
		System.out.println();//换行
		System.out.println(x+","+y);
		System.out.println("\n\'"+x+"\'");
		//定义一个汉字的字符
		char ch = '幕';
		System.out.println(ch);
		char 中文 = '中';
		System.out.println(中文);
		//用科学计数法表示浮点型数据
		double d = 1.23E5; //1.23 乘以 10 的五次方
		float f=1.23e5f;
		double d1 = .2;
		System.out.println("d="+d);
		System.out.println("f="+f);
		System.out.println("d1="+d1);

	}
}