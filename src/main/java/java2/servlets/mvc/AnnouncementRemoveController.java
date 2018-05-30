package java2.servlets.mvc;

import java2.businesslogic.announcementremoval.AnnouncementRemovalRequest;
import java2.businesslogic.announcementremoval.AnnouncementRemovalResponse;
import java2.businesslogic.announcementremoval.AnnouncementRemovalService;
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
public class AnnouncementRemoveController {
    private static Logger logger = Logger.getLogger(AnnouncementRemoveController.class.getName());

    @Autowired private AnnouncementRepository announcementRepository;
    @Autowired private AnnouncementRemovalService announcementRemovalService;

    @RequestMapping(value = "announcementRemove", method = {RequestMethod.GET})
    public ModelAndView announcementRemoveGet(HttpServletRequest request) {
        logger.info("announcementRemove GET called.");
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
            return new ModelAndView("announcementRemove", "model", model);
        }
        return new ModelAndView("userLogin", "model", null);
    }

    @RequestMapping(value = "announcementRemove", method = {RequestMethod.POST})
    public ModelAndView announcementRemovePost(HttpServletRequest request) {
        logger.info("announcementRemove POST called.");
        HttpSession session = request.getSession();
        if(!session.getAttribute("role").toString().equals("U")) {
            return new ModelAndView("userLogin", "model", null);
        }
        String login = session.getAttribute("login").toString();
        int id = Integer.parseInt(request.getParameter("id"));

        AnnouncementRemovalRequest announcementRemovalRequest =
                new AnnouncementRemovalRequest(login, id);
        AnnouncementRemovalResponse response =
                announcementRemovalService.remove(announcementRemovalRequest);

        if(response.isSuccess()) {
            Map<String, Object> model = new HashMap<>();
            model.put("announcement", null);
            model.put("response", response);
            return new ModelAndView("announcementRemove", "model", model);
        } else {
            Optional<Announcement> announcement =
                    announcementRepository.findById(id);
            if(announcement.isPresent()) {
                Map<String, Object> model = new HashMap<>();
                model.put("announcement", announcement.get());
                model.put("response", response);
                return new ModelAndView("announcementRemove", "model", model);
            }
            return new ModelAndView("userLogin", "model", null);
        }
    }
}
