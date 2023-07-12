package com.ltdd14.FarmMarket;

import com.ltdd14.FarmMarket.model.user.User;
import com.ltdd14.FarmMarket.model.user.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FarmMarketApplicationTests {

	@Autowired
	private UserDao userDao;

//	@Test
	void addUserTest() {
		User user = new User();
		user.setFirst_name("Thanh");
		user.setLast_name("Nguyen");
		user.setUsername("minhhoang");
		user.setPassword("123");
		user.setEmail("thanh.2808@gmail.com");
		user.setPhone("039998202");
		userDao.save(user);
	}

//	@Test
	void getAllUsersAndDeleteThem(){
		List<User> users = userDao.getAllUsers();
		users.forEach(user -> {
			userDao.deleteUserById(user.getId());
		});
	}

}
