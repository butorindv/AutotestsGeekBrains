package lesson4;

import net.bytebuddy.implementation.bytecode.Throw;

public class Triangle {

    public static void main(String[] args)  {

        System.out.println(triangleArea(7, 6, 8));
    }


    public static Double triangleArea(double a, double b, double c) {

        if ((a >= 0 && b >= 0 && c >= 0)&&(a<(b+c)&&b<(a+c)&&c<(a+b))) {
            double p = (a + b + c) / 2;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }
      /*  else {
            try {
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Сторона дольжна быть положительной!");
            }

        }*/
return null;
    }


}
