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
        /*SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setFrom(usuarioCorreo);
        mensaje.setSubject(this.asunto);
        mensaje.setText(this.cuerpo);*/


        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg,true);
            helper.setTo(para);
            helper.setFrom(this.usuarioCorreo);
            helper.setSubject(this.asunto);
            helper.setText(this.cuerpo);
            FileSystemResource imagen = new FileSystemResource(new    File("src/main/resources/static/images/disney.jpg"));
            helper.addAttachment("imagen",imagen);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        //javaMailSender.send(mensaje);
    }
}
