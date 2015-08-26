package br.com.fw.consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fw.resources.ConnectionFactory;


public class ConsumerDAO {
	private Connection conexao;
	private PreparedStatement consulta;

	public ConsumerDAO() {
		this.conexao = ConnectionFactory.getConnection();
	}

	public void inserir(Consumer consumer) {
		String sql = "insert into consumer (name, cpf, mail, phone) values (?, ?, ?, ?)";

		try {
			consulta = this.conexao.prepareStatement(sql);
			consulta.setString(1, consumer.getName());
			consulta.setString(2, consumer.getCpf());
			consulta.setString(3, consumer.getMail());
			consulta.setString(4, consumer.getPhone());
			consulta.execute();
			System.out.println("Cliente " + consumer.getName() + " inserido com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao incluir cliente. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				this.conexao.close();
			} catch (Throwable t) {
				System.out.println("Erro ao fechar operações de banco. Mensagem: " + t.getMessage());
			}
		}

	}

	public void atualizar(Consumer consumer) {
		String sql = "update consumer set name = ?, cpf = ?, mail = ?, phone = ? where id = ?";

		try {
			consulta = this.conexao.prepareStatement(sql);
			consulta.setString(1, consumer.getName());
			consulta.setString(2, consumer.getCpf());
			consulta.setString(3, consumer.getMail());
			consulta.setString(4, consumer.getPhone());
			consulta.setInt(5, consumer.getId());
			consulta.execute();
			System.out.println("Cliente " + consumer.getName() + " atualizado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar cliente. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				this.conexao.close();
			} catch (Throwable t) {
				System.out.println("Erro ao fechar operações de banco. Mensagem: " + t.getMessage());
			}
		}
	}

	public void excluir(Consumer consumer) {
		String sql = "delete from consumer where id = ?";

		try {
			consulta = this.conexao.prepareStatement(sql);
			consulta.setInt(1, consumer.getId());
			consulta.execute();
			System.out.println("Cliente " + consumer.getName() + " excluído com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				this.conexao.close();
			} catch (Throwable t) {
				System.out.println("Erro ao fechar operações de banco. Mensagem: " + t.getMessage());
			}
		}
	}

	public List<Consumer> consumersList() {
		ArrayList<Consumer> consumers = new ArrayList<>();
		ResultSet resultado = null;

		String sql = "select * from consumer";

		try {
			this.conexao.setAutoCommit(false);
			this.consulta = this.conexao.prepareStatement(sql);
			resultado = this.consulta.executeQuery();
			while (resultado.next()) {
				Consumer consumer = new Consumer();
				consumer.setId(resultado.getInt("id"));
				consumer.setName(resultado.getString("name"));
				consumer.setCpf(resultado.getString("cpf"));
				consumer.setMail(resultado.getString("mail"));
				consumer.setPhone(resultado.getString("phone"));
				consumers.add(consumer);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar clientes. Mensagem: " + e.getMessage());
		} finally {
			try {
				if (resultado != null) {
					resultado.close();
				}
				if (this.consulta != null) {
					this.consulta.close();
				}
				if (this.conexao != null) {
					this.conexao.close();
				}
			} catch (Throwable t) {
				System.out.println("Erro ao fechar operações de banco. Mensagem: " + t.getMessage());
			}
		}
		return consumers;
	}
}
