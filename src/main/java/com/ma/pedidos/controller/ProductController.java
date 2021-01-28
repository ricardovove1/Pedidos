package com.ma.pedidos.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ma.pedidos.dto.ProductDto;
import com.ma.pedidos.entities.Product;
import com.ma.pedidos.exception.ServiceException;
import com.ma.pedidos.mapper.ProductMapper;
import com.ma.pedidos.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "product" })
@RestController
@RequestMapping("/productos")
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductService productService;

	@ApiOperation("Servicio para crear un producto")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = ProductDto.class),
			@ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProductDto save(@Valid @RequestBody ProductDto productDto) {

		logger.info("Creating product");

		Product product = productService.save(productMapper.convertToEntity(productDto));

		return productMapper.convertToDto(product);
	}

	@ApiOperation("Servicio para actualizar un producto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDto.class),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found") })
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ProductDto update(@PathVariable("id") String id, @Valid @RequestBody ProductDto productDto)
			throws ServiceException {

		logger.info("product update by id: {}", id);

		productService.existProduct(id);

		productDto.setId(id);

		Product product = productService.save(productMapper.convertToEntity(productDto));

		return productMapper.convertToDto(product);
	}

	@ApiOperation("Servicio para obtener todos los productos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDto.class) })
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<ProductDto> getAll() {

		logger.info("get all products");

		List<Product> products = productService.getAll();

		return productMapper.convertToDtoList(products);
	}

	@ApiOperation("Servicio para obtener un producto por id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDto.class),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ProductDto getById(@PathVariable("id") String id) throws ServiceException {

		logger.info("get product by id: {}", id);

		Product product = productService.getById(id);

		return productMapper.convertToDto(product);
	}

	@ApiOperation("Servicio para eliminar un producto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDto.class),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) throws ServiceException {

		logger.info("product delete by id: {}", id);

		productService.deleteById(id);
	}
}
