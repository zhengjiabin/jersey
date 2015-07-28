package client;

import java.io.ByteArrayInputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import bean.Contact;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TestClient {
    private Client client;
    
    @Before
    public void before() {
        ClientConfig config = new DefaultClientConfig();
        this.client = Client.create(config);
    }
    
    /**
     * 测试@PathParam
     */
    @Test
    public void testGet1() {
        String url = "http://localhost:8080/jersey/rest/contact/getContact1/1";
        WebResource resource = client.resource(url);
        
        Contact contact = resource.get(Contact.class);
        
        //        GenericType<JAXBElement<Contact>> generic = new GenericType<JAXBElement<Contact>>() {
        //        };
        //        Builder builder = resource.accept(MediaType.APPLICATION_XML);
        //        JAXBElement<Contact> jaxbContact = builder.get(generic);
        //        Contact contact = jaxbContact.getValue();
        
        System.out.println(contact.toString());
    }
    
    /**
     * 测试@QueryParam
     */
    @Test
    public void testGet2() {
        String url = "http://localhost:8080/jersey/rest/contact/getContact2";
        WebResource resource = client.resource(url);
        
        //WebResource queryParams = resource.queryParam("id", "1");
        
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("id", "1");
        WebResource queryParams = resource.queryParams(params);
        
        Contact contact = queryParams.get(Contact.class);
        
        System.out.println(contact.toString());
    }
    
    /**
     * 测试传参实体
     */
    @Test
    public void testPost1() {
        String url = "http://localhost:8080/jersey/rest/contact/getContact3";
        WebResource resource = client.resource(url);
        
        Contact contact = new Contact();
        contact.setId("1");
        
        Builder builder = resource.entity(contact, MediaType.APPLICATION_JSON);
        String response = builder.post(String.class);
        
        System.out.println(response);
    }
    
    /**
     * 测试@FormParam
     */
    @Test
    public void testPost2() {
        String url = "http://localhost:8080/jersey/rest/contact/getContact4";
        WebResource resource = client.resource(url);
        
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("id", "1");
        
        Builder builder = resource.type(MediaType.APPLICATION_FORM_URLENCODED);
        Contact contact = builder.post(Contact.class, params);
        
        System.out.println(contact.toString());
    }
    
    /**
     * 测试entity实体传参
     */
    @Test
    public void testPost3() {
        String url = "http://localhost:8080/jersey/rest/contact/getContact5";
        WebResource resource = client.resource(url);
        
        Contact contact = new Contact();
        contact.setId("1");
        
        Builder builder = resource.entity(contact);
        String response = builder.post(String.class);
        
        System.out.println(response);
    }
    
    /**
     * 测试inputStream传参
     */
    @Test
    public void testPost4() {
        String url = "http://localhost:8080/jersey/rest/contact/getContact6";
        WebResource resource = client.resource(url);
        
        String buf = "inputstream content.";
        ByteArrayInputStream bais = new ByteArrayInputStream(buf.getBytes());
        
        Builder builder = resource.entity(bais);
        String result = builder.post(String.class);
        
        System.out.println(result);
    }
}
