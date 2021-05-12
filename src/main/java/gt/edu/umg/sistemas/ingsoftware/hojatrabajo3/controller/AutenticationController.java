package gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.controller;

import gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.service.MenuManagerService;
import gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticationController {

    @Autowired
    private MenuManagerService menuManagerService;

    @RequestMapping("/auth")
    public void AuthUser(@RequestParam(value="name", defaultValue="World") String name, @RequestParam String password) {
        menuManagerService.GenerateToken(name, password);
    }
}
