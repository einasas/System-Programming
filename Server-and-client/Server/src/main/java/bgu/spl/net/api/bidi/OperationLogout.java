package bgu.spl.net.api.bidi;

import bgu.spl.net.api.bidi.impl.DataBase;

public class OperationLogout extends Operation {
	
	public OperationLogout(byte[] msg) {
		super(msg);
	}

	@Override
	public int getOperationCode() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String process(Connections<Operation> connections, int numberOfConnection, DataBase database) {
		if(!database.checkIfOnline(numberOfConnection)) {
			connections.send(numberOfConnection, new OperationError(this.getOperationCode()));
			return null;
		}
		connections.send(numberOfConnection, new OperationAck(this.getOperationCode()));
		database.disconnectUser(numberOfConnection);
		connections.disconnect(numberOfConnection);
		return "Terminate";
	}

	@Override
	public String getOperationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] encode(Operation message) {
		// TODO Auto-generated method stub
		return null;
	}

}
