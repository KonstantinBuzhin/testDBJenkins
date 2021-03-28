package factoryDB;

public class FactoryDBMySQL implements FactoryDB{

	@Override
	public ConnectorDB getConnectorDB() {
		return new MySQLConnectorDB();
	}

}
