package br.com.fw.consumer;

import java.util.List;

public class ConsumerRN {
	
	private ConsumerDAO dao;
	
	
	public void salvar(Consumer consumer) {
		dao = new ConsumerDAO();
		if(consumer.getId() == null) {
			dao.inserir(consumer);
		} else {
			dao.atualizar(consumer);
		}
	}
	
	public void excluir(Consumer consumer){
		dao = new ConsumerDAO();
		dao.excluir(consumer);
	}
	
	public List<Consumer> consumersList(){
		dao = new ConsumerDAO();
		return dao.consumersList();
	}
}
