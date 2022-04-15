package com.project.service;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.User;
import com.project.repository.UserDao;

@Service
public class UserServiceImple implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailService emailService;

	@Override
	public User insertUser(User user) {
		if(user != null) {
		emailService.sendEmailForNewRegistration(user.getEmail());
		String credential = user.getPassword();
		Encoder encoder = Base64.getEncoder();
		String encoderstr = encoder.encodeToString(credential.getBytes());
		user.setPassword(encoderstr);
		
		}
		else {
			throw new RuntimeException("Invalid USer");
			
		}
		return userDao.save(user);	
	}

	@Override
	public List<User> getUserByEmailAndPassword(String email,String password) {
//		Decoder decoder = Base64.getDecoder();
//		byte[] decode = decoder.decode(password.getBytes());
//		String decodestr = new String(decode);
		String credential = password;
		Encoder encoder = Base64.getEncoder();
		String encoderstr = encoder.encodeToString(credential.getBytes());
		
		return userDao.findByEmailAndPassword(email,encoderstr);
	}
	
	@Override
	public List<User> findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public List<User> findByPhone(String phone) {
		return userDao.findByPhone(phone);
	}
	
	@Override
	public List<User> getAllUsers() {
		
		return this.userDao.findAll();
	}

	@Override
	public List<User> forgetPassword(String email,String securityQues, String securityAns) {
		return userDao.findByEmailAndSecurityQuesAndSecurityAns(email,securityQues, securityAns);
	}

	@Override
	public void deleteUser(String email) {
		User user =  userDao.getById(email);
		userDao.delete(user);	
	}

	@Override
	public User updatePassword(User user) {
		User u = userDao.getById(user.getEmail());
		String credential = user.getPassword();
		Encoder encoder = Base64.getEncoder();
		String encoderstr = encoder.encodeToString(credential.getBytes());
		u.setPassword(encoderstr);
		return userDao.save(u);
	}
//	@Override
//	public User updatePassword(User user) {
//		//user.setPassword(user.getPassword());
//		//user = userDao.getById(user.getEmail());
//		return userDao.save(user);
//	}

	
	
	
}
