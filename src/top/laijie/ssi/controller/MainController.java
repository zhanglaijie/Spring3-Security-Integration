package top.laijie.ssi.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {
	protected static Logger logger = Logger.getLogger(MainController.class);
	
	/**
	 * 跳转到commonPage界面
	 */
	@RequestMapping(value = "/common",method = RequestMethod.GET)
	public String getCommonPage(){
		logger.debug("welcome to commonPage");
		return "commonPage";
	}
	/**
	 * 跳转到管理页面
	 * @return
	 */
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public String getAdminPage(){
		logger.debug("welcome to adminPage");
		return "adminPage";
	}
}
