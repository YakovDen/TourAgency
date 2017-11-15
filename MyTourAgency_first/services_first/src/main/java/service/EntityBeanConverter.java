package service;

import java.util.List;

public interface EntityBeanConverter {
	
	<E, B, I> B convertToBean(E entity, Class<B> beanClass, String index);

	<E, B, I> List<B> convertToBeanList(Iterable<E> entities, Class<B> beanClass, String index);

	<E, B, I> E convertToEntity(B bean, Class<E> entityClass, String index);

}
