package com.starkinc.wtopic.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class WebUtil {
	
	public static String getViewAsString(Map<String, String> responseMap,HttpServletRequest request, RedirectAttributes redirectAttrs){
		String error = responseMap.get("error");
		if(error == null){
			request.getSession().setAttribute("token", responseMap.get("token"));
			return "home";
		}else{
			redirectAttrs.addFlashAttribute("error", error);
			return "redirect:/";
		}
	}

}
