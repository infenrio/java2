package java2.servlets.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class HomeController {
    private static Logger logger = Logger.getLogger(HomeController.class.getName());

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView homeGet(HttpServletRequest request) {
        logger.info("/ GET called.");
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("home", "model", null);
    }
}
