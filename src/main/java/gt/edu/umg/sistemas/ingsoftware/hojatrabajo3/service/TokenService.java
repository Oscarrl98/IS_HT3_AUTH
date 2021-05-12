package gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.service;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.entity.ValuesymlEntity;
import gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.interfaces.ITokenAction;
import io.jsonwebtoken.*;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements ITokenAction {

    /**
     * Obtenemos los valores del yml
     */
    @Autowired
    private ValuesymlEntity ymlProperties;

    //private String secretKey="qwertyuiopasdfghjklzxcvbnm123456";
    long ttlMillis=60000;

     String issuer="IS";
     String id="ISHT3";

     @Override
    public String createJWT( String subject,String passw) {
        /**
         * Definimos el algoritmo de encriptacion y creamos valor de tiempo en el que es creado el token
         */
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        /**
         * Uilizamos la clave secreta para encriptar los valores obrenidos
         * JwtBuilder construye un json encriptado que contiene los valores
         */
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(ymlProperties.getSecretkey());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .claim("pswr", passw)
                .signWith(signatureAlgorithm, signingKey);
        /**
         * Validacion de expiracion
         */
        if (ymlProperties.getExpire_lenght() >= 0) {
            long expMillis = nowMillis + ymlProperties.getExpire_lenght();
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }


    /**
     * Metodo par desencriptar el token , mostramos el usuario ,fecha de vencimiento y creacion
     * @param jwt token encriptado
     */

    @Override
    public void parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(ymlProperties.getSecretkey()))
                .parseClaimsJws(jwt).getBody();
        System.out.println("BIENVENIDO "+claims.getSubject());

        System.out.println("ID: " + claims.getId());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }
}
