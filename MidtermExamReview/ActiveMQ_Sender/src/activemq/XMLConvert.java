package activemq;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLConvert<T> {
	private T type;
	
	public XMLConvert(T type){
		this.type = type;
	}
	
	public String object2XML(T obj) throws Exception{
		JAXBContext context = JAXBContext.newInstance(type.getClass());
		Marshaller marshaller = context.createMarshaller();
		StringWriter sw = new StringWriter();
		marshaller.marshal(obj, sw);
		return sw.toString();
	}
	
	@SuppressWarnings("all")
	public T xml2Object(String xml) throws Exception{
		T t = null;
		JAXBContext context = JAXBContext.newInstance(type.getClass());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		t = (T) unmarshaller.unmarshal(new StringReader(xml));
		return t;
	}
}
