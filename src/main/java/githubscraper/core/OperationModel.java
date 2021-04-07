package githubscraper.core;

public class OperationModel {

	private String operationName;
	
	public OperationModel(String operationName) {
		this.operationName = operationName;
	}
	
	protected String getOperationName() {
		return operationName;
	}

}
