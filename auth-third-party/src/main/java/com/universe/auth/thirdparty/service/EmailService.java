package com.universe.auth.thirdparty.service;

/**
 * @description: 邮件发信Server
 * @author: lhp
 * @time: 2022/1/13 4:57 下午
 */
public interface EmailService {
    /**
     * 发送简单的邮件
     *
     * @param email 电子邮件
     * @param content  内容
     */
    String sendSimpleMail(String email, String content);

    /**
     * 发送HTML邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    String sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    String sendAttachmentsMail(String to, String subject, String content, String filePath);
}
