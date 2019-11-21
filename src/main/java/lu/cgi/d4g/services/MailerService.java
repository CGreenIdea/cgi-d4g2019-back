package lu.cgi.d4g.services;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.ReactiveMailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MailerService {

    @Inject
    ReactiveMailer reactiveMailer;

    public CompletionStage<Response> sendVerification(String user, String uuid) {
        return reactiveMailer.send(Mail.withText(user, "[green l4nterne] Vérifier votre E-mail",
            "Bonjour \n" +
                "Nous avons besoin de vérifier votre adresse e-mail avant que vous ne puissiez vous authentifier sur green l4nterne.\n\n" +
                "S'il-vous-plaît, cliquez sur le lien ci-dessous pour vérifier votre adresse e-mail : "
                + "/mailer/validation/" + uuid + " \n\n" + // TODO adresse validation
                "L'équipe de green l4nterne"))
            .thenApply(x -> Response.accepted().build());
    }

}
