package com.tim.document.web1.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.document.web1.model.Order;
import com.tim.document.web1.model.User;
import com.tim.document.web1.service.OrderService;
import com.tim.document.web1.service.UserService;
import com.tim.document.web1.utility.JwtUtility;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService orderService;
	private final UserService userService;
	private final JwtUtility jwtUtility;

	public OrderController(
			OrderService orderService,
			UserService userService,
			JwtUtility jwtUtility) {
		super();
		this.orderService = orderService;
		this.jwtUtility=jwtUtility;
		this.userService=userService;
	}
	
	@GetMapping
	public List<Order>findAll(){
		return orderService.findAll();
	}
	@GetMapping("/{id}")
	public Order findById(@PathVariable Integer id) {
		return orderService.findById(id);
	}
	
	@GetMapping("/report")
	public ResponseEntity<byte[]>exportOrderReport()throws Exception{
		List<Order>orders =orderService.findAll();
		JRBeanCollectionDataSource dataSource=
				new JRBeanCollectionDataSource(orders);
		InputStream jrInputStream =
				getClass().getResourceAsStream("/reports/OrderList.jrxml");
		JasperReport jasperReport=
				JasperCompileManager.compileReport(jrInputStream);
		JasperPrint print=
				JasperFillManager.fillReport(
						jasperReport,new HashMap<>(),dataSource);
		byte[]pdf=JasperExportManager.exportReportToPdf(print);
		return ResponseEntity.ok()
				.header("Content-Disposition","attachment;filename=order_report.pdf")
				.contentType(MediaType.APPLICATION_PDF)
				.body(pdf);
	}
	@PostMapping
	public ResponseEntity<String>createOrder(
			@RequestHeader("Authorization")String authorization,
			@RequestBody Order order){
		try {
			String token=authorization.substring(7);
			String username=jwtUtility.extractUsername(token);
			User user=userService.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("找不到使用者"));
			order.setUserId(user.getId());
			orderService.createOrder(order);
			return ResponseEntity.ok("訂單建立成功");
			
		} 
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
