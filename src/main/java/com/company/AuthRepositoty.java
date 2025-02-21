package com.company;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class AuthRepositoty {
    private List<Auth> authList = new ArrayList<>();

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;


    public String sendGmail(Auth auth) {
        System.out.println(authList);
        Random random = new Random();
        String verificationCodeTemp = String.valueOf(100000 + random.nextInt(900000));

        if (auth.getStatus() == Status.INACTIVE) {
            try {
                auth.setVerificationCode(verificationCodeTemp);
                auth.setStatus(Status.REGISTER);
                authList.add(auth);

                System.out.println(auth);

                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(from);
                helper.setTo(auth.getEmail());
                helper.setSubject("Verification Code");
                helper.setText("Kodni hech kimga aytmang bu kod bir martalik \n" + verificationCodeTemp);

                javaMailSender.send(message);
                return "Email ga kod bordi";
            } catch (Exception e) {
                e.printStackTrace();
                return "Email xatolik yuz berdi";
            }
        }
        return "Email ga xatolik yuz berdi";
    }

    public String sendVerificationCode(Auth auth) {

            for (Auth a : authList) {
                if (a.getEmail().equals(auth.getEmail())) {
                    System.out.println("email  topildi");
                    if (a.getVerificationCode().equals(auth.getVerificationCode())) {
                        System.out.println("Kod");
                        a.setStatus(Status.ACTIVE);
                        System.out.println(a.getStatus());
                        a.setPassword("KOD DAN O'TDI");

                        return "Ro`yxatdan o`tildi";
                    }
                }
            }
        return "Email xatolik";
    }

    public String login(Auth auth) {
        String password = auth.getPassword();
        String email = auth.getEmail();
        for (Auth a : authList) {
            if (a.getEmail().equals(email) ) {
                System.out.println("email  topildi .....");
                if (a.getPassword().equals(password) && a.getStatus == Status.ACTIVE) {
                    System.out.println("Kod");
                }
                return "Xush kelibsiz";
            }
        }
       return  "Ro'xatdan o`tmagansiz";
    }
}
