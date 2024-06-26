package com.example.gasip.member.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;
    public void sendEmail(String toEmail,String text) throws MessagingException {
        MimeMessage emailForm = createEmailForm(toEmail,text);
        emailSender.send(emailForm);
    }

    // 인증번호 발신 이메일 form 생성
    private MimeMessage createEmailForm(String toEmail,String text) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setSubject("GASIP 회원가입 인증번호");
        messageHelper.setTo(toEmail);
        messageHelper.setFrom("GASIP");
        messageHelper.setText(text, true);

        return mimeMessage;
    }
}
