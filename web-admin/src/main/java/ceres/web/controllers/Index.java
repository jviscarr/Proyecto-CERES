/**
 * 
 */
package ceres.web.controllers;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import orion.lib.jpa.entities.Usuario;

/**
 * @author jviscarr
 *
 */
@ManagedBean
@Named("index")
@SessionScoped
public final class Index extends IndexAbs implements Serializable{
	private static final long serialVersionUID = -6191726144481428291L;
	
	private final HttpClient client = HttpClient.newHttpClient();
	private final static String ENDPOINT_USUARIO_API = "http://localhost:8080/web-api-rest-security/usuarioApi";
//	private final HttpRequest reqConsultaXUsuario = HttpRequest.newBuilder()
//			.GET()
//			.uri( URI.create("http://localhost:8080/web-api-rest-security/usuarioApi/consultas") )
//			.build(); 
	
	public void ejemploConsumoRest() {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri( URI.create(ENDPOINT_USUARIO_API) )
				.build();
		
		try {
			HttpResponse<String> response = client.send( request,BodyHandlers.ofString() );
			System.out.println( response.body() );
//			Usuario u = new Gson().fromJson(response.body(), Usuario.class);
//			System.out.println(u.getUsuario());
			
			Type listType = new TypeToken <ArrayList<Usuario>>(){}.getType();
			List<Usuario> list = new Gson().fromJson(response.body(), listType);
			for(Usuario u:list) {
				System.out.println(u.getUsuario());
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String actionLogin() {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri( URI.create(ENDPOINT_USUARIO_API+"/consultas/xusuario?usuario="+getUser()) )
				.build();
		
		try {
			HttpResponse<String> response = client.send( request,BodyHandlers.ofString() );
			System.out.println( response.body() );
			Usuario u = new Gson().fromJson(response.body(), Usuario.class);
			System.out.println(u.getUsuario());
			
			if(u.getClave().equals(getPassword())) {
				return "/test?faces-redirect=true";
			}else {
				System.out.println( "Acceso denegado por contraseña incorrecta" );
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println( "Acceso denegado por error presentado" );
			return null;
		}
		
		return null;
	}
}

