package com.sportradar.assessment.wordle;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordleApplication {

    private static final Logger log = LoggerFactory.getLogger(WordleApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(WordleApplication.class, args);
        log.info("""
                
                
                
                ____    __    ____  ______   .______       _______   __       _______\s
                \\   \\  /  \\  /   / /  __  \\  |   _  \\     |       \\ |  |     |   ____|
                 \\   \\/    \\/   / |  |  |  | |  |_)  |    |  .--.  ||  |     |  |__  \s
                  \\            /  |  |  |  | |      /     |  |  |  ||  |     |   __| \s
                   \\    /\\    /   |  `--'  | |  |\\  \\----.|  '--'  ||  `----.|  |____\s
                    \\__/  \\__/     \\______/  | _| `._____||_______/ |_______||_______|
                
               
                """);
	}

}
