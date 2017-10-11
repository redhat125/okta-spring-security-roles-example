package com.okta.okta.spring.example.controller;

/*
Copyright 2017 Okta, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import com.okta.okta.spring.example.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class SecureController {

    @Autowired
    protected AppProperties appProperties;

    @RequestMapping("/authenticated")
    public String authenticated(Principal principal, Model model) {
        model.addAttribute("appProperties", appProperties);
        model.addAttribute("principal", principal);
        return "authenticated";
    }

    @RequestMapping("/users")
    @PreAuthorize("hasAuthority('users')")
    public String users() {
        return "roles";
    }

    @RequestMapping("/admins")
    @PreAuthorize("hasAuthority('admins')")
    public String admins() {
        return "roles";
    }

    @RequestMapping("/403")
    public String error403() {
        return "403";
    }
}
