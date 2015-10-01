package br.com.fw.products;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ProductBean {
	private Product product = new Product();
	private List<Product> productsList;
	private ProductRN rn;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getProductsList() {
		this.rn = new ProductRN();
		if(this.productsList == null) {
			this.productsList = this.rn.productsList();
		}
		return productsList;
	}
	public void setProductsList(List<Product> productsList) {
		this.productsList = productsList;
	}
	
	public void salvar() {
		this.rn = new ProductRN();
		this.rn.salvar(this.product);
		this.product = new Product();
	}
	
	public void excluir() {
		this.rn = new ProductRN();
		this.rn.excluir(this.product);
		this.product = new Product();
	}
}
