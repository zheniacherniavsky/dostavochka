package com.pet.dostavochka.Services;

import com.pet.dostavochka.Model.Cart;
import com.pet.dostavochka.Model.Delivery;
import com.pet.dostavochka.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailService {
    @Autowired
    JavaMailSender emailSender;
    @Autowired
    CartSevice cartSevice;

    @Async("asyncExecutor")
    public void sendMailAboutOrder(Delivery order) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlMsg = "<h2>Thank you for purchase!</h2>";
        htmlMsg += "<h5>Shipping Address</h5>";
        htmlMsg += "<p>For: "+order.getLastName()+" "+order.getFirstName()+"</p>";
        htmlMsg += "<p>Street: "+order.getStreet()+"</p>";
        htmlMsg += "<p>Home: "+order.getHome()+"</p>";
        htmlMsg += "<p>Floor: "+order.getFloor()+"</p>";
        htmlMsg += "<p>Flat: "+order.getFlat()+"</p>";
        htmlMsg += "</br>";
        htmlMsg += "<img src='https://gridkit.ru/wp-content/uploads/2020/12/delivery-lunches-moscow-logo1.jpg' height=200/>";
        message.setContent(htmlMsg, "text/html");
        helper.setTo(order.getEmail());
        helper.setSubject("Dostavochka!");
        this.emailSender.send(message);
    }
}
