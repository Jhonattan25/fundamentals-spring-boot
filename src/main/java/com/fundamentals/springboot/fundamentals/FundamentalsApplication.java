package com.fundamentals.springboot.fundamentals;

import com.fundamentals.springboot.fundamentals.bean.MyBean;
import com.fundamentals.springboot.fundamentals.bean.MyBeanWithDependency;
import com.fundamentals.springboot.fundamentals.bean.MyBeanWithProperties;
import com.fundamentals.springboot.fundamentals.component.ComponentDependency;
import com.fundamentals.springboot.fundamentals.entity.User;
import com.fundamentals.springboot.fundamentals.pojo.UserPojo;
import com.fundamentals.springboot.fundamentals.repository.UserRepository;
import com.fundamentals.springboot.fundamentals.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	private UserService userService;
	@Autowired
	public FundamentalsApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								   MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
								   UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//previousClasses();
		saveUserInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void saveWithErrorTransactional() {
		User test1 = new User("TestTransactional1", "testTransactional1@email.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "testTransactional2@email.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "testTransactional1@email.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "testTransactional4@email.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		}catch (Exception e) {
			LOGGER.error("Esta es una exception dentro del metodo transaccional " + e);
		}
		userService.getAllUsers()
				.stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional "+
						user));
	}

	private void getInformationJpqlFromUser() {
		/*
		LOGGER.info("Usuario con el metodo findByuserEmail " + userRepository.findByUserEmail("jhonattan@email.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("J", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort" + user));

		userRepository.findByName("Jhonattan")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.findByEmailAndName( "jhonattan@email.com", "Jhonattan")
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%J%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameLike" + user));

		userRepository.findByNameOrEmail("", "pedro@email.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail" + user));
		 */
		userRepository.findByBirthDateBetween(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 4, 20))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas: " + user));
		userRepository.findByNameContainingOrderByIdDesc("J")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado " + user));

		LOGGER.info("Usuario a partir del named parameter es: " + userRepository
				.getAllByBirthDateAndEmail(LocalDate.of(2022, 2,26), "nico@email.com")
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con el named parameter")));
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
