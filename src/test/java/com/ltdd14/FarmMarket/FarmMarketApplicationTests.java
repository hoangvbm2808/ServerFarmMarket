package com.ltdd14.FarmMarket;

import com.ltdd14.FarmMarket.model.order.Order;
import com.ltdd14.FarmMarket.model.order.OrderDao;
import com.ltdd14.FarmMarket.model.user.User;
import com.ltdd14.FarmMarket.model.user.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class FarmMarketApplicationTests {

	@Autowired
	private UserDao userDao;
	@Autowired
			private OrderDao orderDao;

	@Test
	void addUserTest() {
		User user = new User();
		user.setFirst_name("Hoang");
		user.setLast_name("Vo");
		user.setUsername("minhhoang");
		user.setPassword("123456");
		user.setEmail("mhoang8@gmail.com");
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

	@Test
	void saveOrder1(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(100000);
		order.setUser_id(2);
		orderDao.save(order);
	}
	@Test
	void saveOrder2(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(30000);
		order.setUser_id(7);
		orderDao.save(order);
	}
	@Test
	void saveOrder3(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(103000);
		order.setUser_id(6);
		orderDao.save(order);
	}
	@Test
	void saveOrder4(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(1200000);
		order.setUser_id(4);
		orderDao.save(order);
	}
	@Test
	void saveOrder5(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(130000);
		order.setUser_id(5);
		orderDao.save(order);
	}
	@Test
	void saveOrder6(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(500000);
		order.setUser_id(4);
		orderDao.save(order);
	}

	@Test
	void saveOrder7(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(200000);
		order.setUser_id(3);
		orderDao.save(order);
	}
	@Test
	void saveOrder8(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(1100000);
		order.setUser_id(3);
		orderDao.save(order);
	}
	@Test
	void saveOrder9(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(123000);
		order.setUser_id(3);
		orderDao.save(order);
	}
	@Test
	void saveOrder10(){
		Order order = new Order();
		LocalDate today = LocalDate.now();
		order.setCreate_day(String.valueOf(today));
		order.setTotal_amount(210000);
		order.setUser_id(7);
		orderDao.save(order);
	}


}
