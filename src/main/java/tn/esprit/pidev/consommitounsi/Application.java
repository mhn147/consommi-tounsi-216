package tn.esprit.pidev.consommitounsi;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class Application {

    // Stripe secret key
    @PostConstruct
    public void setup() {
        Stripe.apiKey = "sk_test_51IZDC9Dnznjyl503QI1f7zydGwSZwkgDHLibxIHhyNjvPpFsi79SZEfuaiCPV8XO7nzUUEap1N5Cjnqo8U3IG1Yi00Wx8ExOzT";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
