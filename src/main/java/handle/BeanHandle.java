package handle;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

//通用处理结果集的类
public class BeanHandle<T> implements IResultSetHandle<T> {
	private Class<T> beanType;

	//通过构造器赋值
	public BeanHandle(Class<T> type) {
		beanType = type;
	}

	public T handle(ResultSet rs) throws Exception {
		BeanInfo beaninfo = Introspector.getBeanInfo(beanType, Object.class);
		T bean = beanType.newInstance();
		//属性描述器
		PropertyDescriptor[] descriptors = beaninfo.getPropertyDescriptors();
		while (rs.next()) {
			for (PropertyDescriptor descriptor : descriptors) {
				descriptor.getWriteMethod().invoke(bean, rs.getObject(descriptor.getName()));
			}
		}
		return bean;
	}
}
