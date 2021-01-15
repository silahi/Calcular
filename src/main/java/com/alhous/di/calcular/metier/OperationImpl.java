package com.alhous.di.calcular.metier;

import com.alhous.di.calcular.dao.INombre;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silah
 */
public class OperationImpl implements IOperation {

    private INombre nombre;

    @Override
    public double calcul() {
        double res = 0;
        try {
            String op = Files.readAllLines(new File("calcular.conf").toPath()).stream().filter(line -> line.startsWith("operateur")).findFirst().get();
            String operateur = op.split("=")[1];
            switch (operateur.charAt(0)) {
                case '*':
                    res = nombre.getNombre1() * nombre.getNombre2();
                    break;
                case '+':
                    res = nombre.getNombre1() - nombre.getNombre2();
                    break;
                case '-':
                    res = nombre.getNombre1() - nombre.getNombre2();
                    break;
                case '/':
                    res = nombre.getNombre1() / nombre.getNombre2();
                    break;

            }
        } catch (IOException ex) {
            Logger.getLogger(OperationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public INombre getNombre() {
        return nombre;
    }

    public void setNombre(INombre nombre) {
        this.nombre = nombre;
    }

}
