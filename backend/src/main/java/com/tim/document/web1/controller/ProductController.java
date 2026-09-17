package com.tim.document.web1.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim.document.web1.model.Product;
import com.tim.document.web1.service.ProductService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(
			@RequestParam(defaultValue = "1")int page,
			@RequestParam(defaultValue = "10")int size
			) {
	    return ResponseEntity.ok(productService.findPage(page, size));
	}
	
	@GetMapping("/report")
	public ResponseEntity<byte[]>exportProductReport()throws Exception{
		List<Product>products =productService.findAll();
		JRBeanCollectionDataSource dataSource=
				new JRBeanCollectionDataSource(products);
		InputStream jrInputStream =
				getClass().getResourceAsStream("/reports/ProductList.jrxml");
		JasperReport jasperReport=
				JasperCompileManager.compileReport(jrInputStream);
		JasperPrint print=
				JasperFillManager.fillReport(
						jasperReport,new HashMap<>(),dataSource);
		byte[]pdf=JasperExportManager.exportReportToPdf(print);
		return ResponseEntity.ok()
				.header("Content-Disposition","attachment;filename=product_report.pdf")
				.contentType(MediaType.APPLICATION_PDF)
				.body(pdf);
	}
}
