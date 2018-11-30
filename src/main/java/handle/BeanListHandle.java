package handle;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//通用处理结果集的类
public class BeanListHandle<T> implements IResultSetHandle<List<T>> {
	private Class<T> beanType;

	//通过构造器赋值
	public BeanListHandle(Class<T> type) {
		beanType = type;
	}

	public List<T> handle(ResultSet rs) throws Exception {
		List<T> list =new ArrayList();
		BeanInfo beaninfo = Introspector.getBeanInfo(beanType, Object.class);
		//属性描述器
		PropertyDescriptor[] descriptors = beaninfo.getPropertyDescriptors();
		while (rs.next()) {
			T bean = beanType.newInstance();
			for (PropertyDescriptor descriptor : descriptors) {
				descriptor.getWriteMethod().invoke(bean, rs.getObject(descriptor.getName()));
			}
			list.add(bean);
		}
		return list;
	}
}
