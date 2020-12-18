package ninja.diku.email;

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
    private static final Email email = new Email("noreply@lukire.tech", "Discord Verification");
    private SendGrid sendGrid = new SendGrid(getSendGridToken());

    private BotEmail() {
    }

    /**
     * Returns a singleton BotEmail reference
     * @return BotEmail
     */
    public static BotEmail getInstance() {
        if(instance==null) {
            instance=new BotEmail();
        }
        return instance;
    }

    /**
     * Used to send an email to the email with the subject and content specified. Used by the verification system to verify emails.
     * @param to the email address which the email will be sent to
     * @param subject the headline of the email
     * @param content the content of the email
     * @return void
     * @see ninja.diku.command.commands.introduction.JoinCommand
     */
    public void sendEmail(Email to, String subject, Content content) {
        Mail mail = new Mail(email, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.print("Email From: "+email.getEmail()+" to: "+to.getEmail() + " ");
            System.out.print(response.getStatusCode() + " ");
            System.out.println(response.getHeaders() + " ");
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
