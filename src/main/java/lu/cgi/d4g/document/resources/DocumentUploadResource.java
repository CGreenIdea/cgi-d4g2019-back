package lu.cgi.d4g.document.resources;

import lombok.extern.slf4j.Slf4j;
import lu.cgi.d4g.document.services.DocumentService;
import lu.cgi.d4g.house.information.entities.HomeEntity;
import lu.cgi.d4g.house.information.services.HomeService;
import org.apache.commons.io.FileUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Path("/document")
@Slf4j
public class DocumentUploadResource {

    @ConfigProperty(name = "green.l4nterne.upload.path")
    String fileUploadPath;

    @Inject
    HomeService homeService;

    @Inject
    DocumentService documentService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    // FIXME restrict to admin
    public Response upload(MultipartFormDataInput input) {
        final Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        try {
            final long homeId = Long.parseLong(uploadForm.get("home").get(0).getBodyAsString());
            final String title = uploadForm.get("title").get(0).getBodyAsString();
            final HomeEntity home = homeService.findById(homeId);
            doUploadFile(uploadForm.get("file"), home, title);
        } catch (IOException | NumberFormatException e) {
            throw new BadRequestException("Incorrect data");
        }

        return Response.ok("File was uploaded").build();
    }

    private void doUploadFile(List<InputPart> inputParts, HomeEntity home, String title) {
        for (InputPart inputPart : inputParts) {
            try {
                final MultivaluedMap<String, String> header = inputPart.getHeaders();
                final String originalFilename = getFileName(header);
                final String storedFilename = UUID.randomUUID().toString();

                //convert the uploaded file to inputstream
                final InputStream inputStream = inputPart.getBody(InputStream.class, null);

                writeFile(inputStream, storedFilename);

                documentService.createDocument(home, originalFilename, storedFilename, title);
            } catch (IOException e) {
                throw new InternalServerErrorException("Upload failed");
            }
        }
    }

    /**
     * header sample { Content-Type=[image/png], Content-Disposition=[form-data; name="file";
     * filename="filename.extension"] }
     **/
    //get uploaded filename, is there a easy way in RESTEasy?
    private String getFileName(MultivaluedMap<String, String> header) {

        final String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if (filename.trim().startsWith("filename")) {
                final String[] name = filename.split("=");
                return name[1].trim().replaceAll("\"", "");
            }
        }
        return "unknown";
    }

    //save to somewhere
    private void writeFile(InputStream content, String filename) throws IOException {
        final File file = Paths.get(fileUploadPath, filename).toFile();
        FileUtils.copyToFile(content, file);
    }
}
