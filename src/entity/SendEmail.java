/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author laptop
 */
public class SendEmail {
    public static void guiEmail(String toEmail, String subject, String body) throws MessagingException {
        if (toEmail == null || toEmail.trim().isEmpty()) {
            throw new MessagingException("Địa chỉ email không được để trống");
        }

        // Thiết lập các thuộc tính cho kết nối SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tạo một phiên làm việc với SMTP
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("proanuong1@gmail.com", "dkad uctm pnhu ytfv");
            }
        });

        try {
            // Tạo một tin nhắn email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_email@gmail.com"));

            // Kiểm tra địa chỉ email người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail.trim()));

            message.setSubject(subject);
            message.setText(body);

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công");
        } catch (MessagingException e) {
            throw new MessagingException("Lỗi khi gửi email: " + e.getMessage());
        }
    }
}
