public static StringDemo {
	public static void main(String[] args) {
		//定义一个空字符串
		String s1 = "";
		System.out.println("s1="+s1);
		String s2 = "Hello";
		System.out.println("s2="+s2);
		//定义一个包含 Unicode 字符的字符串
		String s3 = "A\u005d\u005fB";
		System.out.println("s3="+s3);
		//定义一个包含空格的字符串
		String s4 = "Hell  imooc!";
		System.out.println("s4="+s4);
	}
}