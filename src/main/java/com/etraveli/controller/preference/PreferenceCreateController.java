package com.etraveli.controller.preference;

import com.etraveli.helper.ApplicationCustomException;
import com.etraveli.model.Preference;
import com.etraveli.model.PreferenceID;
import com.etraveli.model.User;
import com.etraveli.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@SessionAttributes("preference")
public class PreferenceCreateController {
    @Autowired
    private PreferenceService preferenceService;

    @RequestMapping(value = "/createPreference", method = RequestMethod.GET)
    public String startCreatingNewPreference(Model model) {
        model.addAttribute("preference", new Preference());
        return "preferenceCreate";
    }

    @RequestMapping(value = "/createPreferenceFailed", method = RequestMethod.GET)
    public String createPreferenceFailed() {
        return "preferenceCreate";
    }

    @RequestMapping(value = "/createPreference", method = RequestMethod.POST)
    public String performCreate(@ModelAttribute Preference preference, RedirectAttributes redirectAttributes,
                                SessionStatus sessionStatus) {
        String message = null;
        String viewName = null;
        try {
            preferenceService.create(preference);
            message = "Preference created. User id :" + preference.getUserId();
            viewName = "redirect:/mvc/listPreferences";
            sessionStatus.setComplete();
        } catch (Exception ex) {
            message = "Preference create failed.";
            viewName = "redirect:/mvc/createPreferenceFailed";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return viewName;
    }


}
