package br.com.fw.products;

import java.util.List;

public class ProductRN {
	
	private ProductDAO dao;
	
	
	public void salvar(Product product) {
		dao = new ProductDAO();
		if(product.getId() == null) {
			dao.inserir(product);
		} else {
			dao.atualizar(product);
		}
	}
	
	public void excluir(Product product){
		dao = new ProductDAO();
		dao.excluir(product);
	}
	
	public List<Product> productsList(){
		dao = new ProductDAO();
		return dao.productsList();
	}
}
