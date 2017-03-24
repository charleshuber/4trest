package fr.metz.surfthevoid.tttt.rest.resources;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.metz.surfthevoid.tttt.rest.db.entity.GenericDbo;
import fr.metz.surfthevoid.tttt.rest.db.repo.GenericDao;


@Transactional(TxType.REQUIRES_NEW)
public abstract class ResourceStore<R extends Resource, T extends GenericDbo> {
	
	protected abstract GenericDao<T> getDao(); 
	protected abstract Validator<R,T> getValidator();
	
	public R create(R res) throws ValidationException{
		T dbo = validateAndTransform(res, Operation.CREATE);
		getDao().create(dbo);
		return extract(dbo);
	}
	
	public R read(Long id) throws ValidationException{
		getValidator().validateId(id);
		return extract(getDao().read(id));
	}
	
	public R update(R res) throws ValidationException{
		T dbo = validateAndTransform(res, Operation.UDPDATE);
		T mergedDbo = getDao().update(dbo);
		return extract(mergedDbo);
	}

	public R delete(Long id) throws ValidationException{
		getValidator().validateId(id);
		T dboToDelete = getDao().read(id);
		R deletedResource = extract(dboToDelete);
		getDao().delete(dboToDelete);
		return deletedResource;
	}
	
	private T validateAndTransform(R res, Operation op) throws ValidationException{
		getValidator().validate(res, op);
		return transform(res);
	}
	protected abstract T transform(R res);
	protected abstract R extract(T dbo);
}