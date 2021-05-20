package co.empresa.Crud.dao;

public class UsuarioDaoFactory {
	public static UsuarioDao getUsuarioDao(String type) {
		switch(type) {
		case "mysql":
			return new UsuarioDaoMysql();
		case "postgresql":
			return new UsuarioDaoPostgreSQL();
		default:
			return new UsuarioDaoMysql();
			
		}
	}
}
