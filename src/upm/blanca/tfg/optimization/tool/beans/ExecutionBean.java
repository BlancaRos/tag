package upm.blanca.tfg.optimization.tool.beans;


public class ExecutionBean {
	
	private String queryStartTime;
	private String queryEndTime;
	private int totalTime;
	private String queryDate;

	public ExecutionBean(){
	}
	public String getQueryStartTime() {
		return queryStartTime;
	}
	public String getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}
	public String getQueryEndTime() {
		return queryEndTime;
	}
	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
}