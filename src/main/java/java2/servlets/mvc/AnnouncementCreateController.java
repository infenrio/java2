package java2.servlets.mvc;

import java2.businesslogic.announcementcreation.AnnouncementCreationRequest;
import java2.businesslogic.announcementcreation.AnnouncementCreationResponse;
import java2.businesslogic.announcementcreation.AnnouncementCreationService;
import java2.database.AnnouncementCategoryRepository;
import java2.domain.AnnouncementCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class AnnouncementCreateController {
    private static Logger logger = Logger.getLogger(AnnouncementCreateController.class.getName());

    @Autowired private AnnouncementCategoryRepository announcementCategoryRepository;
    @Autowired private AnnouncementCreationService announcementCreationService;

    @RequestMapping(value = "announcementPickCategory", method = {RequestMethod.GET})
    public ModelAndView announcementPickCategoryGet(HttpServletRequest request) {
        logger.info("announcementPickCategory GET called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("U")) {
            return new ModelAndView("userLogin", "model", null);
        }

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        List<AnnouncementCategory> categories =
                announcementCategoryRepository.getCategoriesByParentCategoryId(categoryId);
        return new ModelAndView("announcementPickCategory", "model", categories);
    }

    @RequestMapping(value = "announcementCreate", method = {RequestMethod.GET})
    public ModelAndView announcementCreateGet(HttpServletRequest request) {
        logger.info("announcementCreate GET called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("U")) {
            return new ModelAndView("userLogin", "model", null);
        }

        Map<String, Object> model = new HashMap<>();
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        model.put("categoryId", categoryId);
        model.put("response", null);
        return new ModelAndView("announcementCreate", "model", model);
    }

    @RequestMapping(value = "announcementCreate", method = {RequestMethod.POST})
    public ModelAndView announcementCreatePost(HttpServletRequest request) {
        logger.info("announcementCreate POST called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("U")) {
            return new ModelAndView("userLogin", "model", null);
        }
        String login = session.getAttribute("login").toString();
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        AnnouncementCreationRequest announcementCreationRequest =
                new AnnouncementCreationRequest(categoryId, title, description, login);
        AnnouncementCreationResponse response =
                announcementCreationService.create(announcementCreationRequest);

        Map<String, Object> model = new HashMap<>();
        model.put("categoryId", categoryId);
        model.put("response", response);

        return new ModelAndView("announcementCreate", "model", model);
    }
}
