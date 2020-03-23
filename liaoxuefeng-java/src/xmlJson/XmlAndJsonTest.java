package xmlJson;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class XmlAndJsonTest {
    public static void main(String[] args) throws Exception {
        int i = 2;
        switch (i){
            case 1:
                test1();
                break;
            case 2:
//                test2();
                break;
            case 3:
                test3();
                break;
            default:
                System.out.println("执行完毕");
        }
    }
    static void test3(){

    }
//    static void test2() throws IOException, JsonMappingException {
//        String filePath = "E:\\IDE\\IDEA\\not-work\\liaoxuefeng-java\\src\\xmlJson\\book.xml";
//        InputStream inputStream1 = new FileInputStream(filePath);
//        JacksonXmlModule module = new JacksonXmlModule();
//        XmlMapper mapper = new XmlMapper(module);
//        Book book = null;
//        book = mapper.readValue(inputStream1, Book.class);
//        System.out.println(book.id);
//        System.out.println(book.name);
//        System.out.println(book.author);
//        System.out.println(book.isbn);
//        System.out.println(book.tags);
//        System.out.println(book.pubDate);
//
//    }
    static void test1() throws IOException, ParserConfigurationException, SAXException {
        String filePath = "E:\\IDE\\IDEA\\not-work\\liaoxuefeng-java\\src\\xmlJson\\book.xml";
//        InputStream inputStream = XmlAndJsonTest.class.getResourceAsStream("\book.xml");
        InputStream inputStream1 = new FileInputStream(filePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(inputStream1);
        printNode(doc,0);
    }
    static void printNode(Node n, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(' ');
        }
        switch (n.getNodeType()) {
            case Node.DOCUMENT_NODE: // Document节点
                System.out.println("Document: " + n.getNodeName());
                break;
            case Node.ELEMENT_NODE: // 元素节点
                System.out.println("Element: " + n.getNodeName());
                break;
            case Node.TEXT_NODE: // 文本
                System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.ATTRIBUTE_NODE: // 属性
                System.out.println("Attr: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            default: // 其他
                System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
        }
        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            printNode(child, indent + 1);
        }
    }
}

class Book {
    public long id;
    public String name;
    public String author;
    public String isbn;
    public List<String> tags;
    public String pubDate;
}