package gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.entity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "token", ignoreUnknownFields = false)
//@Configuration("ymlProperties")
public class ValuesymlEntity {

    private String secretkey;
    private int expire_lenght;

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public int getExpire_lenght() {
        return expire_lenght;
    }

    public void setExpire_lenght(int expire_lenght) {
        this.expire_lenght = expire_lenght;
    }
}
