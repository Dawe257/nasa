import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=";
    public static final String AUTH_KEY = "ti7MWrNiTOH1KutmdNpOyJzsWW7n7dcgJCwlriiE";
    public static ObjectMapper mapper = new ObjectMapper();
    public static CloseableHttpClient client = HttpClientBuilder.create()
            .setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setSocketTimeout(30000)
                    .setRedirectsEnabled(false)
                    .build())
            .build();

    public static void main(String[] args) throws IOException, URISyntaxException {

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI + AUTH_KEY);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = client.execute(request);
        NasaContent nasaContent = mapper.readValue(response.getEntity().getContent(), NasaContent.class);
        URL contentUrl = nasaContent.getUrl();

        request.setURI(contentUrl.toURI());
        response = client.execute(request);

        File outputFile = new File(FilenameUtils.getName(contentUrl.getPath()));
        if (outputFile.createNewFile()) {
            try (FileOutputStream writer = new FileOutputStream(outputFile)) {
                writer.write(response.getEntity().getContent().readAllBytes());
                writer.flush();
            }
        } else {
            throw new RuntimeException("Файл " + outputFile + " уже существует");
        }
    }
}
