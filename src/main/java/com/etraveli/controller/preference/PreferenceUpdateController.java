package com.etraveli.controller.preference;

import com.etraveli.model.Preference;
import com.etraveli.model.PreferenceID;
import com.etraveli.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("preference")
public class PreferenceUpdateController {
    @Autowired
    private PreferenceService preferenceService;

    @RequestMapping(value = "/updatePreference/{id}/{city}", method = RequestMethod.GET)
    public String selectForUpdate(@PathVariable Long id,@PathVariable String city, Model model) {

            PreferenceID preferenceID = new PreferenceID();
        preferenceID.setUserId(id);
        preferenceID.setCity(city);
        Preference byId = preferenceService.findById(preferenceID);
        model.addAttribute("preference", byId);
        return "preferenceUpdate";
    }

    @RequestMapping(value="/updatePreferenceFailed", method=RequestMethod.GET)
    public String updatePreferenceFailed() {
        return "preferenceUpdate";
    }

    @RequestMapping(value = "/updatePreference", method = RequestMethod.POST)
    public String performUpdate(@ModelAttribute Preference preference, RedirectAttributes redirectAttributes,
                                SessionStatus sessionStatus) {
        String message = null;
        String viewName = null;
        try {
            preferenceService.update(preference);
            message = "Preference updated.For User id :" + preference.getUserId();
            viewName = "redirect:/mvc/listPreferences";
            sessionStatus.setComplete();
        } catch (Exception ex) {
            message = "Preference update failed.";
            viewName = "redirect:/mvc/updatePreferenceFailed";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return viewName;
    }
}
