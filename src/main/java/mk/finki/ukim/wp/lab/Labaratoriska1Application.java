package mk.finki.ukim.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Labaratoriska1Application {
    public static void main(String[] args) {
        SpringApplication.run(Labaratoriska1Application.class, args);
    }
}