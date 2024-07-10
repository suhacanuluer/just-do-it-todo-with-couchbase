package org.suhacan.justdoit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class SwaggerRedirectController {

    @GetMapping(value = { "/", "/swagger-ui", "/swagger-ui/" })
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public RedirectView redirect() {
        return new RedirectView("/swagger-ui/index.html");
    }
}
