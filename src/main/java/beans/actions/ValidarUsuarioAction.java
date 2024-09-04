
package beans.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.*;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
/*Valida dos posibls resultados dependiendo lo que arroje la classe login action
de los dos posobles resultados dependera si nos redirecciona ala clase bienvenida o 
de regreso a login todo dependera del resultado que arroje la anotacion validar usuario
*/
@Results({
    @Result(name="success", location="/WEB-INF/content/bienvenido.jsp"),
    @Result(name="input", location="/WEB-INF/content/login.jsp")})

public class ValidarUsuarioAction extends ActionSupport {
    Logger log = LogManager.getLogger(LoginAction.class);
    private String usuario;
    private String password;
    
    @Action ("validarUsuario")
    
    public String execute(){
    if ("admin".equals(this.usuario)){
        addActionMessage(getText("usuario.valido"));
        return SUCCESS;
    } else {
    return INPUT;
    }
    
   
    
    }
    
    /*
    Se realizan validaciones las cuales dependiendo la caracteristicas que cumplan 
    mandara un mensaje ya se que este mal el usuario o la contraseña le falten caracteres 
    mosntrara mensajes de advertencia, en dado caso que cumplan con todo los parametros 
    el usuario y la contraseña mandara al jsp bienvenidos.
    */
     @Override
    public void validate(){
    if(this.usuario== null || "".equals(this.usuario.trim())){
    addFieldError("usuario",getText("val.usuario"));
    
    }
    else if(!usuarioValido()){
        
        addActionError(getText("usuario.invalido"));
    
    }
    if(this.password== null || "".equals(this.password.trim())){
    addFieldError("password",getText("val.password"));
    
    } 
    else if(this.password.length()<3){
    addFieldError("password",getText("val.pass.min.lenght"));
    
    }
    }

    private boolean usuarioValido(){
    
    return "admin".equals(this.usuario);
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
