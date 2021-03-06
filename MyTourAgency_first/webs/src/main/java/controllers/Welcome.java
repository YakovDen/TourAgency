/**
 * 
 */
package controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for enter to index page and authorization
 *
 */
@Controller
public class Welcome {
	final Logger log = Logger.getLogger(Welcome.class);

	@RequestMapping(value = "/welcome", method = { RequestMethod.POST, RequestMethod.GET })
	public String index(ModelMap model, HttpServletRequest request) {
		return "welcome";
	}
}
