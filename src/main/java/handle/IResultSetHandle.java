package handle;

import java.sql.ResultSet;
import java.util.List;

public interface IResultSetHandle<T> {
	//处理结果集的规范
	 T handle(ResultSet rs) throws Exception;
	
}
