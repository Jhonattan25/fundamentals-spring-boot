package com.fundamentals.springboot.fundamentals;

import com.fundamentals.springboot.fundamentals.bean.MyBean;
import com.fundamentals.springboot.fundamentals.bean.MyBeanWithDependency;
import com.fundamentals.springboot.fundamentals.bean.MyBeanWithProperties;
import com.fundamentals.springboot.fundamentals.component.ComponentDependency;
import com.fundamentals.springboot.fundamentals.entity.User;
import com.fundamentals.springboot.fundamentals.pojo.UserPojo;
import com.fundamentals.springboot.fundamentals.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentalsApplication implements CommandLineRunner {
	private final Log LOGGER = LogFactory.getLog(FundamentalsApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	@Autowired
	public FundamentalsApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								   MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								   UserPojo userPojo, UserRepository userRepository) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//previousClasses();
		saveUserInDataBase();
		getInformationJpqlFromUser();
	}

	private void getInformationJpqlFromUser() {
		LOGGER.info("Usuario con el metodo findByuserEmail " + userRepository.findByUserEmail("jhonattan@email.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("J", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort" + user));
	}

	private void saveUserInDataBase() {
		User user1 = new User("Jhonattan", "jhonattan@email.com", LocalDate.of(2022, 8,8));
		User user2 = new User("Julie", "julie@email.com", LocalDate.of(2022, 5,21));
		User user3 = new User("Pedro", "pedro@email.com", LocalDate.of(2022, 1,21));
		User user4 = new User("Nico", "nico@email.com", LocalDate.of(2022, 2,26));
		User user5 = new User("Carlos", "carlos@email.com", LocalDate.of(2022, 3,15));
		User user6 = new User("Cristian", "cristian@email.com", LocalDate.of(2022, 8,1));
		User user7 = new User("Catalina", "catalina@email.com", LocalDate.of(2022, 1,17));
		User user8 = new User("Carolina", "carolina@email.com", LocalDate.of(2022, 1,25));
		User user9 = new User("Juan", "juan@email.com", LocalDate.of(2022, 4,14));
		User user10 = new User("Camilo", "camilo@email.com", LocalDate.of(2022, 2,6));

		List<User> userList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		userList.stream().forEach(userRepository::save);
	}

	private void previousClasses() {
		componentDependency.greet();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());

		try{
			//error
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}
}
