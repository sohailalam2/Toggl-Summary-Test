package in.sohailalam.togglsummary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Toggl {

    /**
     * Starting point of the application
     *
     * NOTE: The application expects two required parameters as environment variables -
     * <ol>
     *    <li>api_token = The Toggl API Token</li>
     *    <li>client_secret = The Google API Secret Json file path</li>
     * </ol>
     *
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(Toggl.class, args);
    }
}
