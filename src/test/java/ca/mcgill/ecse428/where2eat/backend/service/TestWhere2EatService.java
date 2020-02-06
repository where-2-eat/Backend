package ca.mcgill.ecse428.where2eat.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.model.*;
import ca.mcgill.ecse428.where2eat.backend.service.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWhere2EatService {

	@Autowired
	private Where2EatService service;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserLogin userLoginRepository;

	@Before
	@After
	public void clearDatabase() {
		userRepository.deleteAll();
		userLoginRepository.delete();;
	}

	/*
	 * Login
	 */
	@Test
	public void testCreateLogin() {
		assertEquals(0, service.getAllLogins().size());
		String userName = "Muhammad";
		String password = "elahi";
		Where2Eat w = new Where2Eat();

		try {
			service.createLogin(userName, password, w);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<UserLogin> allLogins = service.getAllLogins();

		assertEquals(1, allLogins.size());
		assertEquals(userName, allLogins.get(0).getUserName());
		assertEquals(password, allLogins.get(0).getPassword());
		service.deleteLogin(userName);
	}

	@Test
	public void testCreateLoginNull() {
		assertEquals(0, service.getAllLogins().size());

		String error = "";

		String userName = null;
		String password = null;
		Where2Eat w = new Where2Eat();

		try {
			service.createLogin(userName, password, w);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);

		List<UserLogin> allLogins = service.getAllLogins();
		assertEquals(0, allLogins.size());     
	}

	@Test
	public void testCreateLoginSpaces() {
		assertEquals(0, service.getAllLogins().size());

		String error = "";

		String userName = " ";
		String password = " ";
		Where2Eat w = new Where2Eat();

		try {
			service.createLogin(userName, password, w);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);

		List<UserLogin> allLogins = service.getAllLogins();
		assertEquals(0, allLogins.size());     
	}

	@Test
	public void testCreateLoginEmpty() {
		assertEquals(0, service.getAllLogins().size());

		String error = "";

		String userName = "";
		String password = "";
		Where2Eat w = new Where2Eat();

		try {
			service.createLogin(userName, password, w);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);

		List<UserLogin> allLogins = service.getAllLogins();
		assertEquals(0, allLogins.size());     
	}
}