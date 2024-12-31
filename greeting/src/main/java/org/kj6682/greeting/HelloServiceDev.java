package org.kj6682.greeting;

import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class HelloServiceDev implements HelloService {
    public String sayHello() {
        var version = SpringBootVersion.getVersion();
        var str = String.format("DEV > DEV > Hello world from Spring Boot %s", version);
        System.out.println(str);
        return str;
    }
}
