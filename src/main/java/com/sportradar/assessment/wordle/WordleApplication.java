package com.sportradar.assessment.wordle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WordleApplication {

    private static final Logger log = LoggerFactory.getLogger(WordleApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WordleApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = app.run(args);

        log.info("""
                
                
                
                ____    __    ____  ______   .______       _______   __       _______\s
                \\   \\  /  \\  /   / /  __  \\  |   _  \\     |       \\ |  |     |   ____|
                 \\   \\/    \\/   / |  |  |  | |  |_)  |    |  .--.  ||  |     |  |__  \s
                  \\            /  |  |  |  | |      /     |  |  |  ||  |     |   __| \s
                   \\    /\\    /   |  `--'  | |  |\\  \\----.|  '--'  ||  `----.|  |____\s
                    \\__/  \\__/     \\______/  | _| `._____||_______/ |_______||_______|
                
               
                """);

        String filepath = context.getEnvironment().getProperty("words.path", "words.txt");
        Processor processor = new Processor(filepath);
        processor.play();
	}

}
