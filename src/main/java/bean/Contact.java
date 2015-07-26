package bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * JAX-RS ֧��ʹ�� JAXB (Java API for XML Binding) �� JavaBean �󶨵� XML �� JSON����֮��Ȼ��
 * JavaBean ����ʹ�� @XmlRootElement ע��
 * </pre>
 * 
 * @author Administrator
 * 
 */
@XmlRootElement
public class Contact {
    private String id;
    
    private String name;
    
    private List<Address> addresses;
    
    public Contact() {
    }
    
    public Contact(String id, String name, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.addresses = addresses;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElement(name = "address")
    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
}
