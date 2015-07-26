package resources;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import storage.ContactStore;
import util.ParamUtil;
import bean.Address;
import bean.Contact;

import com.sun.jersey.api.NotFoundException;

/**
 * <pre>
 * @Context：使用该注释注入上下文对象
 * @Path，标注资源类或者方法的相对路径 　　
 * @GET，@PUT，@POST，@DELETE，标注方法是HTTP请求的类型。 　　
 * @Produces，标注返回的MIME媒体类型 　　
 * @Consumes，标注可接受请求的MIME媒体类型
 * @PathParam，@QueryParam，@HeaderParam，@CookieParam，@MatrixParam，@FormParam：分别标注方法的参数来自于HTTP请求的不同位置
 *  例如@PathParam来自于URL的路径，@QueryParam来自于URL的查询参数，@HeaderParam来自于HTTP请求的头信息，@CookieParam来自于HTTP请求的Cookie
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
@Path(value = "/contact")
public class ContactResource {
    @Context
    UriInfo uriInfo;
    
    @Context
    Request request;
    
    String contact;
    
    public ContactResource(UriInfo uriInfo, Request request, String contact) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.contact = contact;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Contact getContact() {
        Contact cont = ContactStore.getStore().get(contact);
        if (cont == null)
            throw new NotFoundException("No such Contact.");
        return cont;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putContact(JAXBElement<Contact> jaxbContact) {
        Contact c = jaxbContact.getValue();
        return putAndGetResponse(c);
    }
    
    @PUT
    public Response putContact(@Context
    HttpHeaders herders, byte[] in) {
        Map<String, String> params = ParamUtil.parse(new String(in));
        Contact c = new Contact(params.get("id"), params.get("name"), new ArrayList<Address>());
        return putAndGetResponse(c);
    }
    
    private Response putAndGetResponse(Contact c) {
        Response res;
        if (ContactStore.getStore().containsKey(c.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        ContactStore.getStore().put(c.getId(), c);
        return res;
    }
    
    @DELETE
    public void deleteContact() {
        Contact c = ContactStore.getStore().remove(contact);
        if (c == null)
            throw new NotFoundException("No such Contact.");
    }
}
