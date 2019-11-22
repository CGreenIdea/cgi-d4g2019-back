package lu.cgi.d4g.document.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class DocumentBean {
    private final long id;
    private final String title;
    private final String fileName;
    private final Instant creationDate;
}
