package bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * JAX-RS ֧��ʹ�� JAXB (Java API for XML Binding) �� JavaBean �󶨵� XML �� JSON����֮��Ȼ��
 * JavaBean ����ʹ�� @XmlRootElement ע��
 * û����ȷ @XmlElement ע�͵��ֶν�����һ��������֮��ͬ�� XML Ԫ��
 * </pre>
 * 
 * @author Administrator
 * 
 */
@XmlRootElement
public class Address {
    private String city;
    
    private String street;
    
    public Address() {
    }
    
    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
}
