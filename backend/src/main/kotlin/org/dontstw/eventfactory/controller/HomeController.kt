package org.dontstw.eventfactory.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import javax.annotation.PostConstruct

@Controller
class HomeController{
    @GetMapping("/")
    fun app(): String {
        return "app"
    }
}