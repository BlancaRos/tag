package upm.blanca.tfg.optimization.tool.pdf;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class PDFGenerator {

	public PDFGenerator() {
		
	}
	
	
	public static void generatePDF() throws JRException {
		
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
	     Map<String, Object> parameters = new HashMap<String, Object>();
	      
	        
	          parameters.put("description", "HOLA CARA COLAAAAAAA");
	          
//	          List<ProductOrderPDFBean> products = order.getProducts();
//	          for (ProductOrderPDFBean product : order.getProducts()) {
//	            product.setTotPedida(order.getTotPedida());
//	            product.setTotServida(order.getTotServida());
//	            product.setNomCoop(order.getNomCoop());
//	            product.setNumCoop(order.getNumCoop());
//	            product.setNumPedido(order.getPedido());
//	          }
	          ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	          InputStream template = ClassLoader.getSystemResourceAsStream("Informe.jrxml");
	          JasperReport jReport = JasperCompileManager.compileReport(template);
//	          JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(products);
	          JasperPrint jPrint = JasperFillManager.fillReport(jReport, parameters, new JREmptyDataSource());
	          jasperPrintList.add(jPrint);
	        
			JasperExportManager.exportReportToPdfFile(jPrint, "/Users/admin/Desktop/TFG/Reports/pdf1.pdf");

		
//	      JRExporter exporter = new JRPdfExporter();
//	      exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
//	      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
//	      exporter.exportReport();
	}
}
