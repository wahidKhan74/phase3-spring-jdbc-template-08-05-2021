package com.dell.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dell.webapp.bean.EProduct;

public class EProductDAO {
	
	// spring jdbc template
	JdbcTemplate template;
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	// list all product
	public List<EProduct> getProducts() {
		return template.query("select * from eproduct",  new RowMapper<EProduct>() {
			public EProduct mapRow(ResultSet res, int row) throws SQLException {
				// map fields
				EProduct product = new EProduct();
				product.setId(res.getInt(1));
				product.setName(res.getString(2));
				product.setPrice(res.getBigDecimal(3));
				product.setDateAdded(res.getDate(4));
				return product;
			}
		});
	}
	
	// add product
	public int addProduct(EProduct eProduct) {
		String query = "insert into eproduct(name,price) values('"+eProduct.getName() +"','"+eProduct.getPrice()+"')";
		return template.update(query);
	}
	
	// delete product
	public int deleteProduct(EProduct eProduct) {
		String query = "delete from eproduct wherer id="+eProduct.getId();
		return template.update(query);
	}
	
	// update product
	public int updateProduct(EProduct eProduct) {
		String query = "update eproduct set name='"+eProduct.getName()+"' price='"+eProduct.getPrice()+"' wherer id="+eProduct.getId();
		return template.update(query);
	}
}
