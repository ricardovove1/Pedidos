package com.ma.pedidos.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ma.pedidos.entities.Product;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.repositories.ProductRepository;
import com.ma.pedidos.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) {

		return productRepository.save(product);
	}

	@Override
	public List<Product> getAll() {

		return productRepository.findByDeleteDateIsNull();
	}

	@Override
	public Product getById(String id) throws ServiceException {

		Product product = productRepository.findByIdAndDeleteDateIsNull(id);

		if (product == null) {

			logger.error("Product not found, id: {}", id);

			throw new ServiceException(String.format("Producto no encontrado: %s", id), HttpStatus.NOT_FOUND);
		}
		return product;
	}

	@Override
	public void deleteById(String id) throws ServiceException {

		Product product = getById(id);

		product.setDeleteDate(LocalDate.now());

		logger.info("delete product, id: {}", id);

		productRepository.save(product);
	}

	@Override
	public void existProduct(String id) throws ServiceException {

		if (!productRepository.existsByIdAndDeleteDateIsNull(id)) {

			logger.error("Product not found, id: {}", id);

			throw new ServiceException(String.format("Producto no encontrado: %s", id), HttpStatus.NOT_FOUND);
		}

	}

}
