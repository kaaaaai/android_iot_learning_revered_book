public class ArrayDemo5{
	public static void main(String[] args) {
		//二维数组的声明
		//三种形式
		//声明 int 类型的二维数组
		int[][] intArray;
		//声明 float 类型的二维数组
		float floatArray[][];
		//声明 double 类型的二位数组
		double[] doubleArray[][];
		//创将一个三行三列的 int 类型数组
		intArray = new int[3][3];
		System.out.println("intArray 数组的第3行第2列的元素为："+intArray[2][1]);
		//为第2行第3个元素赋值为9
		intArray[1][2] = 9;
		System.out.println("intArray 数组的第 2 行第 3 列的元素为："+intArray[1][2]);
		//声明数组的同事进行创建
		char[][] ch = new char[3][5];
		//创建 float 类型的数值时，只指定行数
		floatArray = new float[3][];
		// System.out.println(floatArray[0][0]);
		//每行相当于一个一维数组，需要创建
		floatArray[0] = new float[3];
		floatArray[1] = new float[4];
		floatArray[2] = new float[5];
		System.out.println(floatArray[0][0]); //输出 0.0 PS:float 的默认值为 0.0

		//二位数组的初始化
		int[][] num = {{1,2,3},{4,5,6},{7,8,9}};
		System.out.println("num 数组的第 1 行第 2列的元素为："+num[0][1]);
		System.out.println("num 数组的行数为："+num.length);
		System.out.println("num 数组的列数为："+num[0].length);
		//循环输出二维数组的内容
		for (int i = 0; i<num.length; i++) {
			for (int j = 0; j<num[i].length; j+=){
				System..out..println(num[i][j]+"     ");
			}
			System.out.println("     ");
		}
	}
}