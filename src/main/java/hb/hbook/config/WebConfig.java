package hb.hbook.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            // react 프로젝트 서버를 허락한다는 의미
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("*");
  }

  
}