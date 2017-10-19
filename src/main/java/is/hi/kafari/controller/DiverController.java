package is.hi.kafari.controller;

import is.hi.kafari.model.Dive;
import is.hi.kafari.model.Diver;
import is.hi.kafari.services.KafariService;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Einar
 * date: september 2017
 *
 * Tekur við skipunum frá vefviðmóti til að skrá nýjar dýfur, 
 * sýna upplýsingar um allar dýfur og sýna alla notendur
 */
@Controller
@RequestMapping("") // Notice here that the Request Mapping is set at the Class level
public class DiverController {

    // Tenging yfir í þjónustu klasa fyrir forritið 
    @Autowired
    KafariService kafariService;
    
    @Autowired
    LoginController loginController;
    
   /**
     * Birtir síðu með formi til að skrá dýfu
     *
     * @param diver kafari
     * @param model síðumodel
     * @return síða til að skrá dýfu
     */        
    @RequestMapping(value = "/diveForm", method = {RequestMethod.POST, RequestMethod.GET})
    public String diveForm(@ModelAttribute("diver") Diver diver,
            ModelMap model) {
        model.addAttribute("diver", loginController.currentDiver);
        return "diveForm";
    }
    
    /**
     * Birtir síðu með öllum dýfum
     *
     * @param model síðumodel
     * @return síða með öllum dýfum fyrir núverandi notanda
     */    
    @RequestMapping(value = "/showAllDives", method = RequestMethod.GET)
    public String showAllDives(Model model) {
        ArrayList<Dive> list = new ArrayList(loginController.currentDiver.getDives());
        model.addAttribute("dives", list);
        return "showAllDives";
    }

    
   /**
     * Sendir notanda aftur í aðalvalmynd
     *
     * @return síða með upplýsingum um notanda
     */       
    @RequestMapping(value = "/logDive", method = RequestMethod.POST)
    public String logDive() {
        return "showDiver";
    }
    
    /**
     * Skráir dýfu í gagnagrunn og birtir yfirlit yfir dýfu
     * 
     * @param location
     * @param time
     * @param depth
     * @param model
     * @return 
     */
    @RequestMapping(value = "/calculateDecompression", method = RequestMethod.POST)
    public String calculateDecompression(@RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "time", required = false) String time,
            @RequestParam(value = "depth", required = false) String depth,
            ModelMap model) {
        // log dive to database!
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int timeInt = Integer.parseInt(time);
        int depthInt = Integer.parseInt(depth);
        String letter = TableLookupController.getLetter(depthInt, timeInt);
        String decompression = TableLookupController.getDecompressionString(depthInt, timeInt);
        Dive dive = new Dive(loginController.currentDiver, ts, location, timeInt, depthInt, decompression, letter);
        kafariService.addDive(dive);
        model.addAttribute("letter", letter);
        model.addAttribute("decompression", decompression);
        model.addAttribute("depth", depth);
        model.addAttribute("time", time);
        model.addAttribute("location", location);
        return "showDive";
    }
    
    /**
     * Birtir síðu með öllum köfurum
     *
     * @param model síðumodel
     * @return síða með öllum köfurum í kerfinu
     */    
    @RequestMapping(value = "/allDivers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        ArrayList<Diver> list;
        list = (ArrayList<Diver>) kafariService.allDivers();
        model.addAttribute("divers", list);
        return "allDivers";
    }
    

}
