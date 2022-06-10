import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

public class NasaContent {

    private final String copyright;
    private final Date date;
    private final String explanation;
    private final URL hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final URL url;

    public NasaContent(@JsonProperty("copyright") String copyright,
                       @JsonProperty("date") Date date,
                       @JsonProperty("explanation") String explanation,
                       @JsonProperty("hdurl") URL hdurl,
                       @JsonProperty("media_type") String mediaType,
                       @JsonProperty("service_version") String serviceVersion,
                       @JsonProperty("title") String title,
                       @JsonProperty("url") URL url) {

        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public Date getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public URL getHdurl() {
        return hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "NasaContent{" +
                "copyright='" + copyright + '\'' +
                ", date=" + date +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
