package devs2blu.hackweek.eventmanager.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Enable cycle detection to handle circular references
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        return modelMapper;
    }
}
