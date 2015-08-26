package br.com.fw.consumer;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ConsumerBean {
	private Consumer consumer = new Consumer();
	private List<Consumer> consumersList;
	private ConsumerRN rn;

	public Consumer getConsumer() {
		return this.consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	public List<Consumer> getConsumersList() {
		this.rn = new ConsumerRN();
		if(this.consumersList == null) {
			this.consumersList = this.rn.consumersList();
		}
		return this.consumersList;
	}
	public void setConsumersList(List<Consumer> consumersList) {
		this.consumersList = consumersList;
	}
	
	public void salvar() {
		this.rn = new ConsumerRN();
		this.rn.salvar(this.consumer);
		this.consumer = new Consumer();
	}
	
	public void excluir() {
		this.rn = new ConsumerRN();
		this.rn.excluir(this.consumer);
		this.consumer = new Consumer();
	}
}
