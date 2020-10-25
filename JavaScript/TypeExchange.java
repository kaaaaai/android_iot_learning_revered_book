public class TypeExchange{
	public static void main(String[] args) {
		//char 类型和 int 类型之间的转换
		char c =(char)65536;
		int n;
		n = c;//隐式类型转换
		c=(char)n;

		//整型和浮点型的类型转换问题
		int x=100;
		long y=x;
		x = (int)y;
		float f=10000000000L;

		System.out.println("f="+f);
		float f1 = 103948583923948L;
		System.out.println("f1="+f1);
	}
}