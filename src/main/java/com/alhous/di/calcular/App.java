package com.alhous.di.calcular;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silah
 */
public class App {

    public static void main(String[] args) {
        try {
            File file = new File("calcular.conf");
            Optional<String> sc = Files.readAllLines(file.toPath()).stream().filter(line -> line.startsWith("start_class")).findFirst();
            if (sc.isPresent()) {
                String startClassName = sc.get().split("=")[1];
                Class startClass = Class.forName(startClassName);
                IStart istart = (IStart) startClass.newInstance();
                istart.start();
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Message d'erreur : "+ex.getMessage(), ex);
        }
    }
}
