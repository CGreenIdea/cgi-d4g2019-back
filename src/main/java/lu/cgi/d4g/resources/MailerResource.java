package lu.cgi.d4g.resources;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.ReactiveMailer;
import lu.cgi.d4g.security.entities.UserEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletionStage;

@Path("/mailer")
public class MailerResource {

    @Inject
    ReactiveMailer reactiveMailer;

    @GET
    @Path("/verification")
    public CompletionStage<Response> sendVerification() {
        UserEntity user = new UserEntity(); // todo getUser
        return reactiveMailer.send(Mail.withText(user.getUserId(), "[green l4nterne] Vérifier votre E-mail",
            "Bonjour \n" +
                "Nous avons besoin de vérifier votre adresse e-mail avant que vous ne puissiez vous authentifier sur green l4nterne.\n\n" +
                "S'il-vous-plaît, cliquez sur le lien ci-dessous pour vérifier votre adresse e-mail : "
                + "\n\n" + // TODO adresse validation
                "L'équipe de green l4nterne"))
            .thenApply(x -> Response.accepted().build());
    }

}
