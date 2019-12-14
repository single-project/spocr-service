package org.century.scp.spocr;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "org.century.scp.spocr")
public class SpocrApp {

  public static void main(String[] args) {
    new SpringApplicationBuilder()
        .sources(SpocrApp.class)
        // TODO: add unimportant features here
        .bannerMode(Banner.Mode.CONSOLE)
        // TODO: turn off in prod
        .logStartupInfo(true)
        .run(args);
  }
}
