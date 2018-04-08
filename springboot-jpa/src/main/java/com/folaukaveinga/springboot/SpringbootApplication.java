package com.folaukaveinga.springboot;

import java.net.InetAddress;
import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.folaukaveinga.springboot.domain.Address;
import com.folaukaveinga.springboot.domain.Car;
import com.folaukaveinga.springboot.domain.Home;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.UserService;

@EnableCaching
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
	@Autowired
	private UserService userService;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			// Load test data
			
			loadData();
			
			try {
				Runtime runtime = Runtime.getRuntime();
				double mb = 1048576;// megabtye to byte
				double gb = 1073741824;// gigabyte to byte
				Environment env = ctx.getEnvironment();

				System.out.println("************************ Spring ***************************");
				System.out.println("** Active Profile: " + Arrays.toString(env.getActiveProfiles()));
				System.out.println("** Port: " + env.getProperty("server.port"));
				System.out.println("** External Url: " + InetAddress.getLocalHost().getHostAddress() + ":"
						+ env.getProperty("server.port"));
				System.out.println("** Internal Url: http://localhost:" + env.getProperty("server.port"));
				System.out.println("** External Swagger Url: " + InetAddress.getLocalHost().getHostAddress() + ":"
						+ env.getProperty("server.port")+"/swagger-ui.html");
				System.out.println("** Internal Swagger Url: http://localhost:" + env.getProperty("server.port")+"/swagger-ui.html");
				
				System.out.println("************************* Java - JVM ***********************");
				System.out.println("** Number of processors: " + runtime.availableProcessors());
				System.out.println("** Total memory: " + (double) (runtime.totalMemory() / mb) + " MB = "
						+ (double) (runtime.totalMemory() / gb) + " GB");
				System.out.println("** Max memory: " + (double) (runtime.maxMemory() / mb) + " MB = "
						+ (double) (runtime.maxMemory() / gb) + " GB");
				System.out.println("** Free memory: " + (double) (runtime.freeMemory() / mb) + " MB = "
						+ (double) (runtime.freeMemory() / gb) + " GB");
				System.out.println("************************************************************");
			} catch (Exception e) {
				System.err.println("Exception, commandlineRunner -> " + e.getMessage());
			}

		};
	}

	private void loadData() {
		for(int i=1;i<=50;i++) {
			User user = new User();
			user.setId(i);
			user.setAge(2*RandomUtils.nextInt());
			user.setActive(RandomUtils.nextBoolean());
			user.setEmail(RandomStringUtils.randomAlphabetic(10)+"@gmail.com");
			user.setName(RandomStringUtils.randomAlphabetic(5));
			user.setLastName("Folau");
			user.addCar(new Car(RandomStringUtils.randomAlphabetic(8)));
			user.addHome(new Home(RandomStringUtils.randomAlphabetic(8)));
			
			user.addAddress(new Address(RandomStringUtils.randomAlphabetic(8),
					RandomStringUtils.randomAlphabetic(8),
					RandomStringUtils.randomAlphabetic(8),
					RandomStringUtils.randomAlphabetic(8)));
			userService.save(user);
		}
	}
}
