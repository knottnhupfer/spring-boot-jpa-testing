package com.smooth.systems.solutions.boot.jpa.dao;

import com.smooth.systems.solutions.boot.jpa.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDao {

	@Resource
	private UserRepository userRepository;

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void updateUser(long userId) {
		User user = loadUser(userId);
		user.setStatus(user.getStatus() + 1);
		user = userRepository.save(user);
		System.out.println("Updated to state: " + user.getStatus());
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public User createUser(String name, Integer status) {
		User user = new User(name, status);
		return userRepository.save(user);
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public Integer getCurrentUserStatus(Long userId) {
		return loadUser(userId).getStatus();
	}

	private User loadUser(long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) {
			throw new IllegalStateException("No user found");
		}
		return userOptional.get();
	}
}
