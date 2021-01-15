package com.alhous.di.calcular;

import com.alhous.di.calcular.dao.INombre;
import com.alhous.di.calcular.metier.IOperation;
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silah
 */
public class StartImplV2 implements IStart {

    @Override
    public void start() {

        try {
            Path path = new File("calcular.conf").toPath();

            Optional<String> nombreImplLine = Files.readAllLines(path).stream().filter(line -> line.startsWith("nombre_impl")).findFirst();
            Optional<String> operationLine = Files.readAllLines(path).stream().filter(line -> line.startsWith("operation")).findFirst();
            Optional<String> versionLine = Files.readAllLines(path).stream().filter(line -> line.startsWith("version")).findFirst();
            String op = Files.readAllLines(path).stream().filter(line -> line.startsWith("operateur")).findFirst().get();
            if (versionLine.isPresent()) {
                out.println("Version de l'application : " + versionLine.get().split("=")[1]);

            }

            if (operationLine.isPresent() && nombreImplLine.isPresent()) {

                Class cnombreImpl = Class.forName(nombreImplLine.get().split("=")[1]);
                INombre nombre = (INombre) cnombreImpl.newInstance();

                Class coperation = Class.forName(operationLine.get().split("=")[1]);
                IOperation operation = (IOperation) coperation.newInstance();

                Method m = coperation.getMethod("setNombre", INombre.class);
                m.invoke(operation, nombre);

                double resultat = operation.calcul();

                out.println(nombre.getNombre1() + "  " + op.split("=")[1] + "  " + nombre.getNombre2() + " = " + resultat);

            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(StartImpl.class.getName()).log(Level.SEVERE, "Message -> " + ex.getMessage(), ex);
        }

    }
}
