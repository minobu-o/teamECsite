package jp.co.internous.react.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.internous.react.model.domain.MstProduct;

@Mapper
public interface MstProductMapper {
	
	/* 商品の画像か商品名がクリックされた時に表示するためのメソッド */
	@Select("SELECT * FROM mst_product WHERE id = #{Id}")
	MstProduct findById(long id);
	
	/* index.html初期表示のためのメソッド*/
	@Select("SELECT * FROM mst_product")
	List<MstProduct> findAll();
	
	/* カテゴリ+商品名で検索するときのメソッド */
	List<MstProduct> findByCategoryIdAndProductName(@Param("category") long category, @Param("keywords") String[] keywords);
	
	/* カテゴリが空白（0）で検索ワードのみで検索するときのメソッド */
	List<MstProduct> findByProductName(@Param("keywords") String[] keywords);
}