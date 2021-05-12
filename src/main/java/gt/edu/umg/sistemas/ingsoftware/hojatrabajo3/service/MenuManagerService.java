package gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MenuManagerService {

@Autowired
private TokenService tokenService;

    public void GenerateToken(String name, String password){
        String token;
        token=tokenService.createJWT(name, password);
        System.out.println(token);
        tokenService.parseJWT(token);

    }
}
