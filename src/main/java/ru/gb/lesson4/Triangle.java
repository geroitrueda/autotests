package ru.gb.lesson4;

import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        System.out.println("Сторона a=");
        int a=sc.nextInt();

        System.out.println("Сторона b=");
        int b=sc.nextInt();

        System.out.println("Сторона c=");
        int c=sc.nextInt();

        int p = (a+b+c)/2;
        double s=Math.sqrt(p*(p-a)*(p-b)*(p-c));

        System.out.println("Площадь треугольника = " + s);

        sc.close();
    }
}
