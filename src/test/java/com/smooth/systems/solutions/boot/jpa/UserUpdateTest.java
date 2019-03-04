package com.smooth.systems.solutions.boot.jpa;

import com.smooth.systems.solutions.boot.jpa.dao.UserDao;
import com.smooth.systems.solutions.boot.jpa.dao.UserRepository;
import com.smooth.systems.solutions.boot.jpa.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

//@ActiveProfiles("test")
@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestingConfigurationProperties.class })
public class UserUpdateTest {

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserDao userDao;

	@Test
	public void simpleTest() {
		User storedUser = userDao.createUser("test", Integer.valueOf(3));

		Long userId = storedUser.getId();
		User updatedUser = userRepository.findById(userId).get();
		assertEquals(Integer.valueOf(3),userRepository.findById(userId).get().getStatus());

		userDao.updateUser(userId);
		assertEquals(Integer.valueOf(4),userDao.getCurrentUserStatus(userId));

		userDao.updateUser(userId);
		assertEquals(Integer.valueOf(5),userDao.getCurrentUserStatus(userId));

		userDao.updateUser(userId);
		assertEquals(Integer.valueOf(6),userDao.getCurrentUserStatus(userId));

		updatedUser = userRepository.findById(userId).get();
		assertEquals(Integer.valueOf(3), updatedUser.getStatus());
	}
}
