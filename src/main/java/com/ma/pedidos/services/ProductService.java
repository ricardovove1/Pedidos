package com.ma.pedidos.services;

import java.util.List;

import com.ma.pedidos.entities.Product;
import com.ma.pedidos.exception.ServiceException;

public interface ProductService {

	/**
	 * Save product
	 * 
	 * @param product {@link Product}
	 * @return {@link Product}
	 */
	public Product save(Product product);

	/**
	 * Get all products not deleted
	 * 
	 * @return {@link List} of {@link Product}
	 */
	public List<Product> getAll();

	/**
	 * Get product by id
	 * 
	 * @param id {@link String}
	 * @return {@link Product}
	 * @throws ServiceException
	 */
	public Product getById(String id) throws ServiceException;

	/**
	 * delete product by id
	 * 
	 * @param id {@link String}
	 * @throws ServiceException
	 */
	public void deleteById(String id) throws ServiceException;

	/**
	 * check the existence of the product
	 * 
	 * @param id {@link String}
	 */
	public void existProduct(String id)throws ServiceException;

}
