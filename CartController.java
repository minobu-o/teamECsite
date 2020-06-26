package jp.co.internous.react.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import jp.co.internous.react.model.domain.TblCart;
import jp.co.internous.react.model.domain.dto.CartDto;
import jp.co.internous.react.model.form.CartForm;
import jp.co.internous.react.model.mapper.TblCartMapper;
import jp.co.internous.react.model.session.LoginSession;

@Controller
@RequestMapping("/react/cart")
public class CartController {
	
	@Autowired
	TblCartMapper tblCartMapper;
	
	@Autowired 
	protected LoginSession loginSession; 
	
	private Gson gson = new Gson();

	@RequestMapping("/")
	public String index(Model m) {
		/* tbl_cart.product_idとmst_product.idでjoinしたので、CartDtoリストからfindByUserIdメソッドで呼び出し */
		
		List<CartDto> cartDto = tblCartMapper.findByUserId(loginSession.getUserId(), loginSession.getTmpUserId());
			m.addAttribute("CartList",cartDto);
			m.addAttribute("loginSession",loginSession);
		return "cart";
	}
	
	/* userIdでTblCartのデータを取得し、同じ商品IDのデータがあれば個数のみ追加、なければすべてのデータを挿入 */
	@RequestMapping("/add")
	public String addCart(CartForm form , Model m) {
		List<TblCart> tblCart = tblCartMapper.findByProductIdAndUserId(form.getProductId(), loginSession.getUserId(), loginSession.getTmpUserId()); 
		
		if(tblCart.size() > 0) {
			/*update文を実行するためのメソッド */
			tblCartMapper.updateProductId(form.getProductId(), form.getProductCount());
		}else {
			/*CartFormの情報をinsertするためのメソッド*/
			long userId = loginSession.getUserId() != 0 ? loginSession.getUserId() : loginSession.getTmpUserId();
			tblCartMapper.insert(userId,form.getProductId(),form.getProductCount());
		}
		
		List<CartDto> cartDto = tblCartMapper.findByUserId(loginSession.getUserId(), loginSession.getTmpUserId());
			m.addAttribute("CartList",cartDto);
			m.addAttribute("loginSession",loginSession);
		return "cart";
	}
	
/* カート削除機能("/cart/delete") */
	@SuppressWarnings("unchecked")
	@RequestMapping("/delete")
	public String deleteCart(@RequestBody String idList) {
		Map<String,List<String>> map = gson.fromJson(idList,Map.class);
		List<String> idListInstance = map.get("idList");

		try {
			for(String id : idListInstance) {
				tblCartMapper.deleteById(Long.parseLong(id));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "cart";
	}
}