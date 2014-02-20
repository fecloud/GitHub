package demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import demo.entity.ImageDomain;

public class SAXImageService {
	public static void main(String[] args) throws Exception{
		SAXImageService service = new SAXImageService();
		InputStream in = new FileInputStream(new File("image2.xml"));
		List<ImageDomain> imageDomains = service.getImageDomains(in);
		System.out.println("-------"+imageDomains.size());
		for (int i = 0; i < imageDomains.size(); i++) {
			System.out.println("step:"+imageDomains.size()+";"+imageDomains.get(i));
		}
	}
	public List<ImageDomain> getImageDomains(InputStream in) throws Exception{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		ImageDomainParser imageParser = new ImageDomainParser();
		parser.parse(in, imageParser);
		List<ImageDomain> imageDomains = imageParser.getImageDomains();
		return imageDomains;
	}
	private final class ImageDomainParser extends DefaultHandler{
		private List<ImageDomain> imageDomains = null;
		private ImageDomain imageDomain = null;
		private String parentTag = null;
		private String subTag = null;
		private String atr = null;
		
		public List<ImageDomain> getImageDomains() {
			return imageDomains;
		}
		@Override
		public void startDocument() throws SAXException {
			imageDomains = new ArrayList<ImageDomain>();
		}
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
//			System.out.println("localName:"+localName+",qName:"+qName);
			if("step".equals(qName)){
				imageDomain = new ImageDomain();
				imageDomain.setType(new Integer(attributes.getValue(0)));
				parentTag = localName;
			}else{
				subTag = qName;
				atr = attributes.getValue(0);
//				System.out.println("oooooo"+atr);
			}
		}
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if(parentTag!=null){
				String data = new String(ch,start,length);
				if("property".equals(subTag)){
//					System.out.println("===="+data);
					if("next".equals(atr)){
						imageDomain.setNext(data);
					}else if("serialNO".equals(atr)){
//						System.out.println("??????"+data);
						imageDomain.setSerialNO(new Integer(data));
					}else if("summary".equals(atr)){
						imageDomain.setSummary(data);
					}else if("name".equals(atr)){
						imageDomain.setName(data);
					}else if("key".equals(atr)){
						imageDomain.setKey(data);
					}else if("comment".equals(atr)){
						imageDomain.setComment(data);
					}else if("TransactionName".equals(atr)){
						imageDomain.setTransactionName(data);
					}else if("next-false".equals(atr)){
						imageDomain.setNext_false(data);
					}else if("next-true".equals(atr)){
						imageDomain.setNext_true(data);
					}else if("function-path".equals(atr)){
						imageDomain.setFuncationPath(data);
					}else if("param-count".equals(atr)){
						imageDomain.setParamCount(new Integer(data));
					}else if("older_timer_name".equals(atr)){
						imageDomain.setOlder_timer_name(data);
					}else if("timer_var".equals(atr)){
						imageDomain.setTimer_var(data);
					}else if("verify_operate_type".equals(atr)){
						imageDomain.setVerify_operate_type(new Integer(data));
					}else if("toleration".equals(atr)){
						imageDomain.setToleration(new Integer(data));
					}else if("verify_source".equals(atr)){
						imageDomain.setVerify_source(data);
					}else if("verify_method".equals(atr)){
						imageDomain.setVerify_method(new Integer(data));
					}else if("location_rect".equals(atr)){
						imageDomain.setLocation_rect(data);
					}else if("timeout".equals(atr)){
						imageDomain.setTimeout(new Integer(data));
					}else if("expression_result".equals(atr)){
						imageDomain.setExpression_result(data);
					}else if("expression".equals(atr)){
						imageDomain.setExpression(data);
					}else if("wait_time".equals(atr)){
						imageDomain.setWait_time(new Integer(data));
					}else if("TransactionStatus".equals(atr)){
						imageDomain.setTransactionStatus(new Integer(data));
					}else if("TransactionResultCode".equals(atr)){
						imageDomain.setTransactionResultCode(data);
					}else if("TransactionVariable".equals(atr)){
						imageDomain.setTransactionVariable(data);
					}else if("status".equals(atr)){
						imageDomain.setStatus(new Integer(data));
					}
				}
			}
		}
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
//			System.out.println("localName:"+localName+",qName:"+qName);
			if("step".equals(qName)){
				imageDomains.add(imageDomain);
				parentTag = null;
			}else if("property".equals(qName)){
				subTag = null;
			}
		}
	}
}
