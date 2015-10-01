package br.com.fw.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fw.resources.ConnectionFactory;

public class ProductDAO {
	private Connection conexao;
	private PreparedStatement consulta;

	public ProductDAO() {
		this.conexao = ConnectionFactory.getConnection();
	}

	public void inserir(Product product) {
		String sql = "insert into product (name, value) values (?, ?)";

		try {
			consulta = this.conexao.prepareStatement(sql);
			consulta.setString(1, product.getName());
			consulta.setDouble(2, product.getValue());
			consulta.execute();
			System.out.println("Produto " + product.getName()
					+ " inserido com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao incluir produto. Mensagem: "
					+ e.getMessage());
		} finally {
			try {
				consulta.close();
				this.conexao.close();
			} catch (Throwable t) {
				System.out
						.println("Erro ao fechar operações de banco. Mensagem: "
								+ t.getMessage());
			}
		}

	}

	public void atualizar(Product product) {
		String sql = "update product set name = ?, value = ? where id = ?";

		try {
			consulta = this.conexao.prepareStatement(sql);
			consulta.setString(1, product.getName());
			consulta.setDouble(2, product.getValue());
			consulta.setInt(3, product.getId());
			consulta.execute();
			System.out.println("Produto " + product.getName()
					+ " atualizado com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar produto. Mensagem: "
					+ e.getMessage());
		} finally {
			try {
				consulta.close();
				this.conexao.close();
			} catch (Throwable t) {
				System.out
						.println("Erro ao fechar operações de banco. Mensagem: "
								+ t.getMessage());
			}
		}
	}

	public void excluir(Product product) {
		String sql = "delete from product where id = ?";

		try {
			consulta = this.conexao.prepareStatement(sql);
			consulta.setInt(1, product.getId());
			consulta.execute();
			System.out.println("Produto " + product.getName()
					+ " excluído com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao excluir produto. Mensagem: "
					+ e.getMessage());
		} finally {
			try {
				consulta.close();
				this.conexao.close();
			} catch (Throwable t) {
				System.out
						.println("Erro ao fechar operações de banco. Mensagem: "
								+ t.getMessage());
			}
		}
	}

	public List<Product> productsList() {
		ArrayList<Product> products = new ArrayList<>();
		ResultSet resultado = null;

		String sql = "select * from product";

		try {
			this.conexao.setAutoCommit(false);
			this.consulta = this.conexao.prepareStatement(sql);
			resultado = this.consulta.executeQuery();
			while (resultado.next()) {
				Product product = new Product();
				product.setId(resultado.getInt("id"));
				product.setName(resultado.getString("name"));
				product.setValue(resultado.getDouble("value"));
				products.add(product);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar produtos. Mensagem: "
					+ e.getMessage());
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
				System.out
						.println("Erro ao fechar operações de banco. Mensagem: "
								+ t.getMessage());
			}
		}
		return products;
	}
}
