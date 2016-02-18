package top.laijie.ssi.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import top.laijie.ssi.domain.DbUser;

public class UserDao {
	protected static Logger logger = Logger.getLogger(UserDao.class);
	public DbUser getDatabase(String username){
		List<DbUser> users = internalDatabse();
		for(DbUser dbUser:users){
			if(dbUser.getUsername().equals(username) ==true){
				logger.debug("user found");
				return dbUser;
			}
		}
		logger.error("User does not exist!");
		throw new RuntimeException("User does not exist!");
	}
	/**
	 * 初始化数据
	 * @return
	 */
	private List<DbUser> internalDatabse() {
		List<DbUser> users = new ArrayList<DbUser>();
		DbUser user = null;
		user = new DbUser();
		user.setUsername("admin");
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		user.setAccess(1);
		users.add(user);
		
		user = new DbUser();
		user.setUsername("user");
		user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		user.setAccess(2);
		users.add(user);
		return users;
	}
}
