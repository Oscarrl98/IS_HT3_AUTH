package gt.edu.umg.sistemas.ingsoftware.hojatrabajo3.interfaces;

public interface ITokenAction {
    public String createJWT( String subject,String passw);
    public void parseJWT(String jwt);
}
