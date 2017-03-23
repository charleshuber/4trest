package fr.metz.surfthevoid.tttt.rest.resources;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.metz.surfthevoid.tttt.rest.db.entity.GenericDbo;
import fr.metz.surfthevoid.tttt.rest.db.repo.GenericDao;
import fr.metz.surfthevoid.tttt.rest.resources.ValidationException.Errors;
import fr.metz.surfthevoid.tttt.rest.resources.ValidationException.Type;

@Transactional(TxType.REQUIRED)
public abstract class Validator<R extends Resource, T extends GenericDbo> {
	
	public void validate(R input, Operation op) throws ValidationException {
		Errors errors = new Errors();
		if(input == null){
			throw new ValidationException(Type.INVALID_INPUT, errors);
		}
		if(doUserCanAccessData(input, op, errors)){
			throw new ValidationException(Type.INVALID_RIGHT, errors);
		}
		validateState(input, op, errors);
		validateInput(input, op, errors);
	}
	
	protected void validateState(R input, Operation op, Errors errors) throws ValidationException {
		if(op == Operation.CREATE && input.getId() != null){
			throw new ValidationException(Type.INVALID_STATE, errors);
		} else if(op == Operation.UDPDATE){ 
			if(input.getId() == null){
				throw new ValidationException(Type.INVALID_STATE, errors);
			} 
			GenericDbo dbo = getDao().read(input.getId());
			if(dbo == null){
				throw new ValidationException(Type.INVALID_STATE, errors);
			}
		}
	}
	
	public void validateId(Long id) throws ValidationException {
		Errors errors = new Errors();
		if(id == null || getDao().read(id) == null){
			throw new ValidationException(Type.NOT_FOUND, errors);
		}
		if(doUserCanAccessData(id, errors)){
			throw new ValidationException(Type.INVALID_RIGHT, errors);
		}
	}
	
	protected Boolean doUserCanAccessData(R input, Operation op, Errors errors){return true;}
	protected Boolean doUserCanAccessData(Long id, Errors errors){return true;}
	protected abstract void validateInput(R input, Operation op, Errors errors) throws ValidationException;
	protected abstract GenericDao<T> getDao();
}
