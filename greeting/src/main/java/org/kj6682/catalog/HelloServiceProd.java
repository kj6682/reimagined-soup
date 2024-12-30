package org.kj6682.catalog;

import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class HelloServiceProd implements HelloService {
    public String sayHello() {
        var version = SpringBootVersion.getVersion();
        var str = String.format("Hello world from Spring Boot %s", version);
        System.out.println(str);
        return str;
    }
}
