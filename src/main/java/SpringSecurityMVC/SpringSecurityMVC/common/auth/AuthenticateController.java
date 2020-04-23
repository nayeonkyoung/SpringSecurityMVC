package SpringSecurityMVC.SpringSecurityMVC.common.auth;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import SpringSecurityMVC.SpringSecurityMVC.common.Person;
import SpringSecurityMVC.SpringSecurityMVC.controller.BaseController;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AuthenticateController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticateController.class);
	public static final String URI = "/common/auth/adm";
	public static final String LOGIN = "/home";
	public static final String ACCESSDENIED = "/accessDenied";

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "common/auth/home";
	}

	@RequestMapping("/admLogout")
	public String admLogout() {
		return "pub/admLogout";
	}

	@RequestMapping("/pubLogout")
	public String pubLogout() {
		return "pub/pubLogout";
	}

	@RequestMapping("/superLogout")
	public String superLogout() {
		return "pub/superLogout";
	}

	@RequestMapping("/adm")
	public String forGuest() {
		logger.info("admin");
		return "adm/adm";
	}

	/*
	 * @RequestMapping(value = "/test", method = RequestMethod.POST)
	 * public @ResponseBody List<Person> data(@RequestBody List<Person> person) {
	 * System.out.println("hi!"); System.out.println(person); return person; }
	 */
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody Person data(@RequestBody Person person) {
		System.out.println("hi!");
		System.out.println(person);
		return person;
	}

	@RequestMapping("/pub")
	public String forMember() {
		logger.info("public");
		return "pub/pub";
	}

	@RequestMapping("/super")
	public String forManager() {
		logger.info("super");
		return "super/super";
	}

	@RequestMapping("/{userId}/{nickId}/service")
	public String characterInfo(@PathVariable String userId, @PathVariable String nickId, ModelMap model) {
		model.addAttribute("userId", userId);
		model.addAttribute("nickId", nickId);
		return "nyk/hello";
	}

	@RequestMapping("/nyk/hi")
	public String forSitemesh() {
		logger.info("sitemesh");
		return "decorators/defaultDecorator";
	}

	@GetMapping("/hi/params")
	@ResponseBody
	public String getFoos(@RequestParam List<String> id) {
		return "IDs are " + id;
	}

	@RequestMapping("/bye/tiles")
	public String testPage() {
		return "test.page";
	}

}
