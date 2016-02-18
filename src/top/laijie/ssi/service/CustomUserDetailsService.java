package top.laijie.ssi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import top.laijie.ssi.dao.UserDao;
import top.laijie.ssi.domain.DbUser;

/**
 * 一个自定义的service用来和数据库进行操作.即以后我们要通过数据库保存权限,则需要我们继承UserDetailsService
 * 
 * @author laijie
 *
 */
public class CustomUserDetailsService implements UserDetailsService{
	protected static Logger logger = Logger.getLogger(CustomUserDetailsService.class);
	private UserDao userDao = new UserDao();
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails user = null;
		try{
			//搜索数据库以匹配用户登录名
			//我们可以通过dao 使用JDBC来访问数据库
			DbUser dbUser = userDao.getDatabase(username);
			user = new User(dbUser.getUsername(), dbUser.getPassword().toLowerCase(), 
					true, true, true, true, getAutorities(dbUser.getAccess()));
		}catch(Exception e){
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		// TODO Auto-generated method stub
		return user;
	}
	/**
	 * 获取访问角色权限
	 * @param access
	 * @return
	 */
	private Collection<GrantedAuthority> getAutorities(Integer access) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		//所有的用户默认拥有ROLE_USER权限
		logger.debug("Grand ROLE_USER to this user");
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		//如果参数access为1.则拥有ROLE_ADMIN权限
		if(access.compareTo(1)==0){
			logger.debug("Grant ROLE_ADMIN to this user");
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		return authList;
	}

}
