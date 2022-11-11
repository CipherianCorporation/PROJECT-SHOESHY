package com.edu.graduationproject.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class GeoLocationConfig {
    private static DatabaseReader reader = null;
    private final ResourceLoader resourceLoader;

    public GeoLocationConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DatabaseReader databaseReader() {
        try {
            Resource resource = resourceLoader.getResource("classpath:maxmind/GeoLite2-City.mmdb");
            InputStream dbAsStream = resource.getInputStream();
            System.out.println("-----GeoLocationConfig loaded successfully!----");

            return reader = new DatabaseReader.Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build();
        } catch (Exception e) {
            System.out.println("-----Datbase loader could not be initiated!!----");
            return null;
        }
    }

}
