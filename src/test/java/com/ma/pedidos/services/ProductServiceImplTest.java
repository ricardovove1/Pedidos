package com.ma.pedidos.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ma.pedidos.entities.Product;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.repositories.ProductRepository;
import com.ma.pedidos.services.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void save_theReturnObject() {

		Product product = getProduct();

		when(productRepository.save(product)).thenReturn(product);

		assertNotNull(productServiceImpl.save(product));

	}

	@Test
	void getById_theReturnException() {

		String id = "asd";

		when(productRepository.findByIdAndDeleteDateIsNull(id)).thenReturn(null);

		Assertions.assertThrows(ServiceException.class, () -> productServiceImpl.getById(id));
	}

	@Test
	void getById_theReturnObject() throws ServiceException {

		String id = "asd";

		when(productRepository.findByIdAndDeleteDateIsNull(id)).thenReturn(getProduct());

		assertNotNull(productServiceImpl.getById(id));
	}

	@Test
	void deleteById_theReturnException() {

		String id = "asd";

		when(productRepository.findByIdAndDeleteDateIsNull(id)).thenReturn(null);

		Assertions.assertThrows(ServiceException.class, () -> productServiceImpl.deleteById(id));
	}

	@Test
	void deleteById_theReturnOk() throws ServiceException {

		String id = "asd";

		Product product = getProduct();

		when(productRepository.findByIdAndDeleteDateIsNull(id)).thenReturn(product);

		productServiceImpl.deleteById(id);

		verify(productRepository, times(1)).save(product);

	}

	@Test
	void existProduct_theReturnException() {

		String id = "asd";

		when(productRepository.existsByIdAndDeleteDateIsNull(id)).thenReturn(false);

		Assertions.assertThrows(ServiceException.class, () -> productServiceImpl.existProduct(id));
	}

	@Test
	void existProduct_theReturnOk() throws ServiceException {

		String id = "asd";

		when(productRepository.existsByIdAndDeleteDateIsNull(id)).thenReturn(true);

		productServiceImpl.existProduct(id);

		verify(productRepository, times(1)).existsByIdAndDeleteDateIsNull(id);

	}

	private Product getProduct() {

		Product product = new Product();

		product.setName("pizza");
		product.setShortDescription("comun");

		return product;
	}

}
