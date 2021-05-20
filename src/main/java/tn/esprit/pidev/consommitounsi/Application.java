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
        Stripe.apiKey = "sk_test_51IlUUVCoa56OaMeIGuB3oqT1WNPPl8hMBj9OvlLu4kRs8T2bqVUBHGmcT5YrBh3aMJdUggDYvYOGrryPQvYQYM4l00TIDG07Fe";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
