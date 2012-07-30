package org.ahedstrom.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		response.getOutputStream().write("hello doGet".getBytes());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part p = request.getPart("selectedFile");
		String fileName = "default";
		String[] split = p.getHeader("content-disposition").split(";");
		for(String s : split) {
			if(s.contains("filename")) {
				fileName = s.split("=")[1].replace("\"", "").trim();
				break;
			}
		}
		p.write(fileName);
		
		response.getOutputStream().write(String.format("File: %s uploaded", fileName).getBytes());
	}

}
