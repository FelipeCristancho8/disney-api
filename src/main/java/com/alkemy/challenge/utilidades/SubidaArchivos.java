package com.alkemy.challenge.utilidades;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SubidaArchivos {

    public static String subirImagen(MultipartFile imagen){
        Path directorioImagenes = Paths.get("src/main/resources/static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImagen = imagen.getBytes();
            Path rutaImagen = Paths.get(rutaAbsoluta+"/"+imagen.getOriginalFilename());
            //Files.write(rutaImagen,bytesImagen);
            return rutaImagen.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
