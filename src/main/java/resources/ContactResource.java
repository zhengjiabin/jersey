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
 * @Context：使用该注释注入上下文对象
 * @Path：标注资源类或者方法的相对路径 　　
 * @GET：@PUT，@POST，@DELETE，标注方法是HTTP请求的类型　
 * @Produces：标注返回的MIME媒体类型 　　
 * @Consumes：标注可接受请求的MIME媒体类型
 * @PathParam：获取@Path的URL路径中的{xxx}值至参数中
 * @QueryParam：获取URL的查询参数对应的值至参数中
 * @HeaderParam：获取HTTP请求的header信息至参数中
 * @CookieParam：获取HTTP请求的Cookie信息至参数中
 * @BeanParam：当请求参数很多时，比如客户端提交一个修改用户的PUT请求，请求中包含很多项用户信息
 * @FormParam：从POST请求的表单参数中获取数据
 * 
 * @Singleton：在应用范围内，只会创建资源类的一个实例
 * @PerRequest：默认情况下，资源类的生命周期是per-request，也就是系统会为每个匹配资源类URI的请求创建一个实例
 * 
 * HTTP GET：获取/列出/检索单个资源或资源集合。
 * HTTP POST：新建资源。
 * HTTP PUT：更新现有资源或资源集合。
 * HTTP DELETE：删除资源或资源集合
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
     * @Consumes：声明该方法使用 HTML FORM
     * @FormParam：注入该方法的 HTML 属性确定的表单输入
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
    //    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)//可这样配置
    //    @Consumes(MediaType.APPLICATION_XML)//这样配置有误，无法正确读出流信息 
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
