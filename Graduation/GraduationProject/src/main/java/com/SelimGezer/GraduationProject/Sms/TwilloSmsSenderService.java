package com.SelimGezer.GraduationProject.Sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class TwilloSmsSenderService {

    @Value("${accountSID}")
    private String accountSID;

    @Value("${accountAuthToken}")
    private String accountAuthToken;

    @Value("${twilloSenderNumber}")
    private String twilloSenderNumber;

    public String sendMessage(SmsModel messageRequest) {
        try {
            Twilio.init(accountSID, accountAuthToken);

            String smsText = messageRequest.getSmsText();
            String mobileNumber = messageRequest.getMobileNumber();

            PhoneNumber recieverPhoneNumber = new PhoneNumber(mobileNumber);
            PhoneNumber senderTwilloPhoneNumber = new PhoneNumber(twilloSenderNumber);

            MessageCreator creator = com.twilio.rest.api.v2010.account.Message.creator(recieverPhoneNumber, senderTwilloPhoneNumber, smsText);
            Message create = creator.create();

            BigDecimal billingAmount = create.getPrice();
            Message.Status status = create.getStatus();

            log.info(String.format("Mesaj %s telefon numarasına başarı ile gönderildi.",mobileNumber));
            return "Message Send Succesfully";
        } catch (Exception e) {
            log.error("SendMessage methodu bir hata tespit etti. " ,e);
            return "Message Send Fail";
        }

    }

}
