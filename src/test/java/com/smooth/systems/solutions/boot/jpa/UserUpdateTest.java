package com.smooth.systems.solutions.boot.jpa;

import com.smooth.systems.solutions.boot.jpa.dao.UserDao;
import com.smooth.systems.solutions.boot.jpa.dao.UserRepository;
import com.smooth.systems.solutions.boot.jpa.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

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
		assertEquals(Integer.valueOf(3),userRepository.findById(userId).get().getStatus());

		userDao.updateUserTransactionRequiredNew(userId);
		assertEquals(Integer.valueOf(4),userDao.getCurrentUserStatus(userId));

		userDao.updateUserTransactionRequiredNew(userId);
		assertEquals(Integer.valueOf(5),userDao.getCurrentUserStatus(userId));

		userDao.updateUserTransactionRequiredNew(userId);
		assertEquals(Integer.valueOf(6),userDao.getCurrentUserStatus(userId));

		userDao.updateUserTransactionRequired(userId);
		User updatedUser = userRepository.findById(userId).get();
		assertEquals(Integer.valueOf(4), updatedUser.getStatus());

		// TODO transaction will be rolled back, since multiple transaction update the object
	}
}
