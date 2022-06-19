package ex02;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch04.Employee;
import ch04._02.service.EmployeeService;
import ch04._02.service.impl.EmployeeServiceImpl;
import ch04._03.model.BookBean;

@Controller
public class Ex02Controller {
	ServletContext ctx;
	EmployeeService employeeService;

	

	//@Autowired
public Ex02Controller(ServletContext ctx, EmployeeService employeeService) {
		this.ctx = ctx;
		this.employeeService = employeeService;
	}

//	public Ex02Controller() {
//		   System.out.println("=====>IoC容器正在建立本類別(Ex02Controller)的物件");
//	}
	@GetMapping("/eeit145/ex02")
	public String ex02index() {
		return "ex02/index";
	}

	@GetMapping(value = "/ex02req01", produces = "text/html; charset = UTF-8")
	public @ResponseBody String req01() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
		return "現在時間" + simpleDateFormat.format(new Date());
	}

	@GetMapping(value = "/ex02req02/{key}", produces = "application/json; charset = UTF-8")
	public @ResponseBody Employee req02(@PathVariable("key") Long key) {
		return employeeService.findById(key);
	}

	@GetMapping(value = "/ex02req03", produces = "application/json; charset = UTF-8")
	public @ResponseBody List<Employee> req03() {
		return employeeService.findALL();
	}

	@GetMapping(value = "/ex02req04", produces = "application/json; charset = UTF-8")
	public @ResponseBody Map<String, Object> req04() {
		Map<String, Object> map = new HashMap<>();
		map.put("hello", "kitty");
		map.put("score", 100);
		map.put("vat", 0.05);
		map.put("lotteryNumber", new Integer[] { 20, 25, 27, 33, 34, 25 });
		return map;

	}
	@GetMapping(value ="/ex02req05")
	public ResponseEntity<byte[]> getImage(@RequestParam("no") Long no) {
		ResponseEntity<byte[]> re = null;
		HttpHeaders headers = new HttpHeaders();
		Employee emp = employeeService.findById(no);
		Blob blob = emp.getImage();
		String mimeType = emp.getMimeType();
		MediaType mediaType = MediaType.valueOf(mimeType);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = blob.getBinaryStream();
			byte[] b = new byte[81920];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			headers.setContentType(mediaType);
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			re = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	@GetMapping(value ="/ex02req06/{id}")
	public ResponseEntity<byte[]> getImage2(@PathVariable("id") Long no) {
		ResponseEntity<byte[]> re = null;
		HttpHeaders headers = new HttpHeaders();
		Employee emp = employeeService.findById(no);
		Blob blob = emp.getImage();
		String mimeType = emp.getMimeType();
		MediaType mediaType = MediaType.valueOf(mimeType);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream is = blob.getBinaryStream();
			byte[] b = new byte[81920];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			headers.setContentType(mediaType);
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			re = new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

//	public @ResponseBody String timeNow() {
//		return "";
//	}
//	
//	@GetMapping("/variousData")
//	public @ResponseBody Map<String, Object> variousData() {
//		return null;
//	}

}
