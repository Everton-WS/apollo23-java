package devs2blu.hackweek.eventmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Manager API")
                        .version(appVersion)
                        .description(
                                """
                                API for interacting with a simple event manager system. Part of the HackWeek of the +Devs2Blu 2023 final project.
                                Main features are: 1) a gamification feature to manage QR Code treasures associated with certain Activities and Events and 2) handling Questions sent by Users to Speakers.
                                
                                API and Server code can be found [here](https://github.com/Everton-WS/apollo23-java).
                                
                                Frontend Flutter App can be found [here](https://github.com/Everton-WS/apollo23-flutter).
                                """
                        )
                        .license(new License().name("MIT").url("https://opensource.org/license/mit/"))
                );
    }
}
