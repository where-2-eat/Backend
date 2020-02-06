package ca.mcgill.ecse428.where2eat.backend.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse428.where2eat.backend.controller.Where2EatController;
import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.model.User;
import ca.mcgill.ecse428.where2eat.backend.model.UserLogin;
import ca.mcgill.ecse428.where2eat.backend.model.Where2Eat;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceTests {

	private static final String LOGIN_KEY = "123";

	private static final String LOGIN_PASS = "123";

	private static final Integer USER_ID = 123;

	private static final String FIRST_NAME = "First Name";

	private static final String LAST_NAME = "Last Name";
	
	private UserLogin userLogin;
	private User user;

	@Mock 
	private UserLoginRepository userLoginDao;

	@Mock
	private UserRepository userDao;

	@InjectMocks
	private Where2EatService service;
	
	@InjectMocks
	private Where2EatController controller;
	
	@Before
	public void clearDatabase() {
		userLoginDao.deleteAll();
		userDao.deleteAll();
	}

	@Before
	public void setMockOutput() {
		when(userLoginDao.findUserLoginByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {	
			if(invocation.getArgument(0).equals(LOGIN_KEY)) {
				UserLogin lgInfo = new UserLogin(null, null, null);
				lgInfo.setUserName(LOGIN_KEY);
				lgInfo.setPassword(LOGIN_PASS);
				return lgInfo;
			} else {
				return null;
			}
		});
		
		when(userDao.findUserByUserID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {	
			if(invocation.getArgument(0).equals(USER_ID)) {
				User user = new User(null, null, null, 0, null, null, null);
				user.setFirstName(FIRST_NAME);
				user.setLastName(LAST_NAME);
				return user;
			} else {
				return null;
			}
		});

		Answer<?> returnPatameterAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		when(userLoginDao.save(any(UserLogin.class))).thenAnswer(returnPatameterAnswer);
		when(userDao.save(any(User.class))).thenAnswer(returnPatameterAnswer);
	}

	@Before
	public void setupMock() {
		userLogin = mock(UserLogin.class);
		//user = mock(User.class);
	}		
	
	@Test
	public void testCreateLogin() {
		assertEquals(0, service.getAllLogins().size());

		String username = "user";
		String password = "pass";
		Where2Eat w = new Where2Eat();

		try {
			userLogin = service.createLogin(username, password, w);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(username, userLogin.getUserName());
		assertEquals(password, userLogin.getPassword());
	}
	
	@Test
	public void testCreateLoginNull() {
		String error = "";
		String username = null;
		String password = null;
		Where2Eat w = new Where2Eat();


		try {
			userLogin = service.createLogin(username, password, w);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);
	}
	
	@Test
	public void testCreateLoginEmpty() {
		String error = "";

		String username = "";
		String password = "";
		Where2Eat w = new Where2Eat();


		try {
			service.createLogin(username, password, w);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);
	}

	@Test
	public void testCreateLoginSpaces() {
		String error = "";

		String username = " ";
		String password = " ";
		Where2Eat w = new Where2Eat();


		try {
			service.createLogin(username, password, w);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error

		assertEquals("Login userName cannot be empty!Login password cannot be empty!", error);
   	}

	
}