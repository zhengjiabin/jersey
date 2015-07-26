package bean;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestContact {
    private Client client;
    
    @Before
    public void before() {
        ClientConfig config = new DefaultClientConfig();
        this.client = Client.create(config);
        
    }
    
    @Test
    public void testJersey() {
        String url = "http://localhost:8080/jersey/rest/hello";
        WebResource resource = client.resource(url);
        // Fluent interfaces  
        Builder builder = resource.accept(MediaType.TEXT_PLAIN);
        ClientResponse clientResponse = builder.get(ClientResponse.class);
        System.out.println(clientResponse.toString());
        
        //get plain text
        builder = resource.accept(MediaType.TEXT_PLAIN);
        String sayHello = builder.get(String.class);
        System.out.println(sayHello);
        
        //get XML
        builder = resource.accept(MediaType.TEXT_XML);
        String sayXMLHello = builder.get(String.class);
        System.out.println(sayXMLHello);
        
        // get HTML
        builder = resource.accept(MediaType.TEXT_HTML);
        String sayHtmlHello = builder.get(String.class);
        System.out.println(sayHtmlHello);
    }
}
