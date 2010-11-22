/*
 * Ghost4J: a Java wrapper for Ghostscript API.
 *
 * Distributable under LGPL license.
 * See terms of license at http://www.gnu.org/licenses/lgpl.html.
 */
package net.sf.ghost4j.renderer;

import java.awt.Image;
import java.io.File;
import java.util.List;

import junit.framework.TestCase;
import net.sf.ghost4j.document.PDFDocument;
import net.sf.ghost4j.document.PSDocument;

/**
 * SimpleRenderer tests.
 * @author Gilles Grousset (gi.grousset@gmail.com)
 *
 */
public class SimpleRendererTest extends TestCase {

	public SimpleRendererTest(String testName) {
		super(testName);
	}
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testRenderWithPDF() throws Exception {
    	
    	PDFDocument document = new PDFDocument();
		document.load(new File("input.pdf"));
		
		SimpleRenderer simpleRenderer = new SimpleRenderer();
		List<Image> result = simpleRenderer.render(document);
		
		assertEquals(1, result.size());
    }
    
    public void testRenderWithPS() throws Exception {
    	
    	PSDocument document = new PSDocument();
		document.load(new File("input.ps"));
		
		SimpleRenderer simpleRenderer = new SimpleRenderer();
		List<Image> result = simpleRenderer.render(document);
		
		assertEquals(1, result.size());
    }
    
    public void testRenderWithPDFMultiProcess() throws Exception {
    
    	final PDFDocument document = new PDFDocument();
		document.load(new File("input.pdf"));
		
		final SimpleRenderer simpleRenderer = new SimpleRenderer();
		simpleRenderer.setMaxProcessCount(2);
		
		Thread thread1 = new Thread(){
        	public void run() {
        		try{
        			System.out.println("START 1 " + Thread.currentThread());
        			List<Image> result = simpleRenderer.render(document);
        			System.out.println("END 1 " + Thread.currentThread());
        			assertEquals(1, result.size());
        		}catch(Exception e){
        			e.printStackTrace();
        		}
        	};
        };
        thread1.start();
        
        Thread thread2 = new Thread(){
        	public void run() {
        		try{
        			System.out.println("START 2 " + Thread.currentThread());
        			List<Image> result = simpleRenderer.render(document);
        			System.out.println("END 1 " + Thread.currentThread());
        			assertEquals(1, result.size());
        		}catch(Exception e){
        			e.printStackTrace();
        		}
        	};
        };
        thread2.start();
        
        //the last one will block until a previous one finishes
        Thread thread3 = new Thread(){
        	public void run() {
        		try{
        			System.out.println("START 3 " + Thread.currentThread());
        			List<Image> result = simpleRenderer.render(document);
        			System.out.println("END 1 " + Thread.currentThread());
        			assertEquals(1, result.size());
        		}catch(Exception e){
        			e.printStackTrace();
        		}
        	};
        };
        thread3.start();
        
        thread1.join();
        thread2.join();
        thread3.join();
    }
    
}
