package lu.cgi.d4g.services;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.ReactiveMailer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MailerService {

    @Inject
    ReactiveMailer reactiveMailer;

    @ConfigProperty(name = "green.l4nterne.url")
    private String url;

    public CompletionStage<Response> sendReset(String user, String token) {
        return reactiveMailer.send(Mail.withText(user, "[green l4nterne] Reset password",
            "Bonjour\n" +
                "Vous recevez cet e-mail car vous ou un adminstrateur avez effectué une demande de réinitialisation de votre mot de passe \n" +
                "Veuillez cliquer sur le lien ci-dessous pour réinitialiser votre mot de passe sur green l4nterne :\n" +
                url + "/user/reset/" + token + " \n\n" +
                "L'équipe de green l4nterne"))
            .thenApply(x -> Response.accepted().build());
    }

    public CompletionStage<Response> sendVerification(String user, String token) {
        return reactiveMailer.send(Mail.withText(user, "[green l4nterne] Vérifier votre E-mail",
            "Bonjour\n" +
                "Nous avons besoin de vérifier votre adresse e-mail avant de pouvoir vous authentifier sur green l4nterne.\n\n" +
                "S'il-vous-plaît, cliquez sur le lien ci-dessous pour vérifier votre adresse e-mail : \n" +
                url + "/user/validation/" + token + " \n\n" +
                "L'équipe de green l4nterne"))
            .thenApply(x -> Response.accepted().build());
    }

}
