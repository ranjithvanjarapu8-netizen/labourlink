package com.ranji.labourlink.Config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {

        Map<String, String> config = new HashMap<>();

        config.put("cloud_name", "mrjpk64t");
        config.put("api_key", "485235368651879");
        config.put("api_secret", "x9brljV2aIh1lv3Z0z2ozcM4LpQ");
        config.put("secure", "true");

        return new Cloudinary(config);
    }
}