package com.universe.auth.thirdparty.service.impl;

import com.universe.auth.thirdparty.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @description: 邮件发送接口实现类
 * @author: lhp
 * @time: 2022/1/13 4:58 下午
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.title}")
    private String title;

    @Value("${spring.mail.from}")
    private String from;

    @Override
    public String sendSimpleMail(String email, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //发件人
            messageHelper.setFrom(from);
            //收件人
            messageHelper.setTo(email);
            //邮件主题
            message.setSubject(title);
            //邮件内容
            String appellation = email.split("@")[0];
            messageHelper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            return "邮件发送失败" + e.getMessage();
        }
        return "邮件已发送";
    }

    @Override
    public String sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            message.setSubject(subject);
        } catch (MessagingException e) {
            return "邮件发送失败" + e.getMessage();
        }
        return "邮件已发送";
    }

    @Override
    public String sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            //携带附件
            FileSystemResource file = new FileSystemResource(filePath);
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            messageHelper.addAttachment(fileName, file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            return "邮件发送失败" + e.getMessage();
        }
        return "邮件已发送";
    }

}
