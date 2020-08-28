package pe.com.bcp.api.webdto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseWebResponse<T> {
    private T data;

    public static BaseWebResponse successNoData() {
        
    	BaseWebResponse objeto = new BaseWebResponse();
    	
    	return objeto;
    }

    public static <T> BaseWebResponse<T> successWithData(T data) {
    	
    	BaseWebResponse<T> objeto = new BaseWebResponse();
    	objeto.setData(data);
    	return objeto;
    	
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
