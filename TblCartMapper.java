package jp.co.internous.react.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.react.model.domain.TblCart;
import jp.co.internous.react.model.domain.dto.CartDto;

@Mapper
public interface TblCartMapper {
	
	/*CartDtoに入る情報を呼び出すためのfindByUserIdOrTmpUserIdメソッド*/
	List<CartDto> findByUserId(@Param("userId")long userId, @Param("tmpUserId")long tmpUserId);
	
	/* TblCart内に同じ商品が入っているかを検索するメソッド */
	List<TblCart> findByProductIdAndUserId(@Param("productId")long productId, @Param("userId")long userId, @Param("tmpUserId")long tmpUserId);
	 
	/*TblCartの個数を更新するためのupdate文を記述*/
	long updateProductId(@Param("productId")long productId, @Param("productCount")long productCount);

	/*TblCartにデータを入れるため、カートに追加時のinsertを記述*/
	@Options(useGeneratedKeys=true, keyProperty="id")
	long insert(@Param("userId") long userId, @Param("productId")long productId, @Param("productCount")long productCount);

	/*TblCartからデータを削除するためのdelete文を記述*/
	@Delete("DELETE FROM tbl_cart WHERE id = #{id}")
	long deleteById(long id);
	
	/*決済完了時に*TblCartからデータを削除するためのdelete文*/
	@Delete("DELETE FROM tbl_cart WHERE user_id = #{userId}")
	long deleteByUserId(long userId);
	
	/* カートに追加後にログインした際、user_idを更新する為のメソッド*/
	@Update("UPDATE tbl_cart SET user_id = #{userId} WHERE user_id = #{tmpUserId}")
    long updateUserId(@Param("tmpUserId")long tmpUserId, @Param("userId")long userId);
}