package com.alhous.di.calcular.dao;

import java.util.Scanner;

/**
 *
 * @author silah
 */
public class NombreImplV1 implements INombre {

    Scanner sc = new Scanner(System.in);

    @Override
    public double getNombre1() { 
        System.out.print("Entrez le premier nombre : ");
        double n1 = sc.nextDouble();
        return n1;
    }

    @Override
    public double getNombre2() {
        System.out.print("Entrez le deuxième nombre : ");
        double n2 = sc.nextDouble();
        return n2;
    }

}
