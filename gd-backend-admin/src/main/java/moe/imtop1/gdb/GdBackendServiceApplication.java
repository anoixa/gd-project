package moe.imtop1.gdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author anoixa
 */
@SpringBootApplication
//@ComponentScan(basePackages = {"moe.imtop1.gdb","moe.imtop1.gdb.common"})
public class GdBackendServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GdBackendServiceApplication.class, args);
    }
}