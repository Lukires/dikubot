package com.diku.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BotEmail {

    private static BotEmail instance;
    private static final Email email = new Email("lukiwokimc@gmail.com");
    private SendGrid sendGrid = new SendGrid(getSendGridToken());

    private BotEmail() {
    }

    public static BotEmail getInstance() {
        if(instance==null) {
            instance=new BotEmail();
        }
        return instance;
    }

    public void sendEmail(Email to, String subject, Content content) {
        Mail mail = new Mail(email, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getSendGridToken() {
        File file = new File("src/main/resources/sendgridtoken.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
