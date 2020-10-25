class test{
     static int iValue = 0;
     public test() {
     }
}

public class testDemo {
     public static void main(String[] argv)  {
          test test1 = new test();
          test test2 = new test();
          System.out.println(test1.iValue);
          test2.iValue  = 10;
          System.out.println(test2.iValue);
          System.out.println(test1.iValue);
     } 
}
