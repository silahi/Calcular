package com.alhous.di.calcular.metier;

import com.alhous.di.calcular.dao.INombre;

/**
 *
 * @author silah
 */
public class AdditionImpl implements IAddition {

    private INombre nombres;

    @Override
    public double somme() {
        return nombres.getNombre1() + nombres.getNombre2();
    }

    public INombre getNombres() {
        return nombres;
    }

    public void setNombres(INombre nombres) {
        this.nombres = nombres;
    } 
    
    

}
