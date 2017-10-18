package pl.gda.wsb.markiet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gda.wsb.markiet.db.Offer;
import pl.gda.wsb.markiet.db.OfferRepository;
import pl.gda.wsb.markiet.services.EmailService;

/**
 * http requests and view names
 * 
 * @author Piotr Czapiewski
 */
@Controller
public class DefaultController {
    
    private static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    OfferRepository offerRepository;
    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reset")
    public String reset() {
        logger.info("password reset");
        emailService.resetPassword(emailService.getTestEmail());
        return "redirect:login?reset";
    }

    @GetMapping("/signup")
    public String signup() {
        logger.info("signup");
        emailService.signUp(emailService.getTestEmail());
        return "redirect:login?signup";
    }

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }
    
    @GetMapping("/addOffer")
    public String newOffer(Offer offer){
        return "addOffer";
    }
    
    @PostMapping("/addOffer")
    public String saveOffer(@Valid Offer offer, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "addOffer";
        }
        offerRepository.save(new Offer(offer.getDescription(),offer.getCategory()));
        logger.info("New offer has been created");
        model.addAttribute("offers", offerRepository.findAll());
        return "redirect:offers";
    }
    
    @GetMapping("/offers")
    public String showOffers(Model model){
        model.addAttribute("offers", offerRepository.findAll());
        return "offers";
    }
}
