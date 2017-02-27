package dsy.utils;

import java.util.List;
import java.util.Map;

public class ModelAndView {

	private String code;//000:失败，001：成功
	private String message;
	private Object object;
	private boolean isNull = true;

	/**
	 * @param code
	 * @param message
	 */
	public ModelAndView(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * @param code
	 * @param message
	 * @param object
	 */
	public ModelAndView(String code, String message, Object object) {
		super();
		this.code = code;
		this.message = message;
		this.object = object;
		checkObject(object);
	}

	@SuppressWarnings("rawtypes")
	private void checkObject(Object o) {
		if (o != null) {
			if (o instanceof List) {
				List list = (List) o;
				if (list.size() != 0) {
					isNull = false;
				}
			} else if (o instanceof Map) {
				Map map = (Map) o;
				if (map.size() != 0) {
					isNull = false;
				}
			}
		}
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
