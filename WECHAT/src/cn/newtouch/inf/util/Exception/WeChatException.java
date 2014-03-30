package cn.newtouch.inf.util.Exception;



/**
 * 统一异常处理类
 * @author wuxw
 *
 */
public class WeChatException extends Exception  {

	private static final long serialVersionUID = 1L;

	public WeChatException(String msg) {
		super(msg);
	}

	public WeChatException(String msg, Throwable cause) {
		super(msg, cause);
	}


	@Override
	public String getMessage() {
		return super.getMessage();
	}


	public Throwable getRootCause() {
		Throwable rootCause = null;
		Throwable cause = getCause();
		while (cause != null && cause != rootCause) {
			rootCause = cause;
			cause = cause.getCause();
		}
		return rootCause;
	}

	public Throwable getMostSpecificCause() {
		Throwable rootCause = getRootCause();
		return (rootCause != null ? rootCause : this);
	}

}

