package netgloo.models;

import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.lang.annotation.Annotation;

public class DynamicTable implements Table {
    
    private String name,schema;

    public DynamicTable(String name,String schema) {
        this.name = name;
        this.schema = schema;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return DynamicTable.class;
    }

	@Override
	public String catalog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Index[] indexes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String schema() {
		// TODO Auto-generated method stub
		return schema;
	}

	@Override
	public UniqueConstraint[] uniqueConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

}