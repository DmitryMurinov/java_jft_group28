package learn.mantis.appmanager;

import learn.mantis.Model.MailMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by d.murinov on 21.04.2016.
 */
public class MailHelper {

    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app){
        this.app = app;
        wiser = new Wiser();
    }

    public List<MailMessage> waitForMail(int count, long timeout){
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + timeout){
            if (wiser.getMessages().size() >= count){
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
            }
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        throw new Error("No mail received after signup new user, or received less  than expected");
    }

    public static MailMessage toModelMail(WiserMessage m){
        try {
            MimeMessage mm = m.getMimeMessage();
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }


}