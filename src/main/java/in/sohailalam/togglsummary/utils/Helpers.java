package in.sohailalam.togglsummary.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A class with some helper methods used ofr Toggl
 */
@Service
public final class Helpers {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("MMM");
    private final Environment ENV;
    // Toggl API Token
    private String apiToken;

    @Autowired
    public Helpers(Environment ENV) {
        this.ENV = ENV;
        this.apiToken = ENV.getProperty("api_token");
    }

    public static String getBase64Encoding(String str) {
        return new String(Base64.encodeBase64(str.getBytes(Charset.forName("US-ASCII"))));
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getAuthHeaderValue() {
        return "Basic " + getBase64Encoding(this.apiToken + ":api_token");
    }

    public Pair<Date, Date> getDateRange(Calendar calendar) {
        Date start, end;
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        start = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        end = calendar.getTime();
        
        return Pair.of(start, end);
    }

    public String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public String getMonth(Date date) {
        return MONTH_FORMAT.format(date);
    }
}
