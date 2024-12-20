package com.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	
	@Transactional
	@Modifying
	@Query(value="update tbl_item set item_name = :itemName, item_description = :description, item_price = :price where item_id = :itemId", nativeQuery = true) 
	public int updateItemDetails(@Param("itemName") String itemName, @Param("description") String description, @Param("price") String price, @Param("itemId") int itemId);

	@Query("select t from Item t where t.itemId = :itemId")
	public Item getItemById(@Param("itemId") int itemId);
	
	
	
	@Query(value = "select * from tbl_item t where t.user_id = :userId" ,nativeQuery = true)
	public	List<Item> findAllByUserId(@Param("userId") int userId);


}
