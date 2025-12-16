package com.kotanapp.kotanappapi.utils.mailer;

import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.exception.MailGunException;
import com.mailgun.model.message.Message;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivationEmailService {
    private final MailgunMessagesApi mailgunMessagesApi;

    @Value("${mailgun.api.client_domain}")
    private String CLIENT_DOMAIN;

    @Value("${mailgun.api.from}")
    private String SENDER;

    public void sendActivationEmail(AuthAccountDAO user, String code) throws MailGunException {
        log.info("Sending account activation email to user: {}", user.getEmail());
        Message message = Message.builder()
                                 .from(SENDER)
                                 .to(user.getEmail())
                                 .subject("Kod aktywacyjny konta ")
                                 .html("""
                                               <div>
                                                   Kod aktywacyjny :
                                                   <br>
                                                   <br>
                                                   <b>%s</b>
                                               </div>
                                               """.formatted(code))
                                 .build();

        try (Response feignResponse = mailgunMessagesApi.sendMessageFeignResponse(CLIENT_DOMAIN, message)) {
            if (feignResponse.status() != 200) {
                log.error("Mail not send!");
                throw new MailGunException("Mail not send!");
            }
            log.info("Sent account activation email to user: {}", user.getEmail());
        }
    }

}

