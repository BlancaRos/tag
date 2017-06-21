package upm.blanca.tfg.optimization.tool.beans;


public class ExecutionBean {
	
	private String queryStartTime;
	private String queryEndTime;
	private int totalTime;
	private String queryDate;

	public ExecutionBean(){
	}

	/**
	 * @return the queryStartTime
	 */
	public String getQueryStartTime() {
		return queryStartTime;
	}

	/**
	 * @param queryStartTime the queryStartTime to set
	 */
	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	/**
	 * @return the queryEndTime
	 */
	public String getQueryEndTime() {
		return queryEndTime;
	}

	/**
	 * @param queryEndTime the queryEndTime to set
	 */
	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	/**
	 * @return the totalTime
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * @return the queryDate
	 */
	public String getQueryDate() {
		return queryDate;
	}

	/**
	 * @param queryDate the queryDate to set
	 */
	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
	
}