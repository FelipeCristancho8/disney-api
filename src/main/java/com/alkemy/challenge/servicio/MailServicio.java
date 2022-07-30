package com.alkemy.challenge.servicio;

import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServicio {

    private String asunto = "REGISTRO EXITOSO";
    private String cuerpo = "Gracias por registrarse a la aplicacion de personajes de Disney. Su cuenta ya fue activada.";

    @Value("${spring.mail.username}")
    private String usuarioCorreo;


    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarMensaje(String para){
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setFrom(usuarioCorreo);
        mensaje.setSubject(this.asunto);
        mensaje.setText(this.cuerpo);

        javaMailSender.send(mensaje);
    }
}
