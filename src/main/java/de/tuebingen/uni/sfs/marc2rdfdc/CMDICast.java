package de.tuebingen.uni.sfs.marc2rdfdc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.AccessControlException;
import java.util.Iterator;
import java.util.Properties;
import javax.ws.rs.core.MediaType;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.sf.saxon.xqj.SaxonXQDataSource;
import org.apache.commons.io.FilenameUtils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.IOUtils;

public class CMDICast {

	public static void main(String[] args) throws Exception {
		test();
	}

	public static void test() throws Exception {
		File result = castFile("annotated_english_gigaword.cmdi");
		System.out.println("first done");
		castFile("annotated_english_gigaword.cmdi");
		System.out.println("second done");
		castFile("annotated_english_gigaword.cmdi");
		System.out.println("third done");
	}

	public static File castFile(String cmdifilename) throws Exception {
		return castFile(new File(cmdifilename));
	}

	public static InputStream getInputStream(String filename) {
		try {
			return new FileInputStream(filename);
		} catch (FileNotFoundException | AccessControlException xc) { //ignore
		}
		try {
			return new FileInputStream("src/main/webapp/" + filename);
		} catch (FileNotFoundException | AccessControlException xc) { //ignore
		}

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream is = null;
		try {
			is = cl.getResourceAsStream(filename);
		} catch (AccessControlException xc) { //ignore
			xc.printStackTrace();
		}
		return (is != null) ? is : cl.getResourceAsStream("../../" + filename);
	}

	public static File castFile(File dcfile) throws Exception {

	    // standard call to xslt
	    System.setProperty("javax.xml.transform.TransformerFactory",
			       "net.sf.saxon.TransformerFactoryImpl");
	    
	    String foo_xml = dcfile.getName();
	    String foo_xsl = "MARC21slim2RDFDC.xsl";
	    InputStream foo_xsl_stream = getInputStream( foo_xsl );
	    
	    String name = FilenameUtils.removeExtension(dcfile.getName());
	    File output = new File(dcfile.getParent(), name + ".rdfdc.xml");
	    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));
	    
	    TransformerFactory tfactory = TransformerFactory.newInstance();
	    Transformer transformer = tfactory.newTransformer(new StreamSource( foo_xsl_stream )); // new File(foo_xsl)
	    transformer.transform(new StreamSource( dcfile ), 
				  new StreamResult( outputStream ));
	    
	    return output;
	}
}
