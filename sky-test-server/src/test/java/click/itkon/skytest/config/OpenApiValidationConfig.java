package click.itkon.skytest.config;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiValidationConfig {

    private static final String OPEN_API_SPEC_PATH = "spec/open-api-spec.yml";

    @Bean
    public Filter validationFilter() {
        return new OpenApiValidationFilter(true, true);
    }

    @Bean
    public WebMvcConfigurer openApiValidationInterceptor() {
        OpenApiInteractionValidator validator = OpenApiInteractionValidator
                .createFor(OPEN_API_SPEC_PATH)
                .build();
        OpenApiValidationInterceptor interceptor = new OpenApiValidationInterceptor(validator);

        return new WebMvcConfigurer() {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(interceptor);
            }
        };
    }
}
