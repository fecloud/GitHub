package demo.entity;

public class ImageDomain {
	private Integer id;
	private String name;//对象的标题名称
	private String next;//没有分支时要指向的下一个对象
	private String next_false;//异常时要跳转的对象
	private String next_true;//正常时要跳转的对象
	private String funcationPath;
	private Integer serialNO;//第几步
	private String expression;//条件表达式
	//1等待 2 3校验 4按键  6事务开始 7事务结束 8结束 9开始   12计时器 15调用公共过程 16条件步骤 17计算 
	private Integer type;//跟name对应
	private String summary;//要显示的条件名字
	private String comment;//步骤说明
	private String key;//对象的标识
	private String transactionName;//事务名称
	private Integer paramCount;//参数列表
	private String older_timer_name;
	private String timer_var;
	private Integer verify_operate_type;
	private Integer toleration;
	private String verify_source;
	private Integer verify_method;
	private String location_rect;
	private Integer timeout;
	private String expression_result;//计算结果要保存到的变量
	private Integer wait_time;
	private Integer TransactionStatus;
	private String TransactionResultCode;
	private String TransactionVariable;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getNext_false() {
		return next_false;
	}
	public void setNext_false(String next_false) {
		this.next_false = next_false;
	}
	public String getNext_true() {
		return next_true;
	}
	public void setNext_true(String next_true) {
		this.next_true = next_true;
	}
	public String getFuncationPath() {
		return funcationPath;
	}
	public void setFuncationPath(String funcationPath) {
		this.funcationPath = funcationPath;
	}
	public Integer getSerialNO() {
		return serialNO;
	}
	public void setSerialNO(Integer serialNO) {
		this.serialNO = serialNO;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	
	public Integer getParamCount() {
		return paramCount;
	}
	public void setParamCount(Integer paramCount) {
		this.paramCount = paramCount;
	}
	public String getOlder_timer_name() {
		return older_timer_name;
	}
	public void setOlder_timer_name(String older_timer_name) {
		this.older_timer_name = older_timer_name;
	}
	public String getTimer_var() {
		return timer_var;
	}
	public void setTimer_var(String timer_var) {
		this.timer_var = timer_var;
	}
	public Integer getVerify_operate_type() {
		return verify_operate_type;
	}
	public void setVerify_operate_type(Integer verify_operate_type) {
		this.verify_operate_type = verify_operate_type;
	}
	public Integer getToleration() {
		return toleration;
	}
	public void setToleration(Integer toleration) {
		this.toleration = toleration;
	}
	public String getVerify_source() {
		return verify_source;
	}
	public void setVerify_source(String verify_source) {
		this.verify_source = verify_source;
	}
	public Integer getVerify_method() {
		return verify_method;
	}
	public void setVerify_method(Integer verify_method) {
		this.verify_method = verify_method;
	}
	public String getLocation_rect() {
		return location_rect;
	}
	public void setLocation_rect(String location_rect) {
		this.location_rect = location_rect;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public String getExpression_result() {
		return expression_result;
	}
	public void setExpression_result(String expression_result) {
		this.expression_result = expression_result;
	}
	public Integer getWait_time() {
		return wait_time;
	}
	public void setWait_time(Integer wait_time) {
		this.wait_time = wait_time;
	}
	public Integer getTransactionStatus() {
		return TransactionStatus;
	}
	public void setTransactionStatus(Integer transactionStatus) {
		TransactionStatus = transactionStatus;
	}
	public String getTransactionResultCode() {
		return TransactionResultCode;
	}
	public void setTransactionResultCode(String transactionResultCode) {
		TransactionResultCode = transactionResultCode;
	}
	public String getTransactionVariable() {
		return TransactionVariable;
	}
	public void setTransactionVariable(String transactionVariable) {
		TransactionVariable = transactionVariable;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ImageDomain [id=" + id + ", name=" + name + ", next=" + next
				+ ", next_false=" + next_false + ", next_true=" + next_true
				+ ", funcationPath=" + funcationPath + ", serialNO=" + serialNO
				+ ", expression=" + expression + ", type=" + type
				+ ", summary=" + summary + ", comment=" + comment + ", key="
				+ key + ", transactionName=" + transactionName
				+ ", paramCount=" + paramCount + ", older_timer_name="
				+ older_timer_name + ", timer_var=" + timer_var
				+ ", verify_operate_type=" + verify_operate_type
				+ ", toleration=" + toleration + ", verify_source="
				+ verify_source + ", verify_method=" + verify_method
				+ ", location_rect=" + location_rect + ", timeout=" + timeout
				+ ", expression_result=" + expression_result + ", wait_time="
				+ wait_time + ", TransactionStatus=" + TransactionStatus
				+ ", TransactionResultCode=" + TransactionResultCode
				+ ", TransactionVariable=" + TransactionVariable + ", status="
				+ status + "]";
	}
}
