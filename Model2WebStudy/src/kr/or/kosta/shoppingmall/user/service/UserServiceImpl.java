package kr.or.kosta.shoppingmall.user.service;

import java.util.List;

import kr.or.kosta.shoppingmall.common.db.DaoFactory;
import kr.or.kosta.shoppingmall.common.web.Params;
import kr.or.kosta.shoppingmall.user.dao.JdbcUserDao;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = (UserDao)DaoFactory.getInstance().getDao(JdbcUserDao.class);

	@Override
	public List<User> listAll() {
		return userDao.listAll();
	}
	
	@Override
	public void regist(User user) {
		userDao.create(user);
		
	}

	@Override
	public User isMember(String id, String passwd) {
		return userDao.isMember(id, passwd);
	}

	@Override
	public List<User> listByParams(Params params) {
		return userDao.listByParams(params);
	}

	@Override
	public int pageCount(Params params) {
		return userDao.pageCount(params);
	}

	@Override
	public User read(String id) {
		User user=userDao.read(id);
		return user;
	}

	@Override
	public int deleteUser(String id) {
		int checkDelete=userDao.deleteUser(id);
		return checkDelete;
	}

}







