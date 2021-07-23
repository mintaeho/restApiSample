package com.nuritech.excs.restapi.user.web;

import com.nuritech.excs.restapi.user.dto.SignInRequest;
import com.nuritech.excs.restapi.user.dto.SignInResponse;
import com.nuritech.excs.restapi.user.dto.SignUpRequest;
import com.nuritech.excs.restapi.user.dto.SignUpResponse;
import com.nuritech.excs.restapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/index")
    public String index(Model model) {
        List<SignUpResponse> users = userService.findAllDesc();

        log.debug(">>>> users.size = {}", users.size());

        model.addAttribute("users", users);
        return "index";
    }
}
