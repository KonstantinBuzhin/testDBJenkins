package factoryDB;

public class FactoryDBsql implements FactoryDB{

	@Override
	public ConnectorDB getConnectorDB(ConnectorDB connector) {
		return connector;
	}

}
