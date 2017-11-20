package com.github.java4wro.user;


import com.github.java4wro.user.dto.RegisterUserDTO;
import com.github.java4wro.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    List<UserDTO> getAll(){
        return userService.getAll();
    }
    @PostMapping(value = "/register")
    public RegisterUserDTO addUser (@RequestParam ("password") String password, @RequestParam("confirmedPassword") String confirmedPassword, RegisterUserDTO registerUserDTO) {
        userService.validationOfPasswordIdenitiy(password, confirmedPassword);
        return userService.addUser(registerUserDTO);
    }

    @GetMapping(value = "/confirmRegistration")
    void confirmRegistration(@RequestParam("token") String token) {
        userService.confirmRegistration(token);
    }

    @PostMapping(value = "/forgotPassword")
    void sendEmailWhenForgotPassword (@RequestParam("email") String email, @RequestParam ("newPassword") String newPassword, @RequestParam ("confirmNewPassword") String confirmNewPassword) {
        userService.sendEmailWhenForgotPassword(email, newPassword, confirmNewPassword);
    }
    @GetMapping(value = "/forgotPassword")
    void changePasswordsWhenForgot(@RequestParam("token") String token){
        userService.changePasswordsWhenForgot(token);
    }

    @PostMapping(value = "/resetPassword")
    void sendEmailWhenResetPassword (@RequestParam("email") String email, @RequestParam ("newPassword") String newPassword, @RequestParam ("confirmNewPassword") String confirmNewPassword) {
        userService.sendEmailWhenResetPassword(email, newPassword, confirmNewPassword);
    }
    @GetMapping(value = "/resetPassword")
    void changePasswordsWhenReset(@RequestParam("token") String token){
        userService.changePasswordsWhenReset(token);
    }

}
