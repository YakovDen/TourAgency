package serviceImpl;

import java.util.ArrayList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.EntityBeanConverter;

/** service methods for convert from entity to bean(and List from beans to entities)
 *  and on the contrary using dozer
 **/

@Service
public class EntityBeanConverterImpl implements EntityBeanConverter{
	
	@Autowired
	private Mapper dozerMapper;
	
	@Override
	public <E, B, I> B convertToBean(E entity, Class<B> beanClass, String index) {
		B bean = dozerMapper.map(entity, beanClass, index);
		
		return bean;
	}
	
	@Override
	public <E, B, I> List<B> convertToBeanList(Iterable<E> entities, Class<B> beanClass, String index) {
		List<B> beans = new ArrayList<>();

		for (E entity : entities) {
			B bean = convertToBean(entity, beanClass, index);
			beans.add(bean);
		}

		return beans;
	}

	@Override
	public <E, B, I> E convertToEntity(B bean, Class<E> entityClass, String index) {
		E entity = dozerMapper.map(bean, entityClass, index);

		return entity;
	}

}
