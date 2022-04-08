package api.mailer;

/**
 * Created by Ibrahim Olanrewaju S on 24/09/2016.
 */

import org.apache.commons.mail.EmailAttachment;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Mailer {

    /**
     * 1 second delay on sending emails
     */
    private static final int DELAY = 1;

    private static final String MAIL_FROM = play.Configuration.root().getString("mail.from");

    /**
     * Envelop to prepare.
     */
    public static class Envelop {
        private String subject;
        private String body;
        private List<String> toEmails;
        private List<File> attachments;
        private String attachmentType = EmailAttachment.ATTACHMENT;


        public Envelop(String subject, String body, List<String> toEmails) {
            this.subject = subject;
            this.body = body;
            this.toEmails = toEmails;
        }

        public Envelop(String subject, String body, String email) {
            this.body = body;
            this.subject = subject;
            this.toEmails = new ArrayList<String>();
            this.toEmails.add(email);
        }

        public Envelop(String subject, String body, String email, List<File> files) {
            this.subject = subject;
            this.body = body;
            this.toEmails = new ArrayList<>();
            this.toEmails.add(email);
            this.attachments = files;
        }

        public Envelop(String subject, String body, List<String> toEmails, List<File> files) {
            this.subject = subject;
            this.body = body;
            this.toEmails = toEmails;
            this.attachments = files;
        }
    }

    /**
     * Send a email, using Akka to offload it to an actor.
     *
     * @param envelop envelop to send
     */
    public static void sendMail(Envelop envelop) {
        final FiniteDuration delay = Duration.create(DELAY, TimeUnit.SECONDS);
        Akka.system().scheduler().scheduleOnce(delay, new EnvelopCong(envelop), Akka.system().dispatcher());
    }

    static class EnvelopCong implements Runnable {

        private Envelop envelop;

        public EnvelopCong(Envelop envelop) {
            this.envelop = envelop;
        }

        public void run() {
            Properties props = System.getProperties();

            // Setup mail server
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", play.Configuration.root().getString("mail.host"));
            props.put("mail.smtp.port", play.Configuration.root().getString("mail.port"));

            System.out.println("Sent mail to " + play.Configuration.root().getString("mail.host"));


            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(play.Configuration.root().getString("mail.user"), play.Configuration.root().getString("mail.password"));
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                for (String email : envelop.toEmails) {
                    message.setFrom(new InternetAddress(play.Configuration.root().getString("mail.from")));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message.setSubject(envelop.subject);
                    message.setContent(envelop.body, "text/html");

                    Transport.send(message);
                    System.out.println("Sent mail to " + email);
                }
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }
}
