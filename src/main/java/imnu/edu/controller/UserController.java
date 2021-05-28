package imnu.edu.controller;

import imnu.edu.pojo.User;
import imnu.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/index";
        } else {
            return "login";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(defaultValue = "off") String remember,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            userService.updateLoginTime(username);
            return "redirect:/";
        } else {
            attributes.addFlashAttribute("msg", "用户名密码错误!!!");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password1,
                           @RequestParam String password2,
                           @RequestParam(defaultValue = "请改名") String nickname,
                           @RequestParam String email,
                           RedirectAttributes attributes
    ) {
        if (userService.checkUsername(username)) {
            attributes.addFlashAttribute("msg", "用户名已存在");
            return "redirect:/user/register";
        }
        if (userService.checkEmail(email)) {
            attributes.addFlashAttribute("msg", "邮箱已存在");
            return "redirect:/user/register";
        }
        if (!password1.equals(password2)) {
            attributes.addFlashAttribute("msg", "两次密码不一致");
            return "redirect:/user/register";
        }
        boolean b = userService.registerUser(username, nickname, email, password1);
        if (b) {
            return "redirect:/user/login";
        } else {
            return "register";
        }
    }

}
