package lu.cgi.d4g.document.resources;

import lu.cgi.d4g.document.entities.DocumentEntity;
import lu.cgi.d4g.document.services.DocumentService;
import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Path("/document")
public class DocumentResource {

    @ConfigProperty(name = "green.l4nterne.upload.path")
    String fileUploadPath;

    @Inject
    public DocumentService documentService;

    @GET
    @Path("/mine")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public List<DocumentEntity> find(@Context SecurityContext securityContext) {
        return documentService.findByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/download/{id}")
    @RolesAllowed({"user"})
    public Response download(@Context SecurityContext securityContext, @PathParam("id") long id) {
        final DocumentEntity document = documentService.findByIdAndUsername(id, securityContext.getUserPrincipal().getName());
        if (document == null) {
            throw new NotFoundException("This document does not exist or you don't have access to it.");
        }

        final File localFile = Paths.get(fileUploadPath, document.getLocalName()).toFile();
        if (!localFile.exists()) {
            throw new InternalServerErrorException("This file seems to be missing.");
        }

        String contentType;
        try {
            contentType = Files.probeContentType(Paths.get(document.getFilename()));
        } catch (IOException e) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }

        return Response
            .ok((StreamingOutput) output -> {
                try (InputStream in = new FileInputStream(localFile)) {
                    IOUtils.copy(in, output);
                } finally {
                    output.close();
                }
            })
            .header("Content-Disposition", String.format("attachment; filename=\"%s\"", document.getFilename()))
            .header("Content-Length", localFile.length())
            .header("Content-Type", contentType)
            .build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<DocumentEntity> findAll() {
        return documentService.findAll();
    }

}
