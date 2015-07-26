package bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * JAX-RS 支持使用 JAXB (Java API for XML Binding) 将 JavaBean 绑定到 XML 或 JSON，反之亦然。
 * JavaBean 必须使用 @XmlRootElement 注释
 * 没有明确 @XmlElement 注释的字段将包含一个名称与之相同的 XML 元素
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
