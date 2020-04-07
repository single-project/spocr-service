package org.century.scp.spocr;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "org.century.scp.spocr")
public class SpocrApp {

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .sources(SpocrApp.class)
        // TODO: create unimportant features here
        .bannerMode(Banner.Mode.CONSOLE)
        // TODO: turn off in prod
        .logStartupInfo(true)
        .run(args);
  }

  @PostConstruct
  public void init(){
    // Setting Spring Boot SetTimeZone
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
}
