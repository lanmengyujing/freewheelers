package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.security.PasswordEncoding;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ServiceResult;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/account")
@SessionAttributes("account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private PasswordEncoding passwordEncoding;

    public AccountController() {
        this.accountService = new AccountServiceImpl();

    }

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/terms", method = RequestMethod.GET)
    public String showTerms() {
        return "account/termsAndConditions";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(Model model) {

        return new ModelAndView("account/create", "validationMessage", model).addObject("country", Country.values());
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(@ModelAttribute Account account) throws IOException {
        this.passwordEncoding = new PasswordEncoding(passwordEncoder);
        account.setPassword(passwordEncoding.encode(account.getPassword()));

        try {
            ServiceResult<Account> result = accountService.createAccount(account);

            if (result.hasErrors()) {
                return showErrorsForAccount(result.getErrors(), "account/create");
            }

            return showSuccess(result.getModel());
        } catch (Exception e) {
            e.printStackTrace();
            return showError();
        }
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"/editDetails"}, method = RequestMethod.GET)
    public String createEditForm(Model model, Principal principal) {
        Account account = accountService.getAccountIdByName(principal.getName());
        model.addAttribute("account", account).addAttribute("country", Country.values());
        return "account/editDetails";
    }

    @ModelAttribute("account")
    public Account getAccount() {
        return new Account();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = {"/editDetails"}, method = RequestMethod.POST)
    public String editUserDetails(@ModelAttribute("account") Account servedAccount, Principal principal, Model model) {
        Account account = getAccount(servedAccount, principal);

        ServiceResult<Account> result = accountService.editUserDetails(account);

        if (result.hasErrors()) {
            model.addAttribute("errors", result.getErrors()).addAttribute("country", Country.values());
            return "account/editDetails";
        }


        return "redirect:" + "../userProfile";
    }

    private Account getAccount(Account servedAccount, Principal principal) {
        Account account = accountService.getAccountIdByName(principal.getName());

        account.setAddress(servedAccount.getAddress());
        account.setEmailAddress(servedAccount.getEmailAddress());
        account.setPhoneNumber(servedAccount.getPhoneNumber());
        account.setAcceptedTerms("on");
        return account;
    }

    protected ModelAndView showErrorsForAccount(Map errors, String view) {
        ModelMap model = new ModelMap();
        model.put("errors", errors);

        return new ModelAndView(view, model).addObject("country", Country.values());
    }

    private ModelAndView showError() {
        return new ModelAndView("account/createFailure");
    }

    private ModelAndView showSuccess(Account account) {
        ModelMap model = new ModelMap();
        model.put("name", account.getAccount_name());
        return new ModelAndView("account/createSuccess", "postedValues", model);
    }

    public void setPasswordEncoding(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
