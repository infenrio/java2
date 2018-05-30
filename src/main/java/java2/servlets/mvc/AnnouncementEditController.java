package java2.servlets.mvc;

import java2.businesslogic.announcementediting.AnnouncementEditingRequest;
import java2.businesslogic.announcementediting.AnnouncementEditingResponse;
import java2.businesslogic.announcementediting.AnnouncementEditingService;
import java2.database.AnnouncementRepository;
import java2.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class AnnouncementEditController {
    private static Logger logger = Logger.getLogger(AnnouncementEditController.class.getName());

    @Autowired private AnnouncementRepository announcementRepository;
    @Autowired private AnnouncementEditingService announcementEditingService;

    @RequestMapping(value = "announcementEdit", method = {RequestMethod.GET})
    public ModelAndView announcementEditGet(HttpServletRequest request) {
        logger.info("announcementEdit GET called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("U")) {
            return new ModelAndView("userLogin", "model", null);
        }

        int announcementId = Integer.parseInt(request.getParameter("id"));
        Optional<Announcement> announcement =
                announcementRepository.findById(announcementId);
        if(announcement.isPresent()) {
            Map<String, Object> model = new HashMap<>();
            model.put("announcement", announcement.get());
            model.put("response", null);
            return new ModelAndView("announcementEdit", "model", model);
        }
        return new ModelAndView("userLogin", "model", null);
    }

    @RequestMapping(value = "announcementEdit", method = {RequestMethod.POST})
    public ModelAndView announcementEditPost(HttpServletRequest request) {
        logger.info("announcementEdit POST called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("U")) {
            return new ModelAndView("userLogin", "model", null);
        }
        String login = session.getAttribute("login").toString();
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        AnnouncementEditingRequest announcementEditingRequest =
                new AnnouncementEditingRequest(id, title, description, login);
        AnnouncementEditingResponse response =
                announcementEditingService.edit(announcementEditingRequest);

        Optional<Announcement> announcement =
                announcementRepository.findById(id);
        if(announcement.isPresent()) {
            Map<String, Object> model = new HashMap<>();
            model.put("announcement", announcement.get());
            model.put("response", response);
            return new ModelAndView("announcementEdit", "model", model);
        }
        return new ModelAndView("userLogin", "model", null);
    }
}
