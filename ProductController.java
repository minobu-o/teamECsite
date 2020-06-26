package jp.co.internous.react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import jp.co.internous.react.model.domain.MstProduct;
import jp.co.internous.react.model.mapper.MstProductMapper;
import jp.co.internous.react.model.session.LoginSession;

@Controller
@RequestMapping("/react")
public class ProductController {
	
	@Autowired
	MstProductMapper mstProductMapper;
	
	@Autowired 
	protected LoginSession loginSession; 
	
	@Autowired
	Gson gson = new Gson();
	
	@RequestMapping(value="/product/{id}")
	public String index(@PathVariable("id") long id ,Model mProduct) {
		
		MstProduct mstProduct = mstProductMapper.findById(id);
		
		if (mstProduct != null) {
			mProduct.addAttribute("mstProductList",mstProduct);
			mProduct.addAttribute("loginSession",loginSession);
		}
		
		return "product_detail";
	
	}
}
