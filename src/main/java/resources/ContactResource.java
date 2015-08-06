package resources;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import bean.Contact;

import com.sun.jersey.spi.resource.Singleton;

/**
 * <pre>
 * @Context��ʹ�ø�ע��ע�������Ķ���
 * @Path����ע��Դ����߷��������·�� ����
 * @GET��@PUT��@POST��@DELETE����ע������HTTP��������͡�
 * @Produces����ע���ص�MIMEý������ ����
 * @Consumes����ע�ɽ��������MIMEý������
 * @PathParam����ȡ@Path��URL·���е�{xxx}ֵ��������
 * @QueryParam����ȡURL�Ĳ�ѯ������Ӧ��ֵ��������
 * @HeaderParam����ȡHTTP�����header��Ϣ��������
 * @CookieParam����ȡHTTP�����Cookie��Ϣ��������
 * @BeanParam������������ܶ�ʱ������ͻ����ύһ���޸��û���PUT���������а����ܶ����û���Ϣ
 * @FormParam����POST����ı������л�ȡ����
 * 
 * @Singleton����Ӧ�÷�Χ�ڣ�ֻ�ᴴ����Դ���һ��ʵ��
 * @PerRequest��Ĭ������£���Դ�������������per-request��Ҳ����ϵͳ��Ϊÿ��ƥ����Դ��URI�����󴴽�һ��ʵ��
 * 
 * HTTP GET����ȡ/�г�/����������Դ����Դ���ϡ�
 * HTTP POST���½���Դ��
 * HTTP PUT������������Դ����Դ���ϡ�
 * HTTP DELETE��ɾ����Դ����Դ����
 * </pre>
 * 
 * @author Administrator
 * 
 */
@Singleton
@Path(value = "/contact")
public class ContactResource {
    @Context
    UriInfo uriInfo;
    
    @Context
    Request request;
    
    @Context
    HttpHeaders httpHeader;
    
    @GET
    @Path(value = "getContact1/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Contact getContact1(@PathParam(value = "id")
    String id) {
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
    
    @GET
    @Path(value = "/getContact2")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Contact getContact2(@DefaultValue(value = "0")
    @QueryParam(value = "id")
    String id) {
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
    
    /**
     * @Consumes�������÷���ʹ�� HTML FORM
     * @FormParam��ע��÷����� HTML ����ȷ���ı�����
     * @param id
     * @param name
     * @param servletResponse
     * @throws IOException
     */
    @POST
    @Path(value = "getContact3")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_HTML)
    public String getContact3(Contact contact) {
        return contact.getId();
    }
    
    @POST
    @Path(value = "getContact4")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Contact getContact4(@FormParam(value = "id")
    String id) {
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
    
    @POST
    @Path(value = "getContact5")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String getContact5(Contact contact, @Context
    HttpServletRequest request) {
        System.out.println(contact.getId());
        return request.getContextPath();
    }
    
    @POST
    @Path(value = "getContact6")
    //    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)//����������
    //    @Consumes(MediaType.APPLICATION_XML)//�������������޷���ȷ��������Ϣ 
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String getContact6(InputStream is, @Context
    HttpServletRequest request) throws Exception {
        byte[] buf = new byte[is.available()];
        is.read(buf);
        System.out.println("buf:" + new String(buf));
        return request.getContextPath();
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putContact(JAXBElement<Contact> jaxbContact) {
        Contact c = jaxbContact.getValue();
        if (c == null) {
            return Response.noContent().build();
        }
        return Response.created(uriInfo.getAbsolutePath()).build();
    }
    
    @PUT
    public Response putContact(@Context
    HttpHeaders herders, byte[] in) {
        return Response.noContent().build();
    }
    
    @DELETE
    public void deleteContact() {
        return;
    }
}
