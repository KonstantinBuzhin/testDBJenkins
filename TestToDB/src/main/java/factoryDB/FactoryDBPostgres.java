package factoryDB;

public class FactoryDBPostgres implements FactoryDB{

	@Override
	public ConnectorDB getConnectorDB() {
		return new PostgreSQLConnectorDB();
	}

}
