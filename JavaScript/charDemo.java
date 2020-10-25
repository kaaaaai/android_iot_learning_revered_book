public class charDemo{
	public static void main(String[] args) {
		//定义一个变量存放字符‘a’
		char a='a';
		System.out.println("a="+a); //输出：a=a
		char ch=65;
		System.out.println("ch="+ch);//输出：ch=A
		char ch1=65535;
		System.out.println("ch1="+ch1);
		//如果字面值超出了 char 类型所表示的数据范围，需要进行强制类型转换
		char ch2=(char)65536;
		System.out.println("ch2="+ch2);
		//定义变量存放 unicode 编码表示的字符
		char c='\u005d';
		System.out.println("c="+c);
	}
}