package com.jprogrammers.component;

import org.primefaces.component.datatable.DataTable;

import javax.faces.component.FacesComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;

/**
 * Created by Vahid Forghani on 2/2/14.
 */
@FacesComponent(JDataTable.COMPONENT_TYPE)
public class JDataTable extends DataTable {

    public static final String COMPONENT_FAMILY = "com.jprogrammers.component";
    public static final String COMPONENT_TYPE = "com.jprogrammers.component.JDataTable";

    @Override
    public void broadcast(FacesEvent event) throws AbortProcessingException {
        setValue(null);
        super.broadcast(event);
    }
}
