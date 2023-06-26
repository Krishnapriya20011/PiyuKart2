package com.example.Product.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsserivesClass {
    @Value("${twilio.accountSid}")
    private String accountSid;
    @Value("${twilio.authToken}")
    private String authToken;
    public String sendSms(String to, String body) {
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber(to), new PhoneNumber("+14302434985"), body).create();
        return "Message sended Successfully";
    }
}
